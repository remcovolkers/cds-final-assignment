package datastructures;

import models.Station;
import models.Track;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Graph {
    private final Map<String, List<Track>> adjList;
    private final Map<String, Station> stationsMap;

    public Graph() {
        adjList = new HashMap<>();
        stationsMap = new HashMap<>();
    }

    public void addStation(Station station) {
        stationsMap.put(station.getCode(), station);
        adjList.putIfAbsent(station.getCode(), new LinkedList<>());
    }

    public void addTrack(Track track) {
        adjList.get(track.getStationVan().getCode()).add(track);
        // Voeg ook een omgekeerde track toe als het geen gerichte grafiek is
        adjList.get(track.getStationNaar().getCode()).add(
                new Track(track.getStationNaar(), track.getStationVan(), track.getBinnenland()));
    }

    public List<Track> getAdjacentTracks(String stationCode) {
        return adjList.get(stationCode);
    }

    public Station getStation(String code) {
        return stationsMap.get(code);
    }
}
