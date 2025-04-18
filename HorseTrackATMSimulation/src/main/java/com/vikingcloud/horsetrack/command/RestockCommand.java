package com.vikingcloud.horsetrack.command;

import com.vikingcloud.horsetrack.AutomatedTellerMachine;

/**
 * Implementation of the Restock Command which allows the user to restock the cash inventory.
 */
public class RestockCommand implements Command {
    @Override
    public void execute(AutomatedTellerMachine atm) {
        atm.restock();
    }
}
