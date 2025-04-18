package com.vikingcloud.horsetrack;

import com.vikingcloud.horsetrack.model.CashInventory;
import com.vikingcloud.horsetrack.model.Horse;
import com.vikingcloud.horsetrack.service.HorseFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.vikingcloud.horsetrack.constants.DenominationConstants.AVAILABLE_DENOMINATIONS;

/**
 * Handles the core ATM (Automated Teller Machine) logic, including displaying current state, get horse by number, processing bets, restocking, setting the winner horse, and initializing the list of horses.
 */
public class AutomatedTellerMachine {
    private final CashInventory inventory = new CashInventory();
    private List<Horse> horses = new ArrayList<>();

    public AutomatedTellerMachine() {
        initializeHorses();
    }

    public void displayState() {
        inventory.displayInventory();
        System.out.println("Horses:");
        for (Horse horse : horses) {
            String result = horse.isWinner() ? "won" : "lost";
            System.out.println(horse.getNumber() + "," + horse.getName() + "," + horse.getOdds() + "," + result);
        }
    }

    public Horse getHorseByNumber(int number) {
        for (Horse horse : horses) {
            if (horse.getNumber() == number) {
                return horse;
            }
        }
        return null;
    }

    public void processBet(int horseNumber, int amount) {
        Horse horse = getHorseByNumber(horseNumber);

        // This should be controlled at the Command Parser level, but we should handle it here also (probably).
        if (horse == null) {
            System.out.println("Invalid Horse Number: " + horseNumber);
            displayState();
            return;
        }

        // This should be controlled at the Command Parser level, but we should handle it here also (probably).
        if (amount <= 0) {
            System.out.println("Invalid Bet: " + amount);
            displayState();
            return;
        }

        if (!horse.isWinner()) {
            System.out.println("No Payout: " + horse.getName());
            displayState();
            return;
        }

        int payout = horse.getOdds() * amount;

        Map<Integer, Integer> billsToDispense = inventory.getBillsForPayout(payout);
        if (billsToDispense == null) {
            System.out.println("Insufficient Funds: $" + payout);
            displayState();
            return;
        }

        inventory.dispenseBills(billsToDispense);

        System.out.println("Payout: " + horse.getName() + ",$" + payout);

        System.out.println("Dispensing:");
        Arrays.stream(AVAILABLE_DENOMINATIONS)
                .sorted()
                .forEach(denomination -> {
                    int count = billsToDispense.getOrDefault(denomination, 0);
                    System.out.println("$" + denomination + "," + count);
                });

        displayState();
    }

    public void restock() {
        inventory.restock();
        displayState();
    }

    public void setWinningHorse(int number) {
        boolean found = false;
        for (Horse horse : horses) {
            if (horse.getNumber() == number) {
                horse.setWinner(true);
                found = true;
            } else {
                horse.setWinner(false);
            }
        }

        if (!found) {
            System.out.println("Invalid Horse Number: " + number);
            return;
        }

        displayState();
    }

    private void initializeHorses() {
        horses = HorseFactory.createInitialHorseList();

        /*
        Upon application startup, the initial inventory and horse lists would look like this:

        Inventory:
        ...
        Horses:
        1,That Darn Gray Cat,5,won
        2,Fort Utopia,10,lost
        3,Count Sheep,9,lost
        4,Ms Traitour,4,lost
        5,Real Princess,3,lost6,Pa Kettle,5,lost
        7,Gin Stinger,6,lost

        The first horse, That Darn Gray Cat, with odds of 5, by default starts as the one who won.
         */

        // This sets the default winner to horse #1
        setWinningHorse(1);
    }
}
