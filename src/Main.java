import commands.Command;
import commands.CommandFactory;
import core.ApplicationData;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ApplicationData appData = new ApplicationData();
        appData.init();

        System.out.println("Welkom bij de Spoormanager-applicatie!");

        while (true) {
            System.out.println("\nKies een optie:");
            System.out.println("1. Zoeken");
            System.out.println("2. Sorteren");
            System.out.println("3. java-graphviz functionaliteit");

            System.out.println("0. Sluit de applicatie");

            System.out.print("Voer het nummer van uw keuze in: ");
            String input = scanner.nextLine();

            Command command = CommandFactory.getCommand(input, appData, scanner);

            if (command != null) {
                command.execute();
            } else {
                System.out.println("Ongeldige invoer, probeer het opnieuw.");
            }
        }
    }
}