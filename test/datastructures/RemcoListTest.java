package datastructures;

import models.Station;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RemcoListTest {
    private RemcoList<Station> list;

    @Before
    public void setUp() {
        list = new RemcoList<>();
        list.add(new Station("001", "Station A", "slug-a", "NL", "type-a", 50.01, 4.01));
        list.add(new Station("002", "Station B", "slug-b", "BE", "type-b", 51.02, 4.02));
        list.add(new Station("003", "Station C", "slug-c", "DE", "type-c", 52.03, 4.03));
    }

    @Test
    public void testIsEmpty() {
        assertFalse("List should not be empty", list.isEmpty());
        list = new RemcoList<>(); // reset list
        assertTrue("New list should be empty", list.isEmpty());
    }

    @Test
    public void testSize() {
        assertEquals("List size should be 3", 3, list.size());
    }

    @Test
    public void testAdd() {
        Station newStation = new Station("004", "Station D", "slug-d", "FR", "type-d", 53.04, 4.04);
        list.add(newStation);
        assertEquals("List size should be 4 after adding", 4, list.size());
    }

    @Test
    public void testContains() {
        Station station = new Station("002", "Station B", "slug-b", "BE", "type-b", 51.02, 4.02);
        assertTrue("List should contain station with code 002", list.contains(station));
    }

    @Test
    public void testRemove() {
        Station station = new Station("002", "Station B", "slug-b", "BE", "type-b", 51.02, 4.02);
        list.remove(station);
        assertEquals("List size should be 2 after removing", 2, list.size());
        assertFalse("List should not contain station with code 002 after removing", list.contains(station));
    }

    @Test
    public void testGet() {
        Station station = list.get(new Station("002", "", "", "", "", 0, 0));
        assertNotNull("Should get station with code 002", station);
        assertEquals("The retrieved station should have the code 002", "002", station.getStationsCode());
    }
}
