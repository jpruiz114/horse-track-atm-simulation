package com.vikingcloud.horsetrack.service;

import com.vikingcloud.horsetrack.model.Horse;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for creating and initializing the default list of horses for the ATM.
 */
public class HorseFactory {
    private HorseFactory() {
        // Prevent instantiation
    }

    public static List<Horse> createInitialHorseList() {
        List<Horse> horses = new ArrayList<>();
        horses.add(new Horse(1, "That Darn Gray Cat", 5));
        horses.add(new Horse(2, "Fort Utopia", 10));
        horses.add(new Horse(3, "Count Sheep", 9));
        horses.add(new Horse(4, "Ms Traitour", 4));
        horses.add(new Horse(5, "Real Princess", 3));
        horses.add(new Horse(6, "Pa Kettle", 5));
        horses.add(new Horse(7, "Gin Stinger", 6));
        return horses;
    }
}
