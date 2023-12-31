package searchalgorithms;


import models.Station;

import java.util.List;

public class BinarySearch implements SearchAlgorithm<List<Station>> {
    //inspiratie van https://www.geeksforgeeks.org/binary-search/ en sheets
    @Override
    public Station search(List<Station> stations, String code) {
        int low = 0;
        int high = stations.size() - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            Station midStation = stations.get(mid);
            int result = midStation.getStationsCode().compareTo(code);

            if (result == 0) {
                // Gevonden
                return midStation;
            } else if (result < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        // Niet gevonden
        return null;
    }
}
