package com.vikingcloud.horsetrack.command;

import com.vikingcloud.horsetrack.AutomatedTellerMachine;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RestockCommandTest {
    @Test
    void restockCommand_resetsInventoryToFull() {
        AutomatedTellerMachine atm = new AutomatedTellerMachine();

        // Use up some of the inventory
        atm.processBet(1, 10); // simulate some payout
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        // Execute the restock
        RestockCommand cmd = new RestockCommand();
        cmd.execute(atm);

        // Check output to confirm inventory is back to 10s
        String result = out.toString();
        assertTrue(result.contains("Inventory:"));
        assertTrue(result.contains("$100,10"));
        assertTrue(result.contains("$1,10"));
    }
}
