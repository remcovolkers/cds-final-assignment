package commands;

import core.ApplicationData;
import org.junit.Before;
import org.junit.Test;

import java.util.Scanner;

import static org.junit.Assert.*;

public class CommandFactoryTest {

    private ApplicationData appData;
    private Scanner scanner;

    @Before
    public void setUp() {
        appData = new ApplicationData();
        scanner = new Scanner(System.in);
    }

    @Test
    public void testGetSearchCommand() {
        Command command = CommandFactory.getCommand("1", appData, scanner);
        assertTrue("Moet een instantie van SearchCommand zijn", command instanceof SearchCommand);
    }

    @Test
    public void testGetSortCommand() {
        Command command = CommandFactory.getCommand("2", appData, scanner);
        assertTrue("Moet een instantie van SortCommand zijn", command instanceof SortCommand);
    }

    @Test
    public void testGetGraphVizCommand() {
        Command command = CommandFactory.getCommand("3", appData, scanner);
        assertTrue("Moet een instantie van GraphVizCommand zijn", command instanceof GraphVizCommand);
    }

    @Test
    public void testGetAdvancedAlgorithmsCommand() {
        Command command = CommandFactory.getCommand("4", appData, scanner);
        assertTrue("Moet een instantie van AdvancedAlgorithmsCommand zijn", command instanceof AdvancedAlgorithmsCommand);
    }

    @Test
    public void testGetInvalidCommand() {
        Command command = CommandFactory.getCommand("invalid", appData, scanner);
        assertNull("Moet null zijn voor ongeldige input", command);
    }
}
