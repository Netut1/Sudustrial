package com.netut.sudustrial.block.tnt.type;

import net.minecraft.util.StringRepresentable;
import org.jspecify.annotations.NonNull;

public enum NuclearTntType implements StringRepresentable {
    NUCLEAR("nuclear_tnt", 1000.0f);

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
    @NonNull
    public String getSerializedName() {
        return this.name;
    }
}
