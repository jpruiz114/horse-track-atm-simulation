package com.vikingcloud.horsetrack.command;

import com.vikingcloud.horsetrack.AutomatedTellerMachine;

/**
 * Implementation of the Set Winner Command which allows the user to set the winner racehorse.
 */
public class SetWinnerCommand implements Command {
    private final int horseNumber;

    public SetWinnerCommand(int horseNumber) {
        this.horseNumber = horseNumber;
    }

    @Override
    public void execute(AutomatedTellerMachine atm) {
        atm.setWinningHorse(horseNumber);
    }
}
