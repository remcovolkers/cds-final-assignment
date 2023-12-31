package commands;

import core.ApplicationData;
import models.Station;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class AdvancedAlgorithmsCommandTest {
    private AdvancedAlgorithmsCommand command;

    @Before
    public void setUp() {
        ApplicationData appData = new ApplicationData();
        appData.init("test/resources/mockstations.csv", "test/resources/mocktracks.csv");
        command = new AdvancedAlgorithmsCommand(appData, null);
    }

    @Test
    public void testPrintStationsInRechthoek() {
        List<Station> stations = command.printStationsInRechthoek(52.4, 5.5, 52.2, 5.7);
        assertFalse("Moet minstens één station bevatten", stations.isEmpty());
        assertTrue("Moet station EML bevatten", stations.stream().anyMatch(s -> s.getStationsCode().equals("EML")));
    }

    @Test
    public void testRoutePlanningDijkstra() {
        List<Station> path = command.printRoutePlanningDijkstra("EML", "PT");
        assertNotNull("Pad mag niet null zijn", path);
        assertFalse("Pad mag niet leeg zijn", path.isEmpty());
    }

    @Test
    public void testRoutePlanningAStar() {
        List<Station> path = command.printRoutePlanningAStar("EML", "PT");
        assertNotNull("Pad mag niet null zijn", path);
        assertFalse("Pad mag niet leeg zijn", path.isEmpty());
    }
}
