package advancedalgorithms;

import core.ApplicationData;
import models.Station;
import models.Track;

import java.util.*;

public class BFSAlgorithm {
    ApplicationData appData;

    public BFSAlgorithm(ApplicationData appData) {
        this.appData = appData;
    }

    public List<Station> bfsConnectedStations(Station startStation, List<Station> stationsInRectangle) {
        List<Station> connectedStations = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        Queue<Station> queue = new LinkedList<>();

        queue.add(startStation);
        visited.add(startStation.getCode());

        while (!queue.isEmpty()) {
            Station current = queue.poll();
            connectedStations.add(current);

            for (Track track : appData.spoorwegNetwerk.getAdjacentTracks(current.getCode())) {
                Station nextStation = track.getStationNaar();
                if (stationsInRectangle.contains(nextStation) && !visited.contains(nextStation.getCode())) {
                    queue.add(nextStation);
                    visited.add(nextStation.getCode());
                }
            }
        }
        return connectedStations;
    }

}
