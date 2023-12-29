package advancedalgorithms;

import datastructures.DijkstraMinHeap;
import datastructures.Graph;
import models.Station;
import models.Track;

import java.util.HashMap;
import java.util.Map;

public class DijkstraAlgorithm {
    private final Graph graph;
    private final Map<Station, Double> distances;
    private final Map<Station, Station> previous;
    private final DijkstraMinHeap minHeap;

    public DijkstraAlgorithm(Graph graph) {
        this.graph = graph;
        this.distances = new HashMap<>();
        this.previous = new HashMap<>();
        this.minHeap = new DijkstraMinHeap();
    }

    public void execute(Station startStation) {
        for (Station station : graph.getStations()) {
            distances.put(station, Double.MAX_VALUE);
            previous.put(station, null);
            minHeap.add(new StationDistancePair(station, Double.MAX_VALUE));
        }

        distances.put(startStation, 0.0);
        minHeap.decreaseKey(startStation, 0.0);

        while (!minHeap.isEmpty()) {
            StationDistancePair stationDistancePair = minHeap.pop();
            Station currentStation = stationDistancePair.station();
            double distance = stationDistancePair.distance();

            for (Track track : graph.getAdjacentTracks(currentStation.getCode())) {
                Station adjacentStation = track.getStationNaar();
                double newDistance = distance + track.getDistance();

                if (newDistance < distances.get(adjacentStation)) {
                    distances.put(adjacentStation, newDistance);
                    previous.put(adjacentStation, currentStation);
                    minHeap.decreaseKey(adjacentStation, newDistance);
                }
            }
        }
    }

    public Map<Station, Double> getDistances() {
        return distances;
    }

    public Map<Station, Station> getPrevious() {
        return previous;
    }

    public record StationDistancePair(Station station, Double distance) implements Comparable<StationDistancePair> {
        @Override
        public int compareTo(StationDistancePair other) {
            return this.distance.compareTo(other.distance);
        }
    }
}
