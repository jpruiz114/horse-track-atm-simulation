package com.vikingcloud.horsetrack.parser;

import com.vikingcloud.horsetrack.AutomatedTellerMachine;
import com.vikingcloud.horsetrack.command.BetCommand;
import com.vikingcloud.horsetrack.command.Command;
import com.vikingcloud.horsetrack.command.InvalidBetCommand;
import com.vikingcloud.horsetrack.command.InvalidCommand;
import com.vikingcloud.horsetrack.command.InvalidHorseNumberCommand;
import com.vikingcloud.horsetrack.command.QuitCommand;
import com.vikingcloud.horsetrack.command.RestockCommand;
import com.vikingcloud.horsetrack.command.SetWinnerCommand;
import com.vikingcloud.horsetrack.model.Horse;

import static com.vikingcloud.horsetrack.constants.CommandConstants.*;

public class CommandParser {
    private CommandParser() {
        // Prevent instantiation
    }

    public static Command parse(String input, AutomatedTellerMachine atm) {
        if (input == null || input.trim().isEmpty()) {
            return null;
        }

        /*
        If the user enters any combination of whitespace characters between the command tokens, handle it.
        \\s = Any whitespace character (space, tab)
        + = one or more whitespace character
         */
        String[] tokens = input.trim().split("\\s+");
        String cmd = tokens[0].toLowerCase();

        /*
        How can we get invalid commands here?

        Command: x 5
        Output: Invalid Command: x 5

        Command: w two
        Output: Invalid Command: w two

        Command: 9 100
        Output: Invalid Horse Number: 9

        Command: 1 10.25
        Output: Invalid Bet: 10.25

        Command: 4 -5
        Output: Invalid Bet: -5

        Command: 4 ten
        Output: Invalid Bet: ten
         */

        if (cmd.equals(RESTOCK)) {
            return new RestockCommand();
        } else if (cmd.equals(QUIT)) {
            return new QuitCommand();
        } else if (cmd.equals(SET_WINNING_HORSE) && tokens.length == 2) {
            try {
                int horseNumber = Integer.parseInt(tokens[1]);
                return new SetWinnerCommand(horseNumber);
            } catch (NumberFormatException numberFormatException) {
                return new InvalidCommand(input);
            }
        } else if (tokens.length == 2) {
            String horseToken = tokens[0];
            String amountToken = tokens[1];

            try {
                int horseNumber = Integer.parseInt(horseToken);
                Horse horse = atm.getHorseByNumber(horseNumber);
                if (horse == null) {
                    return new InvalidHorseNumberCommand(horseNumber);
                }

                // amount may still be invalid (like 10.25)
                try {
                    int amount = Integer.parseInt(amountToken);
                    if (amount <= 0) {
                        return new InvalidBetCommand(amountToken);
                    }
                    return new BetCommand(horseNumber, amount);
                } catch (NumberFormatException e) {
                    return new InvalidBetCommand(amountToken);
                }
            } catch (NumberFormatException e) {
                return new InvalidCommand(input);
            }
        } else {
            return new InvalidCommand(input);
        }
    }
}
