package com.netut.sudustrial.client.datagen;

import com.netut.sudustrial.client.datagen.recipe.*;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class SudustrialRecipeProvider extends FabricRecipeProvider {

    public SudustrialRecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected @NonNull RecipeProvider createRecipeProvider(HolderLookup.@NonNull Provider registries, @NonNull RecipeOutput output) {
        return new Provider(registries, output);
    }

    @Override
    public @NonNull String getName() { return "SudustrialRecipeProvider"; }

    private static class Provider extends RecipeProvider {
        private Provider(HolderLookup.Provider registries, RecipeOutput output) {
            super(registries, output);
        }

        @Override
        public void buildRecipes() {
            TntRecipeGenerator.generate(this, output);
            PotionCauldronRecipeGenerator.generate(this, output);
            CoreItemRecipeGenerator.generate(this,  output);
            NuclearRecipeGenerator.generate(this, output);
        }
    }
}
