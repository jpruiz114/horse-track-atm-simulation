package com.vikingcloud.horsetrack.command;

import com.vikingcloud.horsetrack.AutomatedTellerMachine;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BetCommandTest {
    @Test
    void betCommand_winner_dispensesPayout() {
        AutomatedTellerMachine atm = new AutomatedTellerMachine();
        atm.setWinningHorse(1);

        BetCommand cmd = new BetCommand(1, 10); // odds 5 → payout $50

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        cmd.execute(atm);

        String output = out.toString();
        assertTrue(output.contains("Payout: That Darn Gray Cat,$50"));
        assertTrue(output.contains("Dispensing:"));
    }
}
