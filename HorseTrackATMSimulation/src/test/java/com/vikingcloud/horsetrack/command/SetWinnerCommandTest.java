package com.vikingcloud.horsetrack.command;

import com.vikingcloud.horsetrack.AutomatedTellerMachine;
import com.vikingcloud.horsetrack.model.Horse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SetWinnerCommandTest {
    @Test
    void setWinnerCommand_marksCorrectHorse() {
        AutomatedTellerMachine atm = new AutomatedTellerMachine();
        SetWinnerCommand cmd = new SetWinnerCommand(3);

        cmd.execute(atm);

        Horse horse = atm.getHorseByNumber(3);
        assertTrue(horse.isWinner());

        for (int i = 1; i <= 7; i++) {
            if (i != 3) {
                assertFalse(atm.getHorseByNumber(i).isWinner());
            }
        }
    }
}
