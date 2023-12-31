package sortalgorithms;

import models.Station;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class InsertionSortTest {

    @Test
    public void testInsertionSort() {
        List<Station> stations = new ArrayList<>();
        stations.add(new Station("003", "Station C", "station-c", "NL", "type-c", 52.03, 4.03));
        stations.add(new Station("001", "Station A", "station-a", "NL", "type-a", 50.01, 4.01));
        stations.add(new Station("002", "Station B", "station-b", "NL", "type-b", 51.02, 4.02));

        InsertionSort.insertionSort(stations);

        assertTrue(isGesorteerd(stations));
    }

    private boolean isGesorteerd(List<Station> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i).compareTo(list.get(i + 1)) > 0) {
                return false;
            }
        }
        return true;
    }
}
