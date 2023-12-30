package commands;

import core.ApplicationData;
import datastructures.RemcoBST;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.MutableGraph;
import guru.nidi.graphviz.parse.Parser;
import models.Station;

import java.io.File;
import java.util.Scanner;

public class GraphVizCommand implements Command {
    private final ApplicationData appData;
    private final Scanner scanner;
    private final RemcoBST remcoBST;

    public GraphVizCommand(ApplicationData appData, Scanner scanner) {
        this.appData = appData;
        this.scanner = scanner;
        this.remcoBST = new RemcoBST();
    }

    @Override
    public void execute() {
        System.out.println("\n>> GRAPHVIZ MENU << \n");
        System.out.println("Kies een optie voor GraphViz visualisatie:");
        System.out.println("1. Spoorwegnetwerk als GraphViz");
        System.out.println("2. BST als GraphViz");

        String invoer = scanner.nextLine();
        switch (invoer) {
            case "1":
                graphVizGraaf(appData.spoorwegNetwerk.toGraphViz(), "src/assets/spoorwegnetwerk.svg");
                break;
            case "2":
                buildBST();
                graphVizBST(remcoBST.toGraphViz(), "src/assets/binary-tree.svg");
                break;
            default:
                System.out.println("Ongeldige keuze.");
                break;
        }
    }

    private void buildBST() {
        for (Station station : appData.getStations()) {
            remcoBST.add(station);
        }
    }

    private void graphVizGraaf(String dotString, String locatie) {
        renderGraphViz(dotString, locatie);
    }

    private void graphVizBST(String dotString, String locatie) {
        renderGraphViz(dotString, locatie);
    }

    //code deels genomen van: https://stackoverflow.com/questions/26481910/how-to-call-graphviz-from-java
    //memory issue opgelost door: https://github.com/nidi3/graphviz-java/issues/12
    private static void renderGraphViz(String dotString, String locatie) {
        try {
            MutableGraph g = new Parser().read(dotString);
            Graphviz.fromGraph(g).totalMemory(1000000000)
                    .render(Format.SVG)
                    .toFile(new File(locatie));
            System.out.println("Grafiek is opgeslagen als " + locatie);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
