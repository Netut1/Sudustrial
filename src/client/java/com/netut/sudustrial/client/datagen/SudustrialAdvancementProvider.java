package com.netut.sudustrial.client.datagen;

import com.netut.sudustrial.client.datagen.core.DatagenFeature;
import com.netut.sudustrial.client.datagen.core.Features;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.core.HolderLookup;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class SudustrialAdvancementProvider extends FabricAdvancementProvider {
    public SudustrialAdvancementProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    public void generateAdvancement(HolderLookup.@NonNull Provider registries, @NonNull Consumer<AdvancementHolder> consumer) {
        for (DatagenFeature feature : Features.ALL) {
            feature.advancements(consumer);
        }
    }

    @Override
    public @NonNull String getName() {
        return "SudustrialAdvancementProvider";
    }
}
