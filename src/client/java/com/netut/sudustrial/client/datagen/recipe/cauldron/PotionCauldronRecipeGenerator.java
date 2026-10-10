package com.netut.sudustrial.client.datagen.recipe.cauldron;

import net.minecraft.advancements.triggers.Criterion;
import net.minecraft.advancements.triggers.InventoryChangeTrigger;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;

public final class PotionCauldronRecipeGenerator {

    private PotionCauldronRecipeGenerator() {}

    private static Criterion<?> has(Item item) {
        return InventoryChangeTrigger.TriggerInstance.hasItems(item);
    }

    public static void generate(RecipeProvider provider, RecipeOutput output) {
        provider.shaped(
                        RecipeCategory.MISC,
                        item("sudustrial:potion_cauldron"),
                        1
                )
                .pattern("@$@")
                .pattern("%#%")
                .pattern(" @ ")
                .define('#', item("minecraft:cauldron"))
                .define('%', item("minecraft:lapis_lazuli"))
                .define('@', item("minecraft:magma_cream"))
                .define('$', item("minecraft:amethyst_shard"))
                .unlockedBy(
                        "has_cauldron",
                        has(item("minecraft:cauldron"))
                )
                .save(output);
    }

    private static Item item(String id) {
        Identifier identifier = Identifier.tryParse(id);

        if (identifier == null) {
            throw new IllegalArgumentException(
                    "Invalid item id: " + id
            );
        }

        return BuiltInRegistries.ITEM.get(identifier)
                .orElseThrow(() ->
                        new IllegalStateException(
                                "Unknown item for datagen recipe: " + id
                        )
                )
                .value();
    }
}
