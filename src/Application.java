import models.Connection;
import models.Station;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

class Application {
    private ArrayList<Station> stations = new ArrayList<>();
    private ArrayList<Connection> connections = new ArrayList<>();

    public void initializeArrays() {
        try (BufferedReader br = new BufferedReader(new FileReader("src/resources/stations.csv"))) {
            //skip header
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] lineValues = line.split(",");
                Station station = new Station(Integer.parseInt(lineValues[0]), lineValues[1], Integer.parseInt(lineValues[2]), lineValues[3], lineValues[4], lineValues[5], lineValues[6], lineValues[7], lineValues[8], Double.parseDouble(lineValues[9]), Double.parseDouble(lineValues[10]));
                stations.add(station);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader br = new BufferedReader(new FileReader("src/resources/tracks.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] lineValues = line.split(",");
                Connection connection = new Connection(lineValues[0], lineValues[1], Integer.parseInt(lineValues[2]), Integer.parseInt(lineValues[3]), Integer.parseInt(lineValues[4]));
                connections.add(connection);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Connection> getConnections() {
        return connections;
    }

    public ArrayList<Station> getStations() {
        return stations;
    }
}