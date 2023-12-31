package datastructures;

import models.Station;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class RemcoBSTTest {
    private RemcoBST bst;
    Station stationA = new Station("001", "Station A", "slug-a", "NL", "type-a", 50.01, 4.01);
    Station stationB = new Station("002", "Station B", "slug-b", "NL", "type-b", 51.02, 4.02);
    Station stationC = new Station("003", "Station C", "slug-c", "NL", "type-c", 52.03, 4.03);


    @Before
    public void setUp() {
        bst = new RemcoBST();
        bst.add(stationA);
        bst.add(stationB);
        bst.add(stationC);
    }

    @Test
    public void testVindMetCode() {
        String searchCode = "001";
        assertEquals(stationA, bst.vindMetCode(searchCode));
    }

    @Test
    public void testVindNietMetCode() {
        String searchCode = "999";
        assertNull(bst.vindMetCode(searchCode));
    }
}
