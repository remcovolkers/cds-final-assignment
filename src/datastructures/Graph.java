package datastructures;

import models.Station;
import models.Track;

import java.util.*;

public class Graph {
    private final Map<String, List<Track>> aanliggendeTracks;
    private final Map<String, Station> stationsMap;

    public Graph() {
        aanliggendeTracks = new HashMap<>();
        stationsMap = new HashMap<>();
    }

    public void addStation(Station station) {
        stationsMap.put(station.getStationsCode(), station);
        aanliggendeTracks.putIfAbsent(station.getStationsCode(), new LinkedList<>());
    }

    public void addTrack(Track track) {
        aanliggendeTracks.get(track.getStationVan().getStationsCode()).add(track);
    }

    public List<Track> getAanliggendeTracks(String stationCode) {
        return aanliggendeTracks.get(stationCode);
    }

    public Station getStation(String code) {
        return stationsMap.get(code);
    }

    public Collection<Station> getStations() {
        return stationsMap.values();
    }

    public List<Track> getAlleTracks() {
        List<Track> alleTracks = new ArrayList<>();
        for (List<Track> tracks : aanliggendeTracks.values()) {
            alleTracks.addAll(tracks);
        }
        return alleTracks;
    }

    public String toGraphViz() {
        StringBuilder graphViz = new StringBuilder("digraph G {\n");

        for (String stationCode : aanliggendeTracks.keySet()) {
            for (Track track : aanliggendeTracks.get(stationCode)) {
                String vanStation = track.getStationVan().getStationsCode();
                String naarStation = track.getStationNaar().getStationsCode();
                double afstand = track.getDistance();
                graphViz.append(String.format("  \"%s\" -> \"%s\" [label=\"%s km\"];\n", vanStation, naarStation, afstand));
            }
        }

        graphViz.append("}");
        return graphViz.toString();
    }

    @Override
    public String toString() {
        return "Graph met " + stationsMap.size() + " stations en " +
                aanliggendeTracks.values().stream().mapToInt(List::size).sum() + " verbindingen.";
    }

}
