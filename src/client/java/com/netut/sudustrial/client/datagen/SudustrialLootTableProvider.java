package com.netut.sudustrial.client.datagen;

import com.netut.sudustrial.client.datagen.loot.NuclearLootGenerator;
import com.netut.sudustrial.client.datagen.loot.PotionCauldronLootGenerator;
import com.netut.sudustrial.client.datagen.loot.TntLootGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;
import net.minecraft.core.HolderLookup;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class SudustrialLootTableProvider extends FabricBlockLootSubProvider {

    public SudustrialLootTableProvider(
            FabricPackOutput output,
            CompletableFuture<HolderLookup.Provider> registries
    ) {
        super(output, registries);
    }

    @Override
    public void generate() {
        TntLootGenerator.generate(this::dropSelf);
        NuclearLootGenerator.generate(this::dropSelf);
        PotionCauldronLootGenerator.generate(this::add);
    }

    @Override
    public @NonNull String getName() {
        return "SudustrialLootTableProvider";
    }
}
