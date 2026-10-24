package com.netut.sudustrial.client.datagen.feature.tnt;

import com.netut.sudustrial.client.datagen.core.DatagenFeature;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;

public final class TntFeature implements DatagenFeature {
    @Override
    public void blockModels(BlockModelGenerators generator) {
        ModelGenerator.generate(generator, TntCatalog.load());
    }

    @Override
    public void recipes(RecipeProvider provider, RecipeOutput output) {
        RecipeGenerator.generate(provider, output, TntCatalog.load());
    }

    @Override
    public void loot(LootSink loot) {
        LootGenerator.generate(loot, TntCatalog.load());
    }
}
