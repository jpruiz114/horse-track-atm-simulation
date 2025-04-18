package com.vikingcloud.horsetrack.command;

import com.vikingcloud.horsetrack.AutomatedTellerMachine;

/**
 * Implementation of the Quit Command which allows terminating the execution of the application.
 */
public class QuitCommand implements Command {
    @Override
    public void execute(AutomatedTellerMachine atm) {
        // No-op: signal to main loop to exit
    }
}
