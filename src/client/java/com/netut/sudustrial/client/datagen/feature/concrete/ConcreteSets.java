package com.netut.sudustrial.client.datagen.feature.concrete;

import com.netut.sudustrial.action.GetVanillaColorfulBlocks;
import com.netut.sudustrial.client.datagen.core.Registered;
import com.netut.sudustrial.client.datagen.core.block.BlockSet;
import com.netut.sudustrial.register.block.decorative.concrete.RegisterConcreteBlocks;
import net.minecraft.world.item.DyeColor;

import java.util.ArrayList;
import java.util.List;

final class ConcreteSets {
    private ConcreteSets() {}

    static List<BlockSet> all() {
        List<BlockSet> sets = new ArrayList<>();

        for (DyeColor color : DyeColor.values()) {
            sets.add(BlockSet.of(
                    GetVanillaColorfulBlocks.getVanillaConcrete(color),
                    Registered.require(RegisterConcreteBlocks.CONCRETE_STAIR, color),
                    Registered.require(RegisterConcreteBlocks.CONCRETE_SLAB, color)));

            sets.add(BlockSet.of(
                    GetVanillaColorfulBlocks.getVanillaConcretePowder(color),
                    Registered.require(RegisterConcreteBlocks.CONCRETE_POWDER_STAIR, color),
                    Registered.require(RegisterConcreteBlocks.CONCRETE_POWDER_SLAB, color)));
        }

        return List.copyOf(sets);
    }
}
