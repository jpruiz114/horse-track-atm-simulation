package com.vikingcloud.horsetrack.command;

import com.vikingcloud.horsetrack.AutomatedTellerMachine;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class InvalidHorseNumberCommandTest {
    @Test
    void execute_printsInvalidHorseNumberAndDisplaysState() {
        AutomatedTellerMachine atm = new AutomatedTellerMachine();
        InvalidHorseNumberCommand cmd = new InvalidHorseNumberCommand(99);

        // Capture System.out
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        try {
            cmd.execute(atm);
        } finally {
            System.setOut(originalOut); // restore original output
        }

        String output = outContent.toString();

        // Check for error message
        assertTrue(output.contains("Invalid Horse Number: 99"));
    }
}
