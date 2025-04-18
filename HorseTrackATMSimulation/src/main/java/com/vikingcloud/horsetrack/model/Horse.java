package com.vikingcloud.horsetrack.model;

/**
 * Represents a racehorse with a number, name, odds, and winner state.
 */
public class Horse {
    private final int number;
    private final String name;
    private final int odds;
    private boolean isWinner = false;

    public Horse(int number, String name, int odds) {
        this.number = number;
        this.name = name;
        this.odds = odds;
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public int getOdds() {
        return odds;
    }

    public boolean isWinner() {
        return isWinner;
    }

    public void setWinner(boolean winner) {
        this.isWinner = winner;
    }
}
