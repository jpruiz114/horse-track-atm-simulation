package com.vikingcloud.horsetrack;

import com.vikingcloud.horsetrack.command.Command;
import com.vikingcloud.horsetrack.command.QuitCommand;
import com.vikingcloud.horsetrack.parser.CommandParser;

import java.util.Scanner;

/**
 * This is the main class of the application.
 */
public class Main {
    private Main() {
        // Prevent instantiation
    }

    public static void main(String[] args) {
        AutomatedTellerMachine atm = new AutomatedTellerMachine();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running && scanner.hasNextLine()) {
            String input = scanner.nextLine().trim();

            Command command = CommandParser.parse(input, atm);

            if (command instanceof QuitCommand) {
                running = false;
            } else if (command != null) {
                command.execute(atm);
            }
        }
    }
}
