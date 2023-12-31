package commands;

import core.ApplicationData;
import models.Station;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class SortCommandTest {

    private SortCommand sortCommand;

    @Before
    public void setUp() {
        ApplicationData appData = new ApplicationData();
        appData.init("test/resources/mockstations.csv", "test/resources/mocktracks.csv");

        sortCommand = new SortCommand(appData, null); // null voor Scanner aangezien deze niet gebruikt wordt in de test
    }

    @Test
    public void testInsertionSort() {
        List<Station> gesorteerdeStations = sortCommand.insertionSortUitvoering();
        assertTrue("Lijst moet gesorteerd zijn", isGesorteerd(gesorteerdeStations));
    }

    @Test
    public void testQuickSort() {
        List<Station> gesorteerdeStations = sortCommand.quickSortUitvoering();
        assertTrue("Lijst moet gesorteerd zijn", isGesorteerd(gesorteerdeStations));
    }

    private boolean isGesorteerd(List<Station> stations) {
        for (int i = 0; i < stations.size() - 1; i++) {
            if (stations.get(i).getStationsCode().compareTo(stations.get(i + 1).getStationsCode()) > 0) {
                return false;
            }
        }
        return true;
    }
}
