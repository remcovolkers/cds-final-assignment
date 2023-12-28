package searchalgorithms;

import models.Station;

public interface SearchAlgorithm<T> {
    Station search(T stations, String code);
}
