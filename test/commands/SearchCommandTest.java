package commands;

import core.ApplicationData;
import org.junit.Before;
import org.junit.Test;
import models.Station;

import static org.junit.Assert.*;

public class SearchCommandTest {

    private SearchCommand searchCommand;
    private ApplicationData appData;

    @Before
    public void setUp() {
        appData = new ApplicationData();
        appData.init("test/resources/mockstations.csv", "test/resources/mocktracks.csv");
        searchCommand = new SearchCommand(appData, null); // null voor Scanner
    }

    @Test
    public void testBinarySearch() {
        Station gevondenStation = searchCommand.binairZoeken("EML");
        assertNotNull("Station moet gevonden worden", gevondenStation);
        assertEquals("Gevonden station moet de juiste zijn", "EML", gevondenStation.getStationsCode());
    }

    @Test
    public void testLinearSearch() {
        Station gevondenStation = searchCommand.lineairZoeken("EML");
        assertNotNull("Station moet gevonden worden", gevondenStation);
        assertEquals("Gevonden station moet de juiste zijn", "EML", gevondenStation.getStationsCode());
    }

    @Test
    public void testHashmapSearch() {
        Station gevondenStation = searchCommand.hashMapZoeken("EML");
        assertNotNull("Station moet gevonden worden", gevondenStation);
        assertEquals("Gevonden station moet de juiste zijn", "EML", gevondenStation.getStationsCode());
    }

    @Test
    public void testBSTSearch() {
        Station gevondenStation = searchCommand.bstZoeken("EML");
        assertNotNull("Station moet gevonden worden", gevondenStation);
        assertEquals("Gevonden station moet de juiste zijn", "EML", gevondenStation.getStationsCode());
    }
}
