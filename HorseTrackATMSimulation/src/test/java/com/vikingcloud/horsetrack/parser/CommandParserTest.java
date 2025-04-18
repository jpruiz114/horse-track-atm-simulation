package com.vikingcloud.horsetrack.parser;

import com.vikingcloud.horsetrack.AutomatedTellerMachine;
import com.vikingcloud.horsetrack.command.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNull;

public class CommandParserTest {
    private AutomatedTellerMachine atm;

    @BeforeEach
    void setUp() {
        atm = new AutomatedTellerMachine();
    }

    @Test
    void parse_betCommand_valid() {
        Command command = CommandParser.parse("2 10", atm);
        assertInstanceOf(BetCommand.class, command);
    }

    @Test
    void parse_betCommand_invalidAmountFormat() {
        Command command = CommandParser.parse("2 10.25", atm);
        assertInstanceOf(InvalidBetCommand.class, command);

        command = CommandParser.parse("2 -34", atm);
        assertInstanceOf(InvalidBetCommand.class, command);
    }

    @Test
    void parse_invalidCommandSyntax() {
        Command command = CommandParser.parse("bad input", atm);
        assertInstanceOf(InvalidCommand.class, command);

        command = CommandParser.parse("w seahorse", atm);
        assertInstanceOf(InvalidCommand.class, command);

        command = CommandParser.parse("w", atm);
        assertInstanceOf(InvalidCommand.class, command);

        command = CommandParser.parse("this is wrong", atm);
        assertInstanceOf(InvalidCommand.class, command);
    }

    @Test
    void parse_betCommand_invalidHorseNumber() {
        Command command = CommandParser.parse("99 10", atm);
        assertInstanceOf(InvalidHorseNumberCommand.class, command);
    }

    @Test
    void parse_quitCommand() {
        Command command = CommandParser.parse("q", atm);
        assertInstanceOf(QuitCommand.class, command);
    }

    @Test
    void parse_restockCommand() {
        Command command = CommandParser.parse("r", atm);
        assertInstanceOf(RestockCommand.class, command);
    }

    @Test
    void parse_setWinnerCommand_validHorse() {
        Command command = CommandParser.parse("w 3", atm);
        assertInstanceOf(SetWinnerCommand.class, command);
    }

    @Test
    void parse_emptyInput_returnsNull() {
        Command command = CommandParser.parse(null, atm);
        assertNull(command);

        command = CommandParser.parse("   ", atm);
        assertNull(command);
    }
}
