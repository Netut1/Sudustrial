package com.netut.sudustrial.tnt.type.block;

import com.netut.sudustrial.tnt.action.explosion.Explodes;
import com.netut.sudustrial.tnt.block.TntVariant;
import com.netut.sudustrial.tnt.entity.CustomTntEntity;
import net.minecraft.server.level.ServerLevel;
import org.jspecify.annotations.NonNull;

public enum TntType implements TntVariant {
    STANDARD_TIER_2("standart_tnt_tier_2", TntTiers.standardPower(2)),
    STANDARD_TIER_3("standart_tnt_tier_3", TntTiers.standardPower(3)),
    STANDARD_TIER_4("standart_tnt_tier_4", TntTiers.standardPower(4)),
    STANDARD_TIER_5("standart_tnt_tier_5", TntTiers.standardPower(5)),
    STANDARD_TIER_6("standart_tnt_tier_6", TntTiers.standardPower(6)),
    STANDARD_TIER_7("standart_tnt_tier_7", TntTiers.standardPower(7)),
    STANDARD_TIER_8("standart_tnt_tier_8", TntTiers.standardPower(8)),
    STANDARD_TIER_9("standart_tnt_tier_9", TntTiers.standardPower(9)),
    STANDARD_TIER_10("standart_tnt_tier_10", TntTiers.standardPower(10)),

    MASSIVE_TIER_1("massive_tnt_tier_1", TntTiers.massivePower(1)),
    MASSIVE_TIER_2("massive_tnt_tier_2", TntTiers.massivePower(2)),
    MASSIVE_TIER_3("massive_tnt_tier_3", TntTiers.massivePower(3)),
    MASSIVE_TIER_4("massive_tnt_tier_4", TntTiers.massivePower(4)),
    MASSIVE_TIER_5("massive_tnt_tier_5", TntTiers.massivePower(5)),
    MASSIVE_TIER_6("massive_tnt_tier_6", TntTiers.massivePower(6)),
    MASSIVE_TIER_7("massive_tnt_tier_7", TntTiers.massivePower(7)),
    MASSIVE_TIER_8("massive_tnt_tier_8", TntTiers.massivePower(8)),
    MASSIVE_TIER_9("massive_tnt_tier_9", TntTiers.massivePower(9)),
    MASSIVE_TIER_10("massive_tnt_tier_10", TntTiers.massivePower(10)),
    
    PARTY("party_tnt", TntTiers.PARTY_POWER);

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
    public void explode(ServerLevel level, CustomTntEntity tnt) {
        Explodes.standardExplode(level, tnt, this);
    }

    @Override
    @NonNull
    public String getSerializedName() {
        return this.name;
    }
}
