package advancedalgorithms;

import datastructures.DijkstraMinHeap;
import datastructures.Graph;
import models.Station;
import models.Track;

import java.util.HashMap;
import java.util.Map;

public class DijkstraAlgorithm {
    private final Graph graaf;
    private final Map<Station, Double> afstanden;
    private final Map<Station, Station> vorige;
    private final DijkstraMinHeap minHeap;

    public DijkstraAlgorithm(Graph graaf) {
        this.graaf = graaf;
        this.afstanden = new HashMap<>();
        this.vorige = new HashMap<>();
        this.minHeap = new DijkstraMinHeap();
    }

    public void execute(Station startStation) {
        for (Station station : graaf.getStations()) {
            afstanden.put(station, Double.MAX_VALUE);
            vorige.put(station, null);
            minHeap.add(new StationDistancePair(station, Double.MAX_VALUE));
        }

        afstanden.put(startStation, 0.0);
        minHeap.decreaseKey(startStation, 0.0);

        while (!minHeap.isEmpty()) {
            StationDistancePair stationDistancePair = minHeap.pop();
            Station currentStation = stationDistancePair.station();
            double distance = stationDistancePair.distance();

            for (Track track : graaf.getAanliggendeTracks(currentStation.getStationsCode())) {
                Station aanliggendStation = track.getStationNaar();
                double nieuweAfstand = distance + track.getDistance();

                if (nieuweAfstand < afstanden.get(aanliggendStation)) {
                    afstanden.put(aanliggendStation, nieuweAfstand);
                    vorige.put(aanliggendStation, currentStation);
                    minHeap.decreaseKey(aanliggendStation, nieuweAfstand);
                }
            }
        }
    }

    public Map<Station, Double> getAfstanden() {
        return afstanden;
    }

    public Map<Station, Station> getVorige() {
        return vorige;
    }

    public record StationDistancePair(Station station, Double distance) implements Comparable<StationDistancePair> {
        @Override
        public int compareTo(StationDistancePair other) {
            return this.distance.compareTo(other.distance);
        }
    }
}
