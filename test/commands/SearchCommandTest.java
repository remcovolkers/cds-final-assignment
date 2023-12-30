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
    public void testPerformBinarySearch() {
        Station foundStation = searchCommand.binairZoeken("EML");
        assertNotNull("Station moet gevonden worden", foundStation);
        assertEquals("Gevonden station moet de juiste zijn", "EML", foundStation.getStationsCode());
    }

    @Test
    public void testPerformLinearSearch() {
        Station foundStation = searchCommand.lineairZoeken("EML");
        assertNotNull("Station moet gevonden worden", foundStation);
        assertEquals("Gevonden station moet de juiste zijn", "EML", foundStation.getStationsCode());
    }

    @Test
    public void testPerformHashMapSearch() {
        Station foundStation = searchCommand.hashMapZoeken("EML");
        assertNotNull("Station moet gevonden worden", foundStation);
        assertEquals("Gevonden station moet de juiste zijn", "EML", foundStation.getStationsCode());
    }

    @Test
    public void testPerformBSTSearch() {
        Station foundStation = searchCommand.bstZoeken("EML");
        assertNotNull("Station moet gevonden worden", foundStation);
        assertEquals("Gevonden station moet de juiste zijn", "EML", foundStation.getStationsCode());
    }
}
