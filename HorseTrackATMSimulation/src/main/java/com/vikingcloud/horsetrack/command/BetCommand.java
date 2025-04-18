package com.vikingcloud.horsetrack.command;

import com.vikingcloud.horsetrack.AutomatedTellerMachine;

/**
 * Implementation of the Bet Command which allows handling bets for a racehorse with a number and an amount.
 */
public class BetCommand implements Command {
    private final int horseNumber;
    private final int amount;

    public BetCommand(int horseNumber, int amount) {
        this.horseNumber = horseNumber;
        this.amount = amount;
    }

    @Override
    public void execute(AutomatedTellerMachine atm) {
        atm.processBet(horseNumber, amount);
    }
}
