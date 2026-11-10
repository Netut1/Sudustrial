package com.netut.sudustrial.client.datagen.core.block;

import com.netut.sudustrial.client.datagen.core.recipe.RecipeVariants;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Item;

public final class BlockSetRecipes {
    private BlockSetRecipes() {}

    public static void generate(RecipeProvider provider, RecipeOutput output, BlockSet set) {
        RecipeVariants recipes = new RecipeVariants(provider, output, RecipeCategory.BUILDING_BLOCKS);
        Item source = set.source().asItem();

        for (BlockVariant variant : set.variants()) {
            Item result = set.block(variant).asItem();

            recipes.shaped(null, result, variant.craftCount(), source, variant.pattern());
            recipes.stonecut(result, source, variant.stonecutCount());
        }
    }
}
