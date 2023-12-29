package advancedalgorithms;

import datastructures.Graph;
import models.Station;
import models.Track;

import java.util.*;

public class AStarAlgorithm {
    private final Graph graph;
    private final Map<Station, Double> gScores; // Kosten van start tot huidige knoop
    private final Map<Station, Double> fScores; // Geschatte kosten van start via huidige knoop naar doel
    private final Map<Station, Station> cameFrom;
    private final PriorityQueue<StationDistancePair> openSet;
    private final Station endStation;
    private final ArrayList<Track> tracks;


    public AStarAlgorithm(Graph graph, Station endStation, ArrayList<Track> tracks) {
        this.graph = graph;
        this.endStation = endStation;
        this.tracks = tracks;
        this.gScores = new HashMap<>();
        this.fScores = new HashMap<>();
        this.cameFrom = new HashMap<>();
        this.openSet = new PriorityQueue<>(Comparator.comparingDouble(StationDistancePair::distance));
    }

    // Deze methode moet de heuristiek implementeren
    private double heuristic(Station currentStation) {
        // Als je een directe track hebt van het huidige station naar het eindstation
        Track directTrack = findDirectTrack(currentStation, endStation);
        if (directTrack != null) {
            return directTrack.getDistance();
        }

        // Geen directe track, gebruik een andere methode (bijv. Haversine) voor een schatting
        return currentStation.haversineDistanceTo(endStation);
    }

    public List<Station> execute(Station startStation) {
        gScores.put(startStation, 0.0);
        fScores.put(startStation, heuristic(startStation));
        openSet.add(new StationDistancePair(startStation, fScores.get(startStation)));

        while (!openSet.isEmpty()) {
            Station current = openSet.poll().station();
            if (current.equals(endStation)) {
                return reconstructPath();
            }

            for (Track track : graph.getAdjacentTracks(current.getCode())) {
                Station neighbor = track.getStationNaar();
                double tentativeGScore = gScores.get(current) + track.getDistance();

                if (tentativeGScore < gScores.getOrDefault(neighbor, Double.MAX_VALUE)) {
                    cameFrom.put(neighbor, current);
                    gScores.put(neighbor, tentativeGScore);
                    fScores.put(neighbor, tentativeGScore + heuristic(neighbor));
                    if (!openSet.contains(new StationDistancePair(neighbor, fScores.get(neighbor)))) {
                        openSet.add(new StationDistancePair(neighbor, fScores.get(neighbor)));
                    }
                }
            }
        }
        return new ArrayList<>();
    }

    private Track findDirectTrack(Station fromStation, Station toStation) {
        // Zoek een directe track tussen de twee stations, indien beschikbaar
        for (Track track : tracks) {
            if (track.getStationVan().equals(fromStation) && track.getStationNaar().equals(toStation)) {
                return track;
            }
        }
        return null;
    }


    // Deze methode reconstrueert het pad van het eindstation tot het startstation
    private List<Station> reconstructPath() {
        LinkedList<Station> path = new LinkedList<>();
        Station current = endStation;
        while (current != null) {
            path.addFirst(current);
            current = cameFrom.get(current);
        }
        return path;
    }


    public Map<Station, Station> getCameFrom() {
        return cameFrom;
    }

    // Deze klasse combineert een station met zijn fScore voor de PriorityQueue
    public record StationDistancePair(Station station, Double distance) implements Comparable<StationDistancePair> {
        @Override
        public int compareTo(StationDistancePair other) {
            return this.distance.compareTo(other.distance);
        }
    }
}
