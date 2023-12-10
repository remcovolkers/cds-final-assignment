import datastructures.RemcoList;
import models.Track;
import models.Station;
import utils.DataLoader;

import java.util.ArrayList;

class Application {
    public ArrayList<Station> stations = new ArrayList<>();
    public ArrayList<Track> tracks = new ArrayList<>();
    public RemcoList<Station> stations2 = new RemcoList<>();

    public void initializeArrays() {
        stations = DataLoader.loadStations("src/resources/stations.csv");
        stations2 = DataLoader.loadStationsToRemcoList("src/resources/stations.csv");
        tracks = DataLoader.loadTracks("src/resources/tracks.csv");
    }


    public ArrayList<Track> getTracks() {
        return tracks;
    }

    public ArrayList<Station> getStations() {
        return stations;
    }

    public RemcoList<Station> getStationsRemcoList() {
        return stations2;
    }
}