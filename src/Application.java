import models.Track;
import models.Station;
import utils.DataLoader;

import java.util.ArrayList;

class Application {
    public ArrayList<Station> stations = new ArrayList<>();
    public ArrayList<Track> tracks = new ArrayList<>();

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