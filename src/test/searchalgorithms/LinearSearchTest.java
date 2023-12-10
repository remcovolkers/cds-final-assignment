package test.searchalgorithms;

import datastructures.RemcoList;
import models.Station;
import org.junit.Before;
import org.junit.Test;
import searchalgorithms.LinearSearch;

import static org.junit.Assert.*;

public class LinearSearchTest {
    private RemcoList<Station> stations;
    private LinearSearch linearSearch;

    @Before
    public void setUp() {
        linearSearch = new LinearSearch();
        stations = new RemcoList<>();
        stations.add(new Station("001",
                "Station A",
                "Asgard",
                "D",
                "knooppuntIntercitystation",
                50.01,
                50.02));
        stations.add(new Station("003",
                "Station B",
                "Beaverly Hills",
                "NL",
                "knooppuntIntercitystation",
                50.03,
                50.04));
        stations.add(new Station("002",
                "Station C",
                "Citytropolis",
                "NL",
                "knooppuntIntercitystation",
                50.05,
                50.06));
    }

    @Test
    public void testSearchFound() {
        Station result = linearSearch.search(stations, "002");
        assertNotNull("Het station moet worden gevonden", result);
        assertEquals("De code van het gevonden station moet '002' zijn", "002", result.getCode());
    }

    @Test
    public void testSearchNotFound() {
        Station result = linearSearch.search(stations, "004");
        assertNull("Er zou geen station gevonden moeten worden", result);
    }

    @Test
    public void testSearchEmptyList() {
        RemcoList<Station> emptyList = new RemcoList<>();
        Station result = linearSearch.search(emptyList, "001");
        assertNull("Er zou geen station gevonden moeten worden in een lege lijst", result);
    }

    @Test
    public void testSearchAtBoundaries() {
        Station resultFirst = linearSearch.search(stations, "001");
        assertNotNull("Het eerste station moet worden gevonden", resultFirst);
        assertEquals("De code van het eerste station moet '001' zijn", "001", resultFirst.getCode());

        Station resultLast = linearSearch.search(stations, "003");
        assertNotNull("Het laatste station moet worden gevonden", resultLast);
        assertEquals("De code van het laatste station moet '003' zijn", "003", resultLast.getCode());
    }
}
