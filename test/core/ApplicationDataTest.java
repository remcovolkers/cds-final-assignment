package core;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ApplicationDataTest {
    private ApplicationData appData;

    @Before
    public void setUp() {
        appData = new ApplicationData();
        appData.init("test/resources/mockstations.csv", "test/resources/mocktracks.csv");
    }

    @Test
    public void testInit() {
        // Controleer of de stations en tracks correct zijn geladen
        assertNotNull("Stations mogen niet null zijn", appData.getStations());
        assertNotNull("Tracks mogen niet null zijn", appData.tracks);
    }
}
