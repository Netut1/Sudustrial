package com.netut.sudustrial.client.datagen.feature.tnt.nuclear;

import com.netut.sudustrial.client.datagen.core.DatagenFeature;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;

public final class NuclearFeature implements DatagenFeature {
    @Override
    public void blockModels(BlockModelGenerators generator) {
        NuclearModelGenerator.generate(generator);
    }

    @Override
    public void itemModels(ItemModelGenerators generator) {
        CoreModelGenerator.generate(generator);
    }

    @Override
    public void recipes(RecipeProvider provider, RecipeOutput output) {
        CoreRecipeGenerator.generate(provider, output);
        NuclearRecipeGenerator.generate(provider, output);
    }

    @Override
    public void loot(LootSink loot) {
        NuclearLootGenerator.generate(loot);
    }
}
