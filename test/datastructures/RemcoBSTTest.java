package datastructures;

import static org.junit.Assert.*;

import models.Station;
import org.junit.Before;
import org.junit.Test;

public class RemcoBSTTest {
    private RemcoBST bst;

    @Before
    public void setUp() {
        bst = new RemcoBST();
        // Voeg stations toe aan bst
    }

    @Test
    public void testFindByName() {
        String searchName = "Station A";
        Station stationA = new Station("001", "Station A", "slug-a", "NL", "type-a", 50.01, 4.01);
        bst.add(stationA);

        assertEquals(stationA, bst.findByCode(searchName));
    }

    @Test
    public void testNotFoundByName() {
        String searchName = "Station X";
        assertNull(bst.findByCode(searchName));
    }
}
