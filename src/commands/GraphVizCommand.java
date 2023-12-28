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
        System.out.println("Kies een optie voor GraphViz visualisatie:");
        System.out.println("1. Spoorwegnetwerk als GraphViz");
        System.out.println("2. BST als GraphViz");

        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
                performGraphToGraphViz(appData.spoorwegNetwerk.toGraphViz(), "src/assets/spoorwegnetwerk.svg");
                break;
            case "2":
                buildBST();
                performBstToGraphViz(remcoBST.toGraphViz(), "src/assets/boomViz.svg");
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

    private void performGraphToGraphViz(String dotString, String outputPath) {
        // Hier komt de code om de GraphViz te renderen zoals eerder besproken
        renderGraphViz(dotString, outputPath);
    }

    private void performBstToGraphViz(String dotString, String outputPath) {
        // Hier komt de code om de GraphViz voor de BST te renderen
        renderGraphViz(dotString, outputPath);
    }

    private static void renderGraphViz(String dotString, String outputPath) {
        try {
            MutableGraph g = new Parser().read(dotString);
            Graphviz.fromGraph(g).totalMemory(1000000000)
                    .render(Format.SVG)
                    .toFile(new File(outputPath));
            System.out.println("Grafiek is opgeslagen als " + outputPath);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
