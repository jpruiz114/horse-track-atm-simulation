package com.vikingcloud.horsetrack.command;

import com.vikingcloud.horsetrack.AutomatedTellerMachine;

/**
 * Interface which defines the pattern for all the commands of the system.
 */
public interface Command {
    void execute(AutomatedTellerMachine atm);
}
