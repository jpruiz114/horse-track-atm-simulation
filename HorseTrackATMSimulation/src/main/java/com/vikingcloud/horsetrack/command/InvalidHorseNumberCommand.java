package com.vikingcloud.horsetrack.command;

import com.vikingcloud.horsetrack.AutomatedTellerMachine;

/**
 * Implementation of the Invalid Horse Number Command which allows handling scenarios where the user attempts to execute commands on racehorses non-existing in the system.
 */
public class InvalidHorseNumberCommand implements Command {
    private final int horseNumber;

    public InvalidHorseNumberCommand(int horseNumber) {
        this.horseNumber = horseNumber;
    }

    @Override
    public void execute(AutomatedTellerMachine atm) {
        System.out.println("Invalid Horse Number: " + horseNumber);
    }
}
