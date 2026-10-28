package com.netut.sudustrial.client.datagen.core;

import com.netut.sudustrial.client.datagen.core.block.BlockDefinition;
import com.netut.sudustrial.client.datagen.core.block.BlockModelGenerator;
import com.netut.sudustrial.client.datagen.core.block.BlockSet;
import com.netut.sudustrial.client.datagen.core.block.BlockSetRecipes;
import com.netut.sudustrial.client.datagen.core.block.BlockVariant;
import com.netut.sudustrial.client.datagen.core.item.ItemDefinition;
import com.netut.sudustrial.client.datagen.core.item.ItemModelGenerator;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;

import java.util.List;

public abstract class DefinitionFeature implements DatagenFeature {
    protected List<BlockDefinition> blocks() {
        return List.of();
    }

    protected List<BlockSet> blockSets() {
        return List.of();
    }

    protected List<ItemDefinition> items() {
        return List.of();
    }

    @Override
    public void blockModels(BlockModelGenerators generator) {
        for (BlockDefinition definition : blocks()) {
            BlockModelGenerator.generate(generator, definition);
        }
        for (BlockSet set : blockSets()) {
            for (BlockVariant variant : set.variants()) {
                BlockModelGenerator.generate(generator, set.definition(variant));
            }
        }
    }

    @Override
    public void itemModels(ItemModelGenerators generator) {
        for (ItemDefinition definition : items()) {
            ItemModelGenerator.generate(generator, definition);
        }
    }

    @Override
    public void recipes(RecipeProvider provider, RecipeOutput output) {
        for (BlockSet set : blockSets()) {
            BlockSetRecipes.generate(provider, output, set);
        }
    }

    @Override
    public void loot(LootSink loot) {
        for (BlockDefinition definition : blocks()) {
            if (definition.hasItem()) {
                loot.dropSelf(definition.block());
            }
        }
        for (BlockSet set : blockSets()) {
            for (BlockVariant variant : set.variants()) {
                loot.dropSelf(set.block(variant));
            }
        }
    }
}
