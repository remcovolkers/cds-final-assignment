import datastructures.Graph;
import datastructures.RemcoList;
import models.Track;
import models.Station;
import utils.DataLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class Application {
    public ArrayList<Station> stations = new ArrayList<>();
    public ArrayList<Track> tracks = new ArrayList<>();
    public RemcoList<Station> stations2 = new RemcoList<>();
    Map<String, Station> stationsMap = new HashMap<>();
    public final Graph spoorwegNetwerk = new Graph();

    public void initializeApplicationData() {
        stations = DataLoader.loadStations("src/resources/stations.csv");
        for (Station station : stations) {
            stationsMap.put(station.getCode(), station);
        }
        stations2 = DataLoader.loadStationsToRemcoList("src/resources/stations.csv");
        tracks = DataLoader.loadTracks("src/resources/tracks.csv", stationsMap);
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
            Station stationVan = spoorwegNetwerk.getStation(track.getStationVan().getCode());
            Station stationNaar = spoorwegNetwerk.getStation(track.getStationNaar().getCode());
            if (stationVan != null && stationNaar != null) {
                spoorwegNetwerk.addTrack(track);
            }
        }
        System.out.println(spoorwegNetwerk);
    }
}