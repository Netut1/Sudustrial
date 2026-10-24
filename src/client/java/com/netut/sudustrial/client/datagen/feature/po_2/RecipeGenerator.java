package com.netut.sudustrial.client.datagen.feature.po_2;

import com.netut.sudustrial.register.block.decorative.po_2.RegisterPO_2Fence;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Items;

import static com.netut.sudustrial.client.datagen.util.DatagenUtils.has;

public class RecipeGenerator {
    private RecipeGenerator() {}

    static void generate(RecipeProvider provider, RecipeOutput output) {
        provider.shaped(RecipeCategory.BUILDING_BLOCKS, RegisterPO_2Fence.PO_2_PANEL_ITEM, 1)
                .pattern("###")
                .pattern("#I#")
                .pattern("###")
                .define('#', Items.COBBLESTONE)
                .define('I', Items.IRON_INGOT)
                .unlockedBy("has_cobblestone", has(Items.COBBLESTONE))
                .save(output);
    }
}
