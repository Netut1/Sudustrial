package com.netut.sudustrial.client.datagen.loot.tnt;

import com.netut.sudustrial.register.block.nuclear_block.RegisterNuclearCore;
import net.minecraft.world.level.block.Block;

import java.util.function.Consumer;

public final class NuclearLootGenerator {

    private NuclearLootGenerator() {}

    public static void generate(Consumer<Block> dropSelf) {
        dropSelf.accept(RegisterNuclearCore.NUCLEAR_CORE_BLOCK);
    }
}
