package com.netut.sudustrial.tnt.type.block;

public final class TntTiers {
    private TntTiers() {}

    public static final int MAX_TIER = 10;

    private static final float[] STANDARD_POWER = {4, 8, 16, 24, 32, 80, 100, 130, 160, 200};

    private static final float MASSIVE_STEP = 50.0f;
    private static final float ELEMENTAL_STEP = 2.0f;

    private static final float[] EFFECT_RADIUS    = {5, 5, 10, 10, 15, 15, 20, 20, 25, 30};
    private static final int[]   EFFECT_DURATION  = {20, 30, 40, 60, 80, 100, 130, 160, 190, 250}; // ticks
    private static final int[]   EFFECT_AMPLIFIER = {0, 1, 1, 2, 2, 3, 3, 4, 4, 5};               // 0 = I

    private static final int[]    SPAWN_COUNT  = {3, 6, 10, 20, 25, 30, 50, 70, 90, 150};
    private static final double[] SPAWN_RADIUS = {3, 3, 3, 5, 5, 5, 10, 10, 10, 20};

    private static final int[] SPHERE_RADIUS = {4, 7, 10, 13, 16, 19, 22, 25, 28, 32};
    private static final int[] COLUMN_RADIUS = {2, 4, 6, 8, 10, 12, 15, 18, 21, 24}; // 2 = область 5x5

    public static final float NUCLEAR_POWER = 1000.0f;
    public static final float PARTY_POWER = 0.0f;

    public static float standardPower(int tier)   { return STANDARD_POWER[index(tier)]; }
    public static float massivePower(int tier)    { return MASSIVE_STEP * (index(tier) + 1); }
    public static float elementalPower(int tier)  { return ELEMENTAL_STEP * (index(tier) + 1); }

    public static float effectRadius(int tier)    { return EFFECT_RADIUS[index(tier)]; }
    public static int effectDuration(int tier)    { return EFFECT_DURATION[index(tier)]; }
    public static int effectAmplifier(int tier)   { return EFFECT_AMPLIFIER[index(tier)]; }

    public static int spawnCount(int tier)        { return SPAWN_COUNT[index(tier)]; }
    public static double spawnRadius(int tier)    { return SPAWN_RADIUS[index(tier)]; }

    public static int sphereRadius(int tier)      { return SPHERE_RADIUS[index(tier)]; }
    public static int columnRadius(int tier)      { return COLUMN_RADIUS[index(tier)]; }

    private static int index(int tier) {
        if (tier < 1 || tier > MAX_TIER) {
            throw new IllegalArgumentException("Tier must be 1.." + MAX_TIER + ", got " + tier);
        }
        return tier - 1;
    }
}