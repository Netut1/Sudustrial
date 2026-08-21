package com.netut.sudustrial.item.tnt.nuclear;

import net.minecraft.util.StringRepresentable;
import org.jspecify.annotations.NonNull;

public enum CoreItemType implements StringRepresentable {
    ELEMENTAL_CORE("elemental_core"),
    SPAWNER_CORE("spawner_core"),
    EFFECT_CORE_ALPHA("effect_core_alpha"),
    EFFECT_CORE_OMEGA("effect_core_omega"),
    MINING_SPHERE_CORE("mining_sphere_core"),
    MINING_COLUMN_CORE("mining_column_core"),
    STANDARD_CORE("standard_core"),
    MASSIVE_CORE("massive_core");

    private final String name;

    CoreItemType(String name) {
        this.name = name;
    }

    @Override
    @NonNull
    public String getSerializedName() {
        return this.name;
    }
}
