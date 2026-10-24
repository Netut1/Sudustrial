package com.netut.sudustrial.client.datagen.feature.po_2;

import com.netut.sudustrial.client.datagen.core.DatagenFeature;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;

import java.util.function.Consumer;

public final class Po2Feature implements DatagenFeature {
    @Override
    public void blockModels(BlockModelGenerators generator) {
        ModelGenerator.generate(generator);
    }

    @Override
    public void recipes(RecipeProvider provider, RecipeOutput output) {
        RecipeGenerator.generate(provider, output);
    }

    @Override
    public void advancements(Consumer<AdvancementHolder> consumer) {
        AdvancementGenerator.generate(consumer);
    }
}
