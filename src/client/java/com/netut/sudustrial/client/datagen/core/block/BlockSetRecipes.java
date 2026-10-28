package com.netut.sudustrial.client.datagen.core.block;

import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Item;

import static com.netut.sudustrial.client.datagen.core.DatagenUtils.has;

public final class BlockSetRecipes {
    private BlockSetRecipes() {}

    public static void generate(RecipeProvider provider, RecipeOutput output, BlockSet set) {
        Item source = set.source().asItem();

        for (BlockVariant variant : set.variants()) {
            Item result = set.block(variant).asItem();

            var builder = provider.shaped(RecipeCategory.BUILDING_BLOCKS, result, variant.craftCount());
            for (String row : variant.pattern()) {
                builder.pattern(row);
            }
            builder.define('#', source)
                    .unlockedBy("has_" + set.name(), has(source))
                    .save(output);

            provider.stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, result, source, variant.stonecutCount());
        }
    }
}
