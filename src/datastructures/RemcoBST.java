package datastructures;

import models.Station;

public class RemcoBST {
    private BSTNode root;

    public void add(Station station) {
        root = recursiefToevoegen(root, station);
    }

    private BSTNode recursiefToevoegen(BSTNode current, Station station) {
        if (current == null) {
            return new BSTNode(station);
        }

        int compare = station.getStationsCode().compareTo(current.station.getStationsCode());

        if (compare < 0) {
            current.left = recursiefToevoegen(current.left, station);
        } else if (compare > 0) {
            current.right = recursiefToevoegen(current.right, station);
        }

        return current;
    }

    public Station vindMetCode(String code) {
        return vindMetCodeRecursief(root, code);
    }

    private Station vindMetCodeRecursief(BSTNode huidigeNode, String stationsCode) {
        if (huidigeNode == null) {
            return null;
        }

        int compare = stationsCode.compareTo(huidigeNode.station.getStationsCode());

        if (compare == 0) {
            return huidigeNode.station;
        } else if (compare < 0) {
            return vindMetCodeRecursief(huidigeNode.left, stationsCode);
        } else {
            return vindMetCodeRecursief(huidigeNode.right, stationsCode);
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
        toGraphVizRecursief(root, sb);
        sb.append("}");
        return sb.toString();
    }

    private void toGraphVizRecursief(BSTNode node, StringBuilder stringBuilder) {
        if (node != null) {
            if (node.left != null) {
                stringBuilder.append("  \"").append(node.station.getStationsCode()).append("\" -> \"").append(node.left.station.getStationsCode()).append("\";\n");
                toGraphVizRecursief(node.left, stringBuilder);
            }
            if (node.right != null) {
                stringBuilder.append("  \"").append(node.station.getStationsCode()).append("\" -> \"").append(node.right.station.getStationsCode()).append("\";\n");
                toGraphVizRecursief(node.right, stringBuilder);
            }
        }
    }
}

