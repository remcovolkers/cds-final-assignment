package advancedalgorithms;

import models.Track;

import java.util.*;

public class PrimAlgorithm {
    private final List<Track> tracksInRechthoek;

    public PrimAlgorithm(List<Track> tracksInRechthoek) {
        this.tracksInRechthoek = tracksInRechthoek;
    }

    public List<Track> berekenMcst(String startStationCode) {
        // Initialiseer de prioriteitswachtrij en een set voor bezochte stations
        PriorityQueue<Track> queue = new PriorityQueue<>(Comparator.comparingDouble(Track::getDistance));
        Set<String> bezochteStations = new HashSet<>();
        List<Track> mcstTracks = new ArrayList<>();

        // Voeg alle tracks van het startstation toe aan de wachtrij
        addTracksAanQueue(startStationCode, queue, bezochteStations);

        while (!queue.isEmpty() && bezochteStations.size() < tracksInRechthoek.size()) {
            Track huidigeTrack = queue.poll();

            // Als het eindstation van de track al bezocht is, sla deze over
            assert huidigeTrack != null;
            if (bezochteStations.contains(huidigeTrack.getStationNaar().getStationsCode())) {
                continue;
            }

            // Voeg de track toe aan de MCST en markeer het station als bezocht
            mcstTracks.add(huidigeTrack);
            String nextStationCode = huidigeTrack.getStationNaar().getStationsCode();
            bezochteStations.add(nextStationCode);

            // Voeg alle tracks van het nieuwe station toe aan de wachtrij
            addTracksAanQueue(nextStationCode, queue, bezochteStations);
        }

        return mcstTracks;
    }

    private void addTracksAanQueue(String stationCode, PriorityQueue<Track> queue, Set<String> visitedStations) {
        for (Track track : tracksInRechthoek) {
            // Voeg alleen tracks toe die beginnen bij het huidige station en waarvan het eindstation nog niet is bezocht
            if (track.getStationVan().getStationsCode().equals(stationCode) &&
                    !visitedStations.contains(track.getStationNaar().getStationsCode())) {
                queue.add(track);
            }
        }
    }
}
