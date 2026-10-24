package com.netut.sudustrial.client.datagen;

import com.netut.sudustrial.client.datagen.core.DatagenFeature;
import com.netut.sudustrial.client.datagen.core.Features;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class SudustrialLootTableProvider extends FabricBlockLootSubProvider {
    public SudustrialLootTableProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    public void generate() {
        DatagenFeature.LootSink sink = new DatagenFeature.LootSink() {
            @Override
            public void dropSelf(Block block) {
                SudustrialLootTableProvider.this.dropSelf(block);
            }

            @Override
            public void add(Block block, LootTable.Builder builder) {
                SudustrialLootTableProvider.this.add(block, builder);
            }
        };

        for (DatagenFeature feature : Features.ALL) {
            feature.loot(sink);
        }
    }

    @Override
    public @NonNull String getName() {
        return "SudustrialLootTableProvider";
    }
}
