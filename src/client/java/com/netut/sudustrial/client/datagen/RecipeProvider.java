package com.netut.sudustrial.client.datagen;

import com.netut.sudustrial.client.datagen.feature.DatagenFeature;
import com.netut.sudustrial.client.datagen.feature.Features;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.advancements.Advancement;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.crafting.Recipe;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class RecipeProvider extends FabricRecipeProvider {
    public RecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected net.minecraft.data.recipes.@NonNull RecipeProvider createRecipeProvider(HolderLookup.@NonNull Provider registries,
            @NonNull BootstrapContext<Recipe<?>> recipes, @NonNull BootstrapContext<Advancement> advancements) {
        return new Provider(recipes, advancements);
    }

    @Override
    public @NonNull String getName() {
        return "RecipeProvider";
    }

    private static class Provider extends net.minecraft.data.recipes.RecipeProvider {
        private Provider(BootstrapContext<Recipe<?>> recipes, BootstrapContext<Advancement> advancements) {
            super(recipes, advancements);
        }

        @Override
        public void buildRecipes() {
            for (DatagenFeature feature : Features.ALL) {
                feature.recipes(this, output);
            }
        }
    }
}
