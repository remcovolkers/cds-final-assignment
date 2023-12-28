package commands;

import core.ApplicationData;

import java.util.Scanner;

public class CommandFactory {
    public static Command getCommand(String input, ApplicationData appData, Scanner scanner) {
        return switch (input) {
            case "1" -> new SearchCommand(appData, scanner);
            case "2" -> new SortCommand(appData, scanner);
            case "3" -> new GraphVizCommand(appData, scanner);
            case "4" -> new AdvancedAlgorithmsCommand(appData, scanner);
            default -> null;
        };
    }
}
