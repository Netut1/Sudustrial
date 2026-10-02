package com.netut.sudustrial.client.datagen.concrete;

import com.netut.sudustrial.client.datagen.concrete.generator.*;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import org.jspecify.annotations.NonNull;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ConcreteProvider implements DataProvider {

    private final List<DataProvider> providers;

    public ConcreteProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        this.providers = List.of(
                new ConcreteRecipeGenerator(output, registries),
                new ConcreteAdvancementGenerator(output, registries),
                new ConcreteLootGenerator(output, registries),
                new ConcreteModelProvider(output)
        );
    }

    @Override
    public @NonNull CompletableFuture<?> run(@NonNull CachedOutput output) {
        CompletableFuture<?>[] futures = providers.stream()
                .map(provider -> provider.run(output))
                .toArray(CompletableFuture[]::new);

        return CompletableFuture.allOf(futures);
    }

    @Override
    public @NonNull String getName() {
        return "ConcreteProvider";
    }

    private static class ConcreteModelProvider extends FabricModelProvider {

        public ConcreteModelProvider(FabricPackOutput output) {
            super(output);
        }

        @Override
        public void generateBlockStateModels(@NonNull BlockModelGenerators generator) {
            ConcreteBlockModelGenerator.generate(generator);
            ConcreteItemGenerator.generate(generator);
        }

        @Override
        public void generateItemModels(@NonNull ItemModelGenerators generator) {
        }

        @Override
        public @NonNull String getName() {
            return "ConcreteModelProvider";
        }
    }
}
