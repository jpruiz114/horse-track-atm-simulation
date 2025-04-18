package com.vikingcloud.horsetrack.command;

import com.vikingcloud.horsetrack.AutomatedTellerMachine;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuitCommandTest {
    @Test
    void quitCommand_doesNotChangeState() {
        AutomatedTellerMachine atm = new AutomatedTellerMachine();
        QuitCommand cmd = new QuitCommand();

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        cmd.execute(atm); // no-op in command itself

        assertEquals("", out.toString()); // no output expected
    }
}
