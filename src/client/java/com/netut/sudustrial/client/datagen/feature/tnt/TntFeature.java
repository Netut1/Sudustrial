package com.netut.sudustrial.client.datagen.feature.tnt;

import com.netut.sudustrial.client.datagen.core.DefinitionFeature;
import com.netut.sudustrial.client.datagen.core.block.BlockDefinition;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;

import java.util.List;
import java.util.function.Consumer;

public final class TntFeature extends DefinitionFeature {
    private TntCatalog catalog;

    @Override
    protected List<BlockDefinition> blocks() {
        return TntBlocks.definitions(catalog());
    }

    @Override
    public void recipes(RecipeProvider provider, RecipeOutput output) {
        RecipeGenerator.generate(provider, output, catalog());
    }

    @Override
    public void advancements(Consumer<AdvancementHolder> consumer) {
        TntStages.generate(consumer, catalog());
    }

    private TntCatalog catalog() {
        if (catalog == null) {
            catalog = TntCatalog.load();
        }
        return catalog;
    }
}
