package com.vikingcloud.horsetrack.model;

import com.vikingcloud.horsetrack.constants.DenominationConstants;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.vikingcloud.horsetrack.constants.DenominationConstants.AVAILABLE_DENOMINATIONS;
import static com.vikingcloud.horsetrack.constants.DenominationConstants.DEFAULT_RESTOCK_COUNTS;

/**
 * Represents the ATM's bill inventory. Manages dispensing bills, displaying the inventory, getting bills for payout, and restocking.
 */
public class CashInventory {
    private final Map<Integer, Integer> bills = new LinkedHashMap<>();

    public CashInventory() {
        restock();
    }

    public void dispenseBills(Map<Integer, Integer> dispensed) {
        for (Map.Entry<Integer, Integer> entry : dispensed.entrySet()) {
            int denomination = entry.getKey();
            int current = bills.getOrDefault(denomination, 0);
            int count = entry.getValue();
            bills.put(denomination, current - count);
        }
    }

    public void displayInventory() {
        System.out.println("Inventory:");
        Arrays.stream(DenominationConstants.AVAILABLE_DENOMINATIONS)
                .sorted() // ascending order
                .forEach(denomination -> {
                    int count = bills.getOrDefault(denomination, 0);
                    System.out.println("$" + denomination + "," + count);
                });
    }

    public Map<Integer, Integer> getBillsForPayout(int amount) {
        Map<Integer, Integer> toDispense = new LinkedHashMap<>();
        int remaining = amount;

        for (int denomination : AVAILABLE_DENOMINATIONS) {
            int available = bills.getOrDefault(denomination, 0);
            int needed = remaining / denomination;
            int canUse = Math.min(needed, available);

            if (canUse > 0) {
                toDispense.put(denomination, canUse);
                remaining -= canUse * denomination;
            }
        }

        return (remaining == 0) ? toDispense : null;
    }

    public void restock() {
        bills.clear();
        for (int denomination : AVAILABLE_DENOMINATIONS) {
            int inventory = DEFAULT_RESTOCK_COUNTS.getOrDefault(denomination, 0);
            bills.put(denomination, inventory);
        }
    }
}
