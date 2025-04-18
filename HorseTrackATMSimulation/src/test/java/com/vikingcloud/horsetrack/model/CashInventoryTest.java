package com.vikingcloud.horsetrack.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class CashInventoryTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    private CashInventory inventory;

    @BeforeEach
    void setUp() {
        inventory = new CashInventory();
        inventory.restock(); // ensure it's full before each test

        // Redirect System.out before each test
        System.setOut(new PrintStream(outContent));
    }

    @BeforeEach
    void tearDown() {
        // Restore System.out after test (optional if running isolated)
        System.setOut(originalOut);
    }

    @Test
    void displayInventory_printsCorrectFormat() {
        inventory.displayInventory();

        String expected = String.join(System.lineSeparator(),
                "Inventory:",
                "$1,10",
                "$5,10",
                "$10,10",
                "$20,10",
                "$100,10"
        ) + System.lineSeparator(); // Don't forget final newline

        assertEquals(expected, outContent.toString());
    }

    @Test
    void displayInventory_reflectsReducedBills() {
        Map<Integer, Integer> payout = inventory.getBillsForPayout(125);
        inventory.dispenseBills(payout);

        outContent.reset();
        inventory.displayInventory();

        String actual = outContent.toString();
        assertTrue(actual.contains("$100,9")); // 1x $100 used
        assertTrue(actual.contains("$20,9"));  // 1x $20 used
        assertTrue(actual.contains("$5,9"));   // 1x $5 used
    }

    @Test
    void restock_makesExactChangePossibleForKnownAmount() {
        // After restock, a payout of $186 should be possible:
        // 1x100 + 4x20 + 1x5 + 1x1 = $186
        inventory.restock();
        Map<Integer, Integer> payout = inventory.getBillsForPayout(186);
        assertNotNull(payout);

        assertEquals(1, payout.get(100));
        assertEquals(4, payout.get(20));
        assertEquals(1, payout.get(5));
        assertEquals(1, payout.get(1));
    }

    @Test
    void dispenseBills_makesExactSamePayoutUnavailableIfInventoryIsDepleted() {
        inventory.restock();

        // Payout 1000 using all 10x $100 bills
        Map<Integer, Integer> payout = inventory.getBillsForPayout(1000);
        assertNotNull(payout);
        assertEquals(10, payout.get(100)); // all $100 bills used
        inventory.dispenseBills(payout);

        // Try the exact same payout again
        Map<Integer, Integer> secondPayout = inventory.getBillsForPayout(1000);

        // There is not enough money left to pay another $1000
        assertNull(secondPayout);
    }

    @Test
    void dispenseBills_makesExtraPayoutAvailable() {
        inventory.restock();

        // Payout 1000 using all 10x $100 bills
        Map<Integer, Integer> payout = inventory.getBillsForPayout(1000);
        assertNotNull(payout);
        assertEquals(10, payout.get(100)); // all $100 bills used
        inventory.dispenseBills(payout);

        // Try $360 now (10x$20 + 10x$10 + 10x$5 + 10x$1) = $360
        Map<Integer, Integer> secondPayout = inventory.getBillsForPayout(360);

        // There is not enough money left to pay another $1000
        assertNotNull(secondPayout);
    }
}
