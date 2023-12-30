package commands;

import advancedalgorithms.AStarAlgorithm;
import advancedalgorithms.BFSAlgorithm;
import advancedalgorithms.DijkstraAlgorithm;
import advancedalgorithms.PrimAlgorithm;
import core.ApplicationData;
import datastructures.Graph;
import models.Station;
import models.Track;

import java.util.*;

public class AdvancedAlgorithmsCommand implements Command {
    private final ApplicationData appData;
    private final Scanner scanner;

    public AdvancedAlgorithmsCommand(ApplicationData appData, Scanner scanner) {
        this.appData = appData;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        System.out.println("\n >> ADVANCED ALGORITHMS MENU << \n");

        System.out.println("Selecteer een geavanceerde algoritme-optie:");
        System.out.println("1. Stations binnen een rechthoekige zone");
        System.out.println("2. Routeplanning Dijkstra");
        System.out.println("3. Routeplanning A*");

        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
                System.out.println("Voer de coördinaten in van de linker bovenhoek (bv. 52.3,6.6):");
                String[] linksBovenCoords = scanner.nextLine().split(",");
                double linksBovenLat = Double.parseDouble(linksBovenCoords[0]);
                double linksBovenLng = Double.parseDouble(linksBovenCoords[1]);
                System.out.println("Voer de coördinaten in van de rechter onderhoek (bv. 51.9,6.9):");
                String[] rechtsOnderCoords = scanner.nextLine().split(",");
                double rechtsOnderLat = Double.parseDouble(rechtsOnderCoords[0]);
                double rechtsOnderLng = Double.parseDouble(rechtsOnderCoords[1]);
                printStationsInRechthoek(linksBovenLat, linksBovenLng, rechtsOnderLat, rechtsOnderLng);
                break;
            case "2":
                System.out.println("Voer de stationscode in van het startstation (bv. LTV):");
                String dijkstraStart = scanner.nextLine();
                System.out.println("Voer de stationscode in van het eindstation (bv. LC):");
                String dijkstraEind = scanner.nextLine();
                printRoutePlanningDijkstra(dijkstraStart, dijkstraEind);
                break;
            case "3":
                System.out.println("Voer de stationscode in van het startstation (bv. LTV):");
                String aStarStart = scanner.nextLine();
                System.out.println("Voer de stationscode in van het eindstation (bv. LC):");
                String aStarEind = scanner.nextLine();
                printRoutePlanningAStar(aStarStart, aStarEind);
                break;
            default:
                System.out.println("Ongeldige keuze.");
                break;
        }
    }

    List<Station> printStationsInRechthoek(double linksBovenLat, double linksBovenLng, double rechtsOnderLat, double rechtsOnderLng) {
        // Bepaal welke stations binnen de rechthoek vallen
        List<Station> stationsInRechthoek = new ArrayList<>();
        for (Station station : appData.spoorwegNetwerk.getStations()) {
            double lat = station.getGeoLat();
            double lng = station.getGeoLng();
            if (lat >= rechtsOnderLat && lat <= linksBovenLat && lng >= linksBovenLng && lng <= rechtsOnderLng) {
                // Gevonden in rechthoek
                stationsInRechthoek.add(station);
            }
        }
        Station startStation = null;
        if (!stationsInRechthoek.isEmpty()) {
            // Kies eerste station
            startStation = stationsInRechthoek.getFirst();
        }

        // Voer BFS uit om alle verbonden stations te vinden
        BFSAlgorithm bfs = new BFSAlgorithm(appData.spoorwegNetwerk);
        List<Station> connectedStations = bfs.bfsConnectedStations(startStation, stationsInRechthoek);

        // Gebruik de lijst van verbonden stations om de bijbehorende tracks te vinden
        List<Track> tracksVoorMcst = vindTracksVerbondenStations(connectedStations, appData.spoorwegNetwerk);

        // Print MCST
        assert startStation != null;
        printMcst(tracksVoorMcst, startStation);

        return stationsInRechthoek;
    }

    List<Track> vindTracksVerbondenStations(List<Station> stations, Graph spoorwegNetwerk) {
        List<Track> tracks = new ArrayList<>();
        for (Station station : stations) {
            List<Track> aanliggendeTracks = spoorwegNetwerk.getAanliggendeTracks(station.getStationsCode());
            for (Track track : aanliggendeTracks) {
                if (stations.contains(spoorwegNetwerk.getStation(track.getStationNaar().getStationsCode()))) {
                    tracks.add(track);
                }
            }
        }
        return tracks;
    }


    List<Track> printMcst(List<Track> tracks, Station startStation) {
        PrimAlgorithm prim = new PrimAlgorithm(tracks);
        List<Track> mcstTracks = prim.berekenMcst(startStation.getStationsCode());
        double totaleLengte = mcstTracks.stream().mapToDouble(Track::getDistance).sum();
        System.out.println("Mcst binnen de rechthoek:");
        mcstTracks.forEach(System.out::println);
        System.out.println("Totale lengte van de spoorverbindingen: " + totaleLengte + " km");
        return mcstTracks;
    }

    List<Station> printRoutePlanningDijkstra(String startStationCode, String endStationCode) {
        // Zoek de Station objecten die overeenkomen met de gegeven codes
        Station startStation = appData.spoorwegNetwerk.getStation(startStationCode);
        Station eindStation = appData.spoorwegNetwerk.getStation(endStationCode);

        if (startStation == null || eindStation == null) {
            System.out.println("Een of beide stations zijn niet gevonden in het netwerk.");
            return null;
        }

        // Voer Dijkstra's algoritme uit
        DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(appData.spoorwegNetwerk);
        dijkstra.execute(startStation);

        // Haal de kortste afstand en het pad op
        Double afstand = dijkstra.getAfstanden().get(eindStation);
        LinkedList<Station> path = getPad(dijkstra.getVorige(), eindStation);

        // Toon de resultaten
        System.out.println("De kortste route van " + startStation.getNaamVolledig() +
                " naar " + eindStation.getNaamVolledig() + " is " + afstand + " km.");
        System.out.print("Route: ");
        path.forEach(station -> System.out.print(station.getNaamVolledig() + " -> "));
        System.out.println("Einde");
        return path;
    }

    List<Station> printRoutePlanningAStar(String startStationCode, String endStationCode) {
        // Zoek de Station objecten die overeenkomen met de gegeven codes
        Station startStation = appData.spoorwegNetwerk.getStation(startStationCode);
        Station eindStation = appData.spoorwegNetwerk.getStation(endStationCode);

        if (startStation == null || eindStation == null) {
            System.out.println("Een of beide stations zijn niet gevonden in het netwerk.");
            return null;
        }

        // Voer A* algoritme uit
        AStarAlgorithm aStarAlgorithm = new AStarAlgorithm(appData.spoorwegNetwerk, eindStation, appData.tracks);
        List<Station> pad = aStarAlgorithm.execute(startStation);

        // Bereken de totale afstand
        double totaleAfstand = calculateTotalDistance(pad);

        // Toon de resultaten
        System.out.println("De route van " + startStation.getNaamVolledig() +
                " naar " + eindStation.getNaamVolledig() + " via A* is " + totaleAfstand + " km.");
        System.out.print("Route: ");
        pad.forEach(station -> System.out.print(station.getNaamVolledig() + " -> "));
        System.out.println("Einde");

        return pad;
    }

    private double calculateTotalDistance(List<Station> pad) {
        double totalDistance = 0.0;
        for (int i = 0; i < pad.size() - 1; i++) {
            Station huidigStation = pad.get(i);
            Station volgendStation = pad.get(i + 1);
            // Vind de afstand tussen de huidige en de volgende station
            for (Track track : appData.spoorwegNetwerk.getAanliggendeTracks(huidigStation.getStationsCode())) {
                if (track.getStationNaar().equals(volgendStation)) {
                    totalDistance += track.getDistance();
                    break;
                }
            }
        }
        return totalDistance;
    }


    // Deze methode reconstrueert het pad van de eindstation tot de startstation
    private LinkedList<Station> getPad(Map<Station, Station> vorige, Station eindStation) {
        LinkedList<Station> pad = new LinkedList<>();
        Station tussen = eindStation;

        // Controleer of een pad bestaat
        if (vorige.get(tussen) == null) {
            return pad;
        }
        pad.add(tussen);
        while (vorige.get(tussen) != null) {
            tussen = vorige.get(tussen);
            pad.add(tussen);
        }
        // Om het pad van start naar eind te krijgen, moeten we de lijst omkeren
        Collections.reverse(pad);
        return pad;
    }
}
