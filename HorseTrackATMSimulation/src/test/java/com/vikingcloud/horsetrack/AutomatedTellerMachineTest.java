package com.vikingcloud.horsetrack;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AutomatedTellerMachineTest {
    @Test
    void processBet_horseNull() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        AutomatedTellerMachine atm = new AutomatedTellerMachine();
        atm.processBet(99, 1000);
        String output = outContent.toString();
        assertTrue(output.contains("Invalid Horse Number: 99"));
    }

    @Test
    void processBet_betAmountNegative() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        AutomatedTellerMachine atm = new AutomatedTellerMachine();
        atm.processBet(1, -34);
        String output = outContent.toString();
        assertTrue(output.contains("Invalid Bet: -34"));
    }

    @Test
    void processBet_horseNotWinner() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        AutomatedTellerMachine atm = new AutomatedTellerMachine();
        atm.processBet(3, 160);
        String output = outContent.toString();
        assertTrue(output.contains("No Payout: Count Sheep"));
    }

    @Test
    void processBet_insufficientFunds() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        AutomatedTellerMachine atm = new AutomatedTellerMachine();
        atm.processBet(1, 5000);
        String output = outContent.toString();
        assertTrue(output.contains("Insufficient Funds: $25000"));
    }

    @Test
    void setWinningHorse_invalidHorseNumber() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        AutomatedTellerMachine atm = new AutomatedTellerMachine();
        atm.setWinningHorse(52);
        String output = outContent.toString();
        assertTrue(output.contains("Invalid Horse Number: 52"));
    }
}
