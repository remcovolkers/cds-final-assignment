package utils;

import datastructures.RemcoList;
import models.Station;
import models.Track;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class DataLoaderTest {
    private ArrayList<Station> stations;
    private RemcoList<Station> remcoStations;
    private ArrayList<Track> tracks;

    @Before
    public void setUp() {
        stations = DataLoader.loadStations("test/resources/mockstations.csv");
        remcoStations = DataLoader.loadStationsToRemcoList("test/resources/mockstations.csv");

        Map<String, Station> stationsMap = convertListToMap(stations);
        tracks = DataLoader.loadTracks("test/resources/mocktracks.csv", stationsMap);

    }

    @Test
    public void testValidStationData() {
        assertFalse("Lijst met stations moet niet leeg zijn", stations.isEmpty());
        assertEquals("Verwachte code komt niet overeen", "MA", stations.getFirst().getCode());
        assertEquals("Verwachte naam komt niet overeen", "\"Augsburg Hbf\"", stations.getFirst().getFullName());
    }

    @Test
    public void testRemcoListNotEmpty() {
        assertFalse("RemcoList met stations moet niet leeg zijn", remcoStations.isEmpty());
    }

    @Test
    public void testInvalidStationData() {
        int expectedNumberOfStations = 2; // Aantal geldige stations de mock CSV
        assertEquals("Aantal stations komt niet overeen met verwachte", expectedNumberOfStations, stations.size());
    }


    @Test
    public void testValidRemcoListStationData() {
        Station firstStation = remcoStations.getHead().getData();
        assertNotNull("Eerste station in RemcoList mag niet null zijn", firstStation);
        assertEquals("Verwachte code komt niet overeen", "MA", firstStation.getCode());
        assertEquals("Verwachte naam komt niet overeen", "\"Augsburg Hbf\"", firstStation.getFullName());
    }

    @Test
    public void testValidTrackData() {
        assertFalse("Lijst met tracks moet niet leeg zijn", tracks.isEmpty());
        assertEquals("Verwachte code komt niet overeen", "ac", tracks.getFirst().getStationVan());
        assertEquals("Verwachte naam komt niet overeen", "ashd", tracks.getFirst().getStationNaar());
    }

    @Test
    public void testRemcoListInvalidData() {
        int expectedNumberOfStations = 2; // Aantal geldige stations in de mock CSV
        assertEquals("Aantal stations in RemcoList komt niet overeen met verwachte", expectedNumberOfStations, remcoStations.size());
    }

    @Test
    public void testInvalidTrackData() {
        int expectedNumberOfTracks = 2;
        assertEquals("Aantal tracks komt niet overeen met verwachte", expectedNumberOfTracks, tracks.size());
    }

    private Map<String, Station> convertListToMap(ArrayList<Station> stations) {
        Map<String, Station> stationsMap = new HashMap<>();
        for (Station station : stations) {
            stationsMap.put(station.getCode(), station);
        }
        return stationsMap;
    }

}
