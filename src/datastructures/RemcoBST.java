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

        int compare = station.getCode().compareTo(current.station.getCode());

        if (compare < 0) {
            current.left = addRecursive(current.left, station);
        } else if (compare > 0) {
            current.right = addRecursive(current.right, station);
        }

        return current;
    }

    public Station findByCode(String code) {
        return findByCodeRecursive(root, code);
    }

    private Station findByCodeRecursive(BSTNode current, String code) {
        if (current == null) {
            return null;
        }

        int compare = code.compareTo(current.station.getCode());

        if (compare == 0) {
            return current.station;
        } else if (compare < 0) {
            return findByCodeRecursive(current.left, code);
        } else {
            return findByCodeRecursive(current.right, code);
        }
    }

    public static class BSTNode {
        Station station;
        BSTNode left;
        BSTNode right;

        BSTNode(Station station) {
            this.station = station;
        }
    }

    public String toGraphViz() {
        StringBuilder sb = new StringBuilder();
        sb.append("digraph BST {\n");
        toGraphVizRecursive(root, sb);
        sb.append("}");
        return sb.toString();
    }

    private void toGraphVizRecursive(BSTNode node, StringBuilder sb) {
        if (node != null) {
            if (node.left != null) {
                sb.append("  \"").append(node.station.getCode()).append("\" -> \"").append(node.left.station.getCode()).append("\";\n");
                toGraphVizRecursive(node.left, sb);
            }
            if (node.right != null) {
                sb.append("  \"").append(node.station.getCode()).append("\" -> \"").append(node.right.station.getCode()).append("\";\n");
                toGraphVizRecursive(node.right, sb);
            }
        }
    }
}

