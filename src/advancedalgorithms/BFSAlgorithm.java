package advancedalgorithms;

import datastructures.Graph;
import models.Station;
import models.Track;

import java.util.*;

public class BFSAlgorithm {
    Graph spoortwegNetwerk;

    public BFSAlgorithm(Graph appData) {
        this.spoortwegNetwerk = appData;
    }

    public List<Station> bfsConnectedStations(Station startStation, List<Station> stationsInRechthoek) {
        List<Station> verbondenStations = new ArrayList<>();
        Set<String> bezocht = new HashSet<>();
        Queue<Station> queue = new LinkedList<>();

        queue.add(startStation);
        bezocht.add(startStation.getStationsCode());

        while (!queue.isEmpty()) {
            Station huidigStation = queue.poll();
            verbondenStations.add(huidigStation);

            for (Track track : spoortwegNetwerk.getAanliggendeTracks(huidigStation.getStationsCode())) {
                Station volgendStation = track.getStationNaar();
                if (stationsInRechthoek.contains(volgendStation) && !bezocht.contains(volgendStation.getStationsCode())) {
                    queue.add(volgendStation);
                    bezocht.add(volgendStation.getStationsCode());
                }
            }
        }
        return verbondenStations;
    }
}
