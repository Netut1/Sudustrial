package com.netut.sudustrial.client.datagen.recipe.po_2;

import com.netut.sudustrial.register.block.texture.po_2.RegisterPO_2Fence;
import net.minecraft.advancements.triggers.InventoryChangeTrigger;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Items;

public final class Po2RecipeGenerator {

    private Po2RecipeGenerator() {}

    public static void generate(RecipeProvider provider, RecipeOutput output) {
        provider.shaped(
                        RecipeCategory.BUILDING_BLOCKS,
                        RegisterPO_2Fence.PO_2_PANEL_ITEM,
                        1
                )
                .pattern("###")
                .pattern("#I#")
                .pattern("###")
                .define('#', Items.COBBLESTONE)
                .define('I', Items.IRON_INGOT)
                .unlockedBy(
                        "has_cobblestone",
                        InventoryChangeTrigger.TriggerInstance.hasItems(Items.COBBLESTONE))
                .save(output);
    }
}
