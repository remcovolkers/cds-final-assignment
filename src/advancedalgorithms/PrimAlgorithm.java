package advancedalgorithms;

import models.Track;

import java.util.*;

public class PrimAlgorithm {
    private final List<Track> tracksInRechthoek;

    public PrimAlgorithm(List<Track> tracksInRechthoek) {
        this.tracksInRechthoek = tracksInRechthoek;
    }

    public List<Track> calculateMCST(String startStationCode) {
        // Initialiseer de prioriteitswachtrij en een set voor bezochte stations
        PriorityQueue<Track> queue = new PriorityQueue<>(Comparator.comparingDouble(Track::getDistance));
        Set<String> visitedStations = new HashSet<>();
        List<Track> mcstTracks = new ArrayList<>();

        // Voeg alle tracks van het startstation toe aan de wachtrij
        addTracksToQueue(startStationCode, queue, visitedStations);

        while (!queue.isEmpty() && visitedStations.size() < tracksInRechthoek.size()) {
            Track currentTrack = queue.poll();

            // Als het eindstation van de track al bezocht is, sla deze over
            assert currentTrack != null;
            if (visitedStations.contains(currentTrack.getStationNaar().getCode())) {
                continue;
            }

            // Voeg de track toe aan de MCST en markeer het station als bezocht
            mcstTracks.add(currentTrack);
            String nextStationCode = currentTrack.getStationNaar().getCode();
            visitedStations.add(nextStationCode);

            // Voeg alle tracks van het nieuwe station toe aan de wachtrij
            addTracksToQueue(nextStationCode, queue, visitedStations);
        }

        return mcstTracks;
    }

    private void addTracksToQueue(String stationCode, PriorityQueue<Track> queue, Set<String> visitedStations) {
        for (Track track : tracksInRechthoek) {
            // Voeg alleen tracks toe die beginnen bij het huidige station en waarvan het eindstation nog niet is bezocht
            if (track.getStationVan().getCode().equals(stationCode) &&
                    !visitedStations.contains(track.getStationNaar().getCode())) {
                queue.add(track);
            }
        }
    }
}
