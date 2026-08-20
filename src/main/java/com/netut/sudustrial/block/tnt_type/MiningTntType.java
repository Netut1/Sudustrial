package com.netut.sudustrial.block.tnt_type;

import net.minecraft.util.StringRepresentable;
import org.jspecify.annotations.NonNull;

public enum MiningTntType implements StringRepresentable {
    SPHERE_TIER_1("mining_sphere_tnt_tier_1", 4, false),
    SPHERE_TIER_2("mining_sphere_tnt_tier_2", 7, false),
    SPHERE_TIER_3("mining_sphere_tnt_tier_3", 10, false),
    SPHERE_TIER_4("mining_sphere_tnt_tier_4", 13, false),
    SPHERE_TIER_5("mining_sphere_tnt_tier_5", 16, false),
    SPHERE_TIER_6("mining_sphere_tnt_tier_6", 19, false),
    SPHERE_TIER_7("mining_sphere_tnt_tier_7", 22, false),
    SPHERE_TIER_8("mining_sphere_tnt_tier_8", 25, false),
    SPHERE_TIER_9("mining_sphere_tnt_tier_9", 28, false),
    SPHERE_TIER_10("mining_sphere_tnt_tier_10", 32, false),

    COLUMN_TIER_1("mining_column_tnt_tier_1", 2, true), // radius=2 дает область 5x5 (от -2 до +2)
    COLUMN_TIER_2("mining_column_tnt_tier_2", 4, true),
    COLUMN_TIER_3("mining_column_tnt_tier_3", 6, true),
    COLUMN_TIER_4("mining_column_tnt_tier_4", 8, true),
    COLUMN_TIER_5("mining_column_tnt_tier_5", 10, true),
    COLUMN_TIER_6("mining_column_tnt_tier_6", 12, true),
    COLUMN_TIER_7("mining_column_tnt_tier_7", 15, true),
    COLUMN_TIER_8("mining_column_tnt_tier_8", 18, true),
    COLUMN_TIER_9("mining_column_tnt_tier_9", 21, true),
    COLUMN_TIER_10("mining_column_tnt_tier_10", 24, true);

    private final String name;
    private final int radius;
    private final boolean isColumn;

    MiningTntType(String name, int radius, boolean isColumn) {
        this.name = name;
        this.radius = radius;
        this.isColumn = isColumn;
    }

    public int getRadius() {
        return radius;
    }

    public boolean isColumn() {
        return isColumn;
    }

    @Override
    @NonNull
    public String getSerializedName() {
        return this.name;
    }
}
