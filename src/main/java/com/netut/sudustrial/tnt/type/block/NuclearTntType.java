package com.netut.sudustrial.tnt.type.block;

import com.netut.sudustrial.tnt.action.explosion.Explodes;
import com.netut.sudustrial.tnt.block.TntVariant;
import com.netut.sudustrial.tnt.entity.CustomTntEntity;
import net.minecraft.server.level.ServerLevel;
import org.jspecify.annotations.NonNull;

public enum NuclearTntType implements TntVariant {
    NUCLEAR("nuclear_tnt", TntTiers.NUCLEAR_POWER);

    private final String name;
    private final float explosionPower;

    NuclearTntType(String name, float explosionPower) {
        this.name = name;
        this.explosionPower = explosionPower;
    }

    public float getExplosionPower() {
        return explosionPower;
    }

    @Override
    public void explode(ServerLevel level, CustomTntEntity tnt) {
        Explodes.nuclearExplode(level, tnt, this);
    }

    @Override
    @NonNull
    public String getSerializedName() {
        return this.name;
    }
}
