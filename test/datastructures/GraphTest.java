package datastructures;

import models.Station;
import models.Track;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class GraphTest {
    private Graph graph;

    @Before
    public void setUp() {
        graph = new Graph();

        // Voeg stations toe
        Station stationA = new Station("STA", "Station A", "slug-a", "CountryA", "type-a", 50.01, 4.01);
        Station stationB = new Station("STB", "Station B", "slug-b", "CountryB", "type-b", 51.02, 4.02);
        graph.addStation(stationA);
        graph.addStation(stationB);

        // Voeg track toe
        graph.addTrack(new Track(stationA, stationB, true));
    }

    @Test
    public void testGetAdjacentTracks() {
        List<Track> adjacentTracks = graph.getAdjacentTracks("STA");
        assertNotNull(adjacentTracks);
        assertFalse(adjacentTracks.isEmpty());

        // Verifieer dat de lijst een track bevat van Station A naar Station B
        Track track = adjacentTracks.getFirst();
        assertEquals("STB", track.getStationNaar().getCode());

        // Test de omgekeerde route van Station B naar Station A
        List<Track> reverseTracks = graph.getAdjacentTracks("STB");
        assertNotNull(reverseTracks);
        assertFalse(reverseTracks.isEmpty());
        Track reverseTrack = reverseTracks.getFirst();
        assertEquals("STA", reverseTrack.getStationNaar().getCode());
    }
}
