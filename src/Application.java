import models.Track;
import models.Station;
import utils.DataLoader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

class Application {
    private ArrayList<Station> stations = new ArrayList<>();
    private ArrayList<Track> tracks = new ArrayList<>();

    public void initializeArrays() {
        stations = DataLoader.loadStations("src/resources/stations.csv");
        tracks = DataLoader.loadTracks("src/resources/tracks.csv");
    }


    public ArrayList<Track> getTracks() {
        return tracks;
    }

    public ArrayList<Station> getStations() {
        return stations;
    }
}