package com.netut.sudustrial.client.datagen.feature.tnt;

import com.netut.sudustrial.client.datagen.core.block.BlockDefinition;
import com.netut.sudustrial.client.datagen.feature.DefinitionFeature;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;

import java.util.List;
import java.util.function.Consumer;

public final class TntFeature extends DefinitionFeature {
    @Override
    protected List<BlockDefinition> blocks() {
        return TntBlocks.definitions(TntCatalog.load());
    }

    @Override
    public void recipes(RecipeProvider provider, RecipeOutput output) {
        RecipeGenerator.generate(provider, output, TntCatalog.load());
    }

    @Override
    public void advancements(Consumer<AdvancementHolder> consumer) {
        TntStages.generate(consumer, TntCatalog.load());
    }
}
