package commands;

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
        System.out.println("2. Routeplanning");

        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
                printStationsInRectangle();
                break;
            case "2":
                printRoutePlanningStationToStation();
                break;
            default:
                System.out.println("Ongeldige keuze.");
                break;
        }
    }

    private void printStationsInRectangle() {
        // Vraag de gebruiker om de coördinaten van de rechthoek
        System.out.println("Voer de coördinaten in van de noordwestelijke hoek (bv. 52.3,6.6):");
        String[] nwCornerCoords = scanner.nextLine().split(",");
        double nwLat = Double.parseDouble(nwCornerCoords[0]);
        double nwLng = Double.parseDouble(nwCornerCoords[1]);

        System.out.println("Voer de coördinaten in van de zuidoostelijke hoek (bv. 51.9,6.9):");
        String[] seCornerCoords = scanner.nextLine().split(",");
        double seLat = Double.parseDouble(seCornerCoords[0]);
        double seLng = Double.parseDouble(seCornerCoords[1]);

        // Bepaal welke stations binnen de rechthoek vallen
        List<Station> stationsInRechthoek = new ArrayList<>();
        for (Station station : appData.spoorwegNetwerk.getStations()) {
            double lat = station.getGeoLat();
            double lng = station.getGeoLng();
            if (lat >= seLat && lat <= nwLat && lng >= nwLng && lng <= seLng) {
                // Gevonden in rechthoek
                stationsInRechthoek.add(station);
            }
        }
        Station startStation = null;
        if (!stationsInRechthoek.isEmpty()) {
            startStation = stationsInRechthoek.getFirst();  // Kies bijvoorbeeld het eerste station
        }

        // Voer BFS uit om alle verbonden stations te vinden
        BFSAlgorithm bfs = new BFSAlgorithm(appData);
        List<Station> connectedStations = bfs.bfsConnectedStations(startStation, stationsInRechthoek);

        // Gebruik de lijst van verbonden stations om de bijbehorende tracks te vinden
        List<Track> tracksForMCST = findTracksForStations(connectedStations, appData.spoorwegNetwerk);

        // Print de minimale kostenspanningsboom
        assert startStation != null;
        printMinimumCostSpanningTree(tracksForMCST, startStation);
    }

    private List<Track> findTracksForStations(List<Station> stations, Graph network) {
        List<Track> tracks = new ArrayList<>();
        for (Station station : stations) {
            List<Track> adjacentTracks = network.getAdjacentTracks(station.getCode());
            for (Track track : adjacentTracks) {
                if (stations.contains(network.getStation(track.getStationNaar().getCode()))) {
                    tracks.add(track);
                }
            }
        }
        return tracks;
    }

    private void printMinimumCostSpanningTree(List<Track> tracks, Station startStation) {
        PrimAlgorithm prim = new PrimAlgorithm(tracks);
        List<Track> mcstTracks = prim.calculateMCST(startStation.getCode());

        // Bereken de totale lengte
        double totalLength = mcstTracks.stream().mapToDouble(Track::getDistance).sum();

        // Toon de resultaten
        System.out.println("Minimale kostenspanningsboom binnen de rechthoek:");
        for (Track track : mcstTracks) {
            System.out.println(track);
        }
        System.out.println("Totale lengte van de spoorverbindingen: " + totalLength + " km");
    }

    private void printRoutePlanningStationToStation() {
        System.out.println("Voer de stationscode in van het startstation (bv. LTV):");
        String startStationCode = scanner.nextLine();
        System.out.println("Voer de stationscode in van het eindstation (bv. LC):");
        String endStationCode = scanner.nextLine();

        // Zoek de Station objecten die overeenkomen met de gegeven codes
        Station startStation = appData.spoorwegNetwerk.getStation(startStationCode);
        Station endStation = appData.spoorwegNetwerk.getStation(endStationCode);

        if (startStation == null || endStation == null) {
            System.out.println("Een of beide stations zijn niet gevonden in het netwerk.");
            return;
        }

        // Voer Dijkstra's algoritme uit
        DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(appData.spoorwegNetwerk);
        dijkstra.execute(startStation);

        // Haal de kortste afstand en het pad op
        Double distance = dijkstra.getDistances().get(endStation);
        LinkedList<Station> path = getPath(dijkstra.getPrevious(), endStation);

        // Toon de resultaten
        System.out.println("De kortste route van " + startStation.getFullName() +
                " naar " + endStation.getFullName() + " is " + distance + " km.");
        System.out.print("Route: ");
        path.forEach(station -> System.out.print(station.getFullName() + " -> "));
        System.out.println("Einde");
    }

    // Deze methode reconstrueert het pad van de eindstation tot de startstation
    private LinkedList<Station> getPath(Map<Station, Station> previous, Station endStation) {
        LinkedList<Station> path = new LinkedList<>();
        Station step = endStation;

        // Controleer of een pad bestaat
        if (previous.get(step) == null) {
            return path;
        }
        path.add(step);
        while (previous.get(step) != null) {
            step = previous.get(step);
            path.add(step);
        }
        // Om het pad van start naar eind te krijgen, moeten we de lijst omkeren
        Collections.reverse(path);
        return path;
    }
}
