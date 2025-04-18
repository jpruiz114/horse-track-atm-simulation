package com.vikingcloud.horsetrack.command;

import com.vikingcloud.horsetrack.AutomatedTellerMachine;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class InvalidBetCommandTest {
    @Test
    void execute_printsInvalidBetMessageAndDisplaysState() {
        AutomatedTellerMachine atm = new AutomatedTellerMachine();
        InvalidBetCommand cmd = new InvalidBetCommand("10.25");

        // Capture System.out
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        try {
            cmd.execute(atm);
        } finally {
            System.setOut(originalOut); // Restore System.out
        }

        String output = outContent.toString();

        // Validate the error message
        assertTrue(output.contains("Invalid Bet: 10.25"));
    }
}
