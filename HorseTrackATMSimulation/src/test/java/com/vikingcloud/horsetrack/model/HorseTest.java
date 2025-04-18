package com.vikingcloud.horsetrack.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HorseTest {
    @Test
    void getNumber_returnsCorrectNumber() {
        Horse horse = new Horse(1, "Count Sheep", 5);
        assertEquals(1, horse.getNumber());
    }

    @Test
    void getName_returnsCorrectName() {
        Horse horse = new Horse(7, "Fort Utopia", 10);
        assertEquals("Fort Utopia", horse.getName());
    }

    @Test
    void getOdds_returnsCorrectOdds() {
        Horse horse = new Horse(10, "Real Princess", 25);
        assertEquals(25, horse.getOdds());
    }

    @Test
    void horseStartsAsNotWinner() {
        Horse horse = new Horse(1, "That Darn Gray Cat", 3);
        assertFalse(horse.isWinner());
    }

    @Test
    void canMarkHorseAsWinner() {
        Horse horse = new Horse(1, "Real Princess", 25);
        horse.setWinner(true);
        assertTrue(horse.isWinner());
    }
}
