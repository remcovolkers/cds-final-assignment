package advancedalgorithms;

import datastructures.Graph;
import models.Station;
import models.Track;

import java.util.*;

public class AStarAlgorithm {
    private final Graph graaf;
    private final Map<Station, Double> goedkoopstePad;
    private final Map<Station, Double> huidigGoedkoopstePad;
    private final Map<Station, Station> afkomstigVan;
    private final PriorityQueue<StationDistancePair> teBezoekenStations;
    private final Station eindStation;
    private final ArrayList<Track> tracks;


    public AStarAlgorithm(Graph graaf, Station eindStation, ArrayList<Track> tracks) {
        this.graaf = graaf;
        this.eindStation = eindStation;
        this.tracks = tracks;
        this.goedkoopstePad = new HashMap<>();
        this.huidigGoedkoopstePad = new HashMap<>();
        this.afkomstigVan = new HashMap<>();
        this.teBezoekenStations = new PriorityQueue<>(Comparator.comparingDouble(StationDistancePair::distance));
    }


    private double heuristiek(Station huidigStation) {
        Track directeVerbinding = vindDirecteVerbinding(huidigStation, eindStation);
        if (directeVerbinding != null) {
            return directeVerbinding.getDistance();
        }
        return huidigStation.haversineDistanceTo(eindStation);
    }

    public List<Station> execute(Station startStation) {
        goedkoopstePad.put(startStation, 0.0);
        huidigGoedkoopstePad.put(startStation, heuristiek(startStation));
        teBezoekenStations.add(new StationDistancePair(startStation, huidigGoedkoopstePad.get(startStation)));

        while (!teBezoekenStations.isEmpty()) {
            Station current = teBezoekenStations.poll().station();
            if (current.equals(eindStation)) {
                return herleidPad();
            }

            for (Track track : graaf.getAanliggendeTracks(current.getStationsCode())) {
                Station neighbor = track.getStationNaar();
                double tentativeGScore = goedkoopstePad.get(current) + track.getDistance();

                if (tentativeGScore < goedkoopstePad.getOrDefault(neighbor, Double.MAX_VALUE)) {
                    afkomstigVan.put(neighbor, current);
                    goedkoopstePad.put(neighbor, tentativeGScore);
                    huidigGoedkoopstePad.put(neighbor, tentativeGScore + heuristiek(neighbor));
                    if (!teBezoekenStations.contains(new StationDistancePair(neighbor, huidigGoedkoopstePad.get(neighbor)))) {
                        teBezoekenStations.add(new StationDistancePair(neighbor, huidigGoedkoopstePad.get(neighbor)));
                    }
                }
            }
        }
        return new ArrayList<>();
    }

    private Track vindDirecteVerbinding(Station vanStation, Station naarStation) {
        // Zoek een directe track tussen de twee stations, indien beschikbaar
        for (Track track : tracks) {
            if (track.getStationVan().equals(vanStation) && track.getStationNaar().equals(naarStation)) {
                return track;
            }
        }
        return null;
    }


    // herleid pad startstation naar eindstation
    private List<Station> herleidPad() {
        LinkedList<Station> pad = new LinkedList<>();
        Station huidigStation = eindStation;
        while (huidigStation != null) {
            pad.addFirst(huidigStation);
            huidigStation = afkomstigVan.get(huidigStation);
        }
        return pad;
    }


    public Map<Station, Station> getAfkomstigVan() {
        return afkomstigVan;
    }

    public record StationDistancePair(Station station, Double distance) implements Comparable<StationDistancePair> {
        @Override
        public int compareTo(StationDistancePair other) {
            return this.distance.compareTo(other.distance);
        }
    }
}
