package com.vikingcloud.horsetrack.command;

import com.vikingcloud.horsetrack.AutomatedTellerMachine;

/**
 * Implementation of the Invalid Bet Command which allows handling scenarios where the user attempts to make invalid bets.
 */
public class InvalidBetCommand implements Command {
    private final String amount;

    public InvalidBetCommand(String amount) {
        this.amount = amount;
    }

    @Override
    public void execute(AutomatedTellerMachine atm) {
        System.out.println("Invalid Bet: " + amount);
    }
}
