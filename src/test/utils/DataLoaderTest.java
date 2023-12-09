package test.utils;

import models.Station;
import models.Track;
import org.junit.Before;
import org.junit.Test;
import utils.DataLoader;

import static org.junit.Assert.*;

import java.util.ArrayList;

public class DataLoaderTest {

    private ArrayList<Station> stations;
    private ArrayList<Track> tracks;

    @Before
    public void setUp() {
        stations = DataLoader.loadStations("src/test/resources/mockstations.csv");
        tracks = DataLoader.loadTracks("src/test/resources/mocktracks.csv");
    }

    @Test
    public void testValidStationData() {
        // Test of de geldige data correct is geladen
        assertFalse("Lijst met stations moet niet leeg zijn", stations.isEmpty());
        assertEquals("Verwachte code komt niet overeen", "ABC", stations.getFirst().getCode());
        assertEquals("Verwachte naam komt niet overeen", "Station Naam", stations.getFirst().getFullName());
    }

    @Test
    public void testInvalidStationData() {
        // Test of de ongeldige data correct wordt genegeerd of verwerkt
        int expectedNumberOfStations = 2; // Aantal geldige stations de mock CSV
        assertEquals("Aantal stations komt niet overeen met verwachte", expectedNumberOfStations, stations.size());
    }

    @Test
    public void testValidTrackData() {
        assertFalse("Lijst met tracks moet niet leeg zijn", tracks.isEmpty());
        assertEquals("Verwachte code komt niet overeen", "ac", tracks.getFirst().getStationVan());
        assertEquals("Verwachte naam komt niet overeen", "ashd", tracks.getFirst().getStationNaar());
    }

    @Test
    public void testInvalidTrackData() {
        int expectedNumberOfTracks = 2;
        assertEquals("Aantal tracks komt niet overeen met verwachte", expectedNumberOfTracks, tracks.size());
    }
}
