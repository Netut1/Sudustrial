package com.netut.sudustrial.client.datagen.feature.potion_cauldron;

import com.netut.sudustrial.register.block.potion_cauldron.RegisterPotionCauldron;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Items;

import static com.netut.sudustrial.client.datagen.util.DatagenUtils.has;

final class RecipeGenerator {
    private RecipeGenerator() {}

    static void generate(RecipeProvider provider, RecipeOutput output) {
        provider.shaped(RecipeCategory.MISC, RegisterPotionCauldron.POTION_CAULDRON_ITEM, 1)
                .pattern("@$@")
                .pattern("%#%")
                .pattern(" @ ")
                .define('#', Items.CAULDRON)
                .define('%', Items.LAPIS_LAZULI)
                .define('@', Items.MAGMA_CREAM)
                .define('$', Items.AMETHYST_SHARD)
                .unlockedBy("has_cauldron", has(Items.CAULDRON))
                .save(output);
    }
}
