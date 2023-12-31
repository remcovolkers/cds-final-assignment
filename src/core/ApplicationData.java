package core;

import datastructures.Graph;
import datastructures.RemcoList;
import models.Station;
import models.Track;
import utils.DataLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ApplicationData {
    public ArrayList<Station> stations = new ArrayList<>();
    public ArrayList<Track> tracks = new ArrayList<>();
    public RemcoList<Station> stations2 = new RemcoList<>();
    Map<String, Station> stationsMap = new HashMap<>();
    public final Graph spoorwegNetwerk = new Graph();

    public void init(String locatieCsvStations, String locatieCsvTracks) {
        stations = DataLoader.loadStations(locatieCsvStations);
        for (Station station : stations) {
            stationsMap.put(station.getStationsCode(), station);
        }
        stations2 = DataLoader.laadStationsNaarRemcoList(locatieCsvStations);
        tracks = DataLoader.loadTracks(locatieCsvTracks, stationsMap);
        buildGraph();
    }

    public ArrayList<Station> getStations() {
        return stations;
    }

    public RemcoList<Station> getStationsRemcoList() {
        return stations2;
    }

    private void buildGraph() {
        for (Station station : stations) {
            spoorwegNetwerk.addStation(station);
        }

        for (Track track : tracks) {
            Station stationVan = spoorwegNetwerk.getStation(track.getStationVan().getStationsCode());
            Station stationNaar = spoorwegNetwerk.getStation(track.getStationNaar().getStationsCode());
            if (stationVan != null && stationNaar != null) {
                spoorwegNetwerk.addTrack(track);
            }
        }
    }
}