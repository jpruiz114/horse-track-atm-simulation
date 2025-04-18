package com.vikingcloud.horsetrack;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainTest {
    @Test
    void mainLoop_handlesBasicCommands() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Main.main(null);
        String output = out.toString();
        assertTrue(output.contains("1,That Darn Gray Cat,5,won"));
    }

    @Test
    void main_emptyInputNullCommand() {
        String input = "\n";

        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        System.setIn(in);                      // override input
        System.setOut(new PrintStream(out));   // capture output

        Main.main(null);
        String output = out.toString();

        assertTrue(output.contains("Inventory:"));
    }

    @Test
    void main_processesCommandsCorrectly() {
        String input = String.join(
                System.lineSeparator(),
                "r",        // restock
                "w 1",      // set winner
                "1 10",     // bet
                "q"         // quit
        ) + System.lineSeparator();

        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        System.setIn(in);                      // override input
        System.setOut(new PrintStream(out));   // capture output

        Main.main(null);
        String output = out.toString();

        assertTrue(output.contains("Inventory:"));
        assertTrue(output.contains("Payout: That Darn Gray Cat,$50"));
        assertTrue(output.contains("$100,10"));
    }
}
