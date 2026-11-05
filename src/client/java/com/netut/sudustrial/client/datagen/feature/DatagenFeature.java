package com.netut.sudustrial.client.datagen.feature;

import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;

import java.util.function.Consumer;

public interface DatagenFeature {
    default void blockModels(BlockModelGenerators generator) {}

    default void itemModels(ItemModelGenerators generator) {}

    default void recipes(RecipeProvider provider, RecipeOutput output) {}

    default void loot(LootSink loot) {}

    default void advancements(Consumer<AdvancementHolder> consumer) {}

    interface LootSink {
        void dropSelf(Block block);

        void add(Block block, LootTable.Builder builder);
    }
}
