package datastructures;

import models.Station;

public class RemcoBST {
    private BSTNode root;

    public void add(Station station) {
        root = addRecursive(root, station);
    }

    private BSTNode addRecursive(BSTNode current, Station station) {
        if (current == null) {
            return new BSTNode(station);
        }

        int compare = station.getFullName().compareTo(current.station.getFullName());

        if (compare < 0) {
            current.left = addRecursive(current.left, station);
        } else if (compare > 0) {
            current.right = addRecursive(current.right, station);
        }

        return current;
    }

    public Station findByName(String name) {
        return findByNameRecursive(root, name);
    }

    private Station findByNameRecursive(BSTNode current, String name) {
        if (current == null) {
            return null;
        }

        int compare = name.compareTo(current.station.getFullName());

        if (compare == 0) {
            return current.station;
        } else if (compare < 0) {
            return findByNameRecursive(current.left, name);
        } else {
            return findByNameRecursive(current.right, name);
        }
    }

    public class BSTNode {
        Station station;
        BSTNode left;
        BSTNode right;

        BSTNode(Station station) {
            this.station = station;
        }
    }
}

