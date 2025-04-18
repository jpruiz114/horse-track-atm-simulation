package com.vikingcloud.horsetrack.command;

import com.vikingcloud.horsetrack.AutomatedTellerMachine;

/**
 * Implementation of the Invalid Command which allows handling scenarios where the user attempts to use invalid commands.
 */
public class InvalidCommand implements Command {
    private final String rawInput;

    public InvalidCommand(String rawInput) {
        this.rawInput = rawInput;
    }

    @Override
    public void execute(AutomatedTellerMachine atm) {
        System.out.println("Invalid Command: " + rawInput);
    }
}
