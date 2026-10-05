package com.netut.sudustrial.client.datagen;

import com.netut.sudustrial.client.datagen.texture_block.concrete.generator.ConcreteAdvancementGenerator;
import com.netut.sudustrial.client.datagen.texture_block.concrete.generator.ConcreteBlockModelGenerator;
import com.netut.sudustrial.client.datagen.texture_block.concrete.generator.ConcreteLootGenerator;
import com.netut.sudustrial.client.datagen.texture_block.concrete.generator.ConcreteRecipeGenerator;
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

public class TextureBlockProvider implements DataProvider {

    private final List<DataProvider> providers;

    public TextureBlockProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        this.providers = List.of(
                new ConcreteRecipeGenerator(output, registries),
                new ConcreteAdvancementGenerator(output, registries),
                new ConcreteLootGenerator(output, registries),
                new ConcreteModelProvider(output)
        );
    }

    @Override
    public @NonNull CompletableFuture<?> run(@NonNull CachedOutput output) {
        CompletableFuture<?>[] futures =
                providers.stream()
                        .map(provider -> provider.run(output))
                        .toArray(CompletableFuture[]::new);

        return CompletableFuture.allOf(futures);
    }

    @Override
    public @NonNull String getName() {return "TextureBlockProvider";}

    private static class ConcreteModelProvider extends FabricModelProvider {

        public ConcreteModelProvider(FabricPackOutput output) {
            super(output);
        }

        @Override
        public void generateBlockStateModels(@NonNull BlockModelGenerators generator) {
            ConcreteBlockModelGenerator.generate(generator);
        }

        @Override
        public void generateItemModels(@NonNull ItemModelGenerators generator) {}

        @Override
        public @NonNull String getName() {return "ConcreteModelProvider";}
    }
}