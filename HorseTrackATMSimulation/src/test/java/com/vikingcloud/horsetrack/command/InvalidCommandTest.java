package com.vikingcloud.horsetrack.command;

import com.vikingcloud.horsetrack.AutomatedTellerMachine;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class InvalidCommandTest {
    @Test
    void invalidCommand_printsInvalidMessage() {
        AutomatedTellerMachine atm = new AutomatedTellerMachine();
        InvalidCommand cmd = new InvalidCommand("xyz");

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        cmd.execute(atm);

        String result = out.toString();
        assertTrue(result.contains("Invalid Command: xyz"));
    }
}
