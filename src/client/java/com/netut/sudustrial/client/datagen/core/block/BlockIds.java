package com.netut.sudustrial.client.datagen.core.block;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;

public final class BlockIds {
    private BlockIds() {}

    public static String path(Block block) {
        return BuiltInRegistries.BLOCK.getKey(block).getPath();
    }
}
