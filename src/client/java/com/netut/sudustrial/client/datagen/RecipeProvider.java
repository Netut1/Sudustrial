package com.netut.sudustrial.client.datagen;

import com.netut.sudustrial.client.datagen.feature.DatagenFeature;
import com.netut.sudustrial.client.datagen.feature.Features;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeOutput;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class RecipeProvider extends FabricRecipeProvider {
    public RecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected net.minecraft.data.recipes.@NonNull RecipeProvider createRecipeProvider(HolderLookup.@NonNull Provider registries, @NonNull RecipeOutput output) {
        return new Provider(registries, output);
    }

    @Override
    public @NonNull String getName() {
        return "RecipeProvider";
    }

    private static class Provider extends net.minecraft.data.recipes.RecipeProvider {
        private Provider(HolderLookup.Provider registries, RecipeOutput output) {
            super(registries, output);
        }

        @Override
        public void buildRecipes() {
            for (DatagenFeature feature : Features.ALL) {
                feature.recipes(this, output);
            }
        }
    }
}
