package com.netut.sudustrial.block.tnt_type;

import net.minecraft.util.StringRepresentable;
import org.jspecify.annotations.NonNull;

public enum TntType implements StringRepresentable {
    STANDARD_TIER_2("standart_tnt_tier_2", 8.0f),
    STANDARD_TIER_3("standart_tnt_tier_3", 16.0f),
    STANDARD_TIER_4("standart_tnt_tier_4", 24.0f),
    STANDARD_TIER_5("standart_tnt_tier_5", 32.0f),
    // С Tier 6 переходим на сферу (power * 0.2F):
    STANDARD_TIER_6("standart_tnt_tier_6", 80.0f),
    STANDARD_TIER_7("standart_tnt_tier_7", 100.0f),
    STANDARD_TIER_8("standart_tnt_tier_8", 130.0f),
    STANDARD_TIER_9("standart_tnt_tier_9", 160.0f),
    STANDARD_TIER_10("standart_tnt_tier_10", 200.0f),
    // Обычные тнт массивного взрыва
    MASSIVE_TIER_1("massive_tnt_tier_1", 50.0f),
    MASSIVE_TIER_2("massive_tnt_tier_2", 100.0f),
    MASSIVE_TIER_3("massive_tnt_tier_3", 150.0f),
    MASSIVE_TIER_4("massive_tnt_tier_4", 200.0f),
    MASSIVE_TIER_5("massive_tnt_tier_5", 250.0f),
    MASSIVE_TIER_6("massive_tnt_tier_6", 300.0f),
    MASSIVE_TIER_7("massive_tnt_tier_7", 350.0f),
    MASSIVE_TIER_8("massive_tnt_tier_8", 400.0f),
    MASSIVE_TIER_9("massive_tnt_tier_9", 450.0f),
    MASSIVE_TIER_10("massive_tnt_tier_10", 500.0f),
    
    PARTY("party_tnt", 0.0f);

    private final String name;
    private final float explosionPower;

    TntType(String name, float explosionPower) {
        this.name = name;
        this.explosionPower = explosionPower;
    }

    public float getExplosionPower() {
        return explosionPower;
    }

    @Override
    @NonNull
    public String getSerializedName() {
        return this.name;
    }
}
