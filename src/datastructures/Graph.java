package datastructures;

import models.Station;
import models.Track;

import java.util.*;

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
    }

    public List<Track> getAdjacentTracks(String stationCode) {
        return adjList.get(stationCode);
    }

    public Station getStation(String code) {
        return stationsMap.get(code);
    }

    public Collection<Station> getStations() {
        return stationsMap.values();
    }

    public List<Track> getAllTracks() {
        List<Track> allTracks = new ArrayList<>();
        for (List<Track> tracks : adjList.values()) {
            allTracks.addAll(tracks);
        }
        return allTracks;
    }

    public String toGraphViz() {
        StringBuilder graphViz = new StringBuilder("digraph G {\n");

        for (String stationCode : adjList.keySet()) {
            for (Track track : adjList.get(stationCode)) {
                String fromStation = track.getStationVan().getCode();
                String toStation = track.getStationNaar().getCode();
                double distance = track.getDistance();
                graphViz.append(String.format("  \"%s\" -> \"%s\" [label=\"%s km\"];\n", fromStation, toStation, distance));
            }
        }

        graphViz.append("}");
        return graphViz.toString();
    }

    @Override
    public String toString() {
        return "Graph met " + stationsMap.size() + " stations en " +
                adjList.values().stream().mapToInt(List::size).sum() + " verbindingen.";
    }

}
