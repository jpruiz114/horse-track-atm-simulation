package com.vikingcloud.horsetrack.constants;

import java.util.Map;

/**
 * Holds constants for the denominations available and the amounts initially available.
 */
public final class DenominationConstants {
    public static final int DENOMINATION_1 = 1;
    public static final int DENOMINATION_5 = 5;
    public static final int DENOMINATION_10 = 10;
    public static final int DENOMINATION_20 = 20;
    public static final int DENOMINATION_100 = 100;

    public static final int[] AVAILABLE_DENOMINATIONS = {
        DENOMINATION_100, DENOMINATION_20, DENOMINATION_10, DENOMINATION_5, DENOMINATION_1
    };

    /**
     * This map provies flexibility for the denomination inventory.
     */
    public static final Map<Integer, Integer> DEFAULT_RESTOCK_COUNTS = Map.of(
        DENOMINATION_1, 10,
        DENOMINATION_5, 10,
        DENOMINATION_10, 10,
        DENOMINATION_20, 10,
        DENOMINATION_100, 10
    );

    private DenominationConstants() {
        // prevent instantiation
    }
}
