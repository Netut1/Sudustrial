package com.netut.sudustrial.tnt.type.block;

import com.netut.sudustrial.tnt.action.explosion.Explodes;
import com.netut.sudustrial.tnt.block.TntVariant;
import com.netut.sudustrial.tnt.entity.CustomTntEntity;
import net.minecraft.server.level.ServerLevel;
import org.jspecify.annotations.NonNull;

public enum MiningTntType implements TntVariant {
    SPHERE_TIER_1("mining_sphere_tnt_tier_1", 1, false),
    SPHERE_TIER_2("mining_sphere_tnt_tier_2", 2, false),
    SPHERE_TIER_3("mining_sphere_tnt_tier_3", 3, false),
    SPHERE_TIER_4("mining_sphere_tnt_tier_4", 4, false),
    SPHERE_TIER_5("mining_sphere_tnt_tier_5", 5, false),
    SPHERE_TIER_6("mining_sphere_tnt_tier_6", 6, false),
    SPHERE_TIER_7("mining_sphere_tnt_tier_7", 7, false),
    SPHERE_TIER_8("mining_sphere_tnt_tier_8", 8, false),
    SPHERE_TIER_9("mining_sphere_tnt_tier_9", 9, false),
    SPHERE_TIER_10("mining_sphere_tnt_tier_10", 10, false),

    COLUMN_TIER_1("mining_column_tnt_tier_1", 1, true), // radius = 2 дает область 5x5 (от -2 до +2)
    COLUMN_TIER_2("mining_column_tnt_tier_2", 2, true),
    COLUMN_TIER_3("mining_column_tnt_tier_3", 3, true),
    COLUMN_TIER_4("mining_column_tnt_tier_4", 4, true),
    COLUMN_TIER_5("mining_column_tnt_tier_5", 5, true),
    COLUMN_TIER_6("mining_column_tnt_tier_6", 6, true),
    COLUMN_TIER_7("mining_column_tnt_tier_7", 7, true),
    COLUMN_TIER_8("mining_column_tnt_tier_8", 8, true),
    COLUMN_TIER_9("mining_column_tnt_tier_9", 9, true),
    COLUMN_TIER_10("mining_column_tnt_tier_10", 10, true);

    private final String name;
    private final int radius;
    private final boolean isColumn;

    MiningTntType(String name, int tier, boolean isColumn) {
        this.name = name;
        this.radius = isColumn ? TntTiers.columnRadius(tier) : TntTiers.sphereRadius(tier);
        this.isColumn = isColumn;
    }

    public int getRadius() {
        return radius;
    }

    public boolean isColumn() {
        return isColumn;
    }

    @Override
    public void explode(ServerLevel level, CustomTntEntity tnt) {
        Explodes.miningExplode(level, tnt, this);
    }

    @Override
    @NonNull
    public String getSerializedName() {
        return this.name;
    }
}
