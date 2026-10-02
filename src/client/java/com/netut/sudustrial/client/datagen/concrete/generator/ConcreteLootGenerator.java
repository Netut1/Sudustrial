package com.netut.sudustrial.client.datagen.concrete.generator;

import com.netut.sudustrial.register.block.concrete.ModConcreteBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.level.block.Block;
import org.jspecify.annotations.NonNull;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class ConcreteLootGenerator extends FabricBlockLootSubProvider {

    public ConcreteLootGenerator(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    public void generate() {
        generateSelfDrops(ModConcreteBlocks.CONCRETE_STAIR);
        generateSelfDrops(ModConcreteBlocks.CONCRETE_SLAB);
        generateSelfDrops(ModConcreteBlocks.CONCRETE_POWDER_STAIR);
        generateSelfDrops(ModConcreteBlocks.CONCRETE_POWDER_SLAB);
    }

    private void generateSelfDrops(Map<?, Block> blocks) {
        for (Block block : blocks.values()) {
            dropSelf(block);
        }
    }

    @Override
    public @NonNull String getName() {return "ConcreteLootGenerator";}
}
