package test.searchalgorithms;

import models.Station;
import org.junit.Before;
import org.junit.Test;
import searchalgorithms.BinarySearch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.*;

public class BinarySearchTest {
    private List<Station> stations;
    private BinarySearch binarySearch;

    @Before
    public void setUp() {
        binarySearch = new BinarySearch();
        stations = new ArrayList<>();
        stations.add(new Station("001",
                "Station A",
                "Asgard",
                "D",
                "knooppuntIntercitystation",
                50.01,
                50.02));
        stations.add(new Station("002",
                "Station B",
                "Beaverly Hills",
                "NL",
                "knooppuntIntercitystation",
                50.03,
                50.04));
        stations.add(new Station("003",
                "Station C",
                "Citytropolis",
                "NL",
                "knooppuntIntercitystation",
                50.05,
                50.06));

        stations.sort(Comparator.comparing(Station::getCode));
    }

    @Test
    public void testSearchFound() {
        // Test een situatie waarbij het item wordt gevonden
        Station result = binarySearch.search(stations, "002");
        assertNotNull("Het station moet worden gevonden", result);
        assertEquals("De code van het gevonden station moet '002' zijn", "002", result.getCode());
    }

    @Test
    public void testSearchNotFound() {
        // Test een situatie waarbij het item niet wordt gevonden
        Station result = binarySearch.search(stations, "004");
        assertNull("Er zou geen station gevonden moeten worden", result);
    }

    @Test
    public void testSearchEmptyList() {
        // Test zoeken in een lege lijst
        Station result = binarySearch.search(new ArrayList<>(), "001");
        assertNull("Er zou geen station gevonden moeten worden in een lege lijst", result);
    }

    @Test
    public void testSearchAtBoundaries() {
        // Test zoeken naar items aan de uiteinden van de lijst
        Station resultFirst = binarySearch.search(stations, "001");
        assertNotNull("Het eerste station moet worden gevonden", resultFirst);
        assertEquals("De code van het eerste station moet '001' zijn", "001", resultFirst.getCode());

        Station resultLast = binarySearch.search(stations, "003");
        assertNotNull("Het laatste station moet worden gevonden", resultLast);
        assertEquals("De code van het laatste station moet '003' zijn", "003", resultLast.getCode());
    }
}

