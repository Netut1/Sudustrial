package com.netut.sudustrial.client.datagen.recipe.tnt;

import net.minecraft.advancements.triggers.Criterion;
import net.minecraft.advancements.triggers.InventoryChangeTrigger;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;

public final class NuclearRecipeGenerator {

    private NuclearRecipeGenerator() {}

    private static Criterion<?> has(Item item) {
        return InventoryChangeTrigger.TriggerInstance.hasItems(item);
    }

    public static void generate(RecipeProvider provider, RecipeOutput output) {
        provider.shaped(
                        RecipeCategory.REDSTONE,
                        item("sudustrial:nuclear_tnt"),
                        1
                )
                .pattern("ABC")
                .pattern("D#E")
                .pattern("FGH")
                .define('A', item("sudustrial:elemental_core"))
                .define('B', item("sudustrial:standard_core"))
                .define('C', item("sudustrial:spawner_core"))
                .define('D', item("sudustrial:mining_sphere_core"))
                .define('#', item("minecraft:heart_of_the_sea"))
                .define('E', item("sudustrial:mining_column_core"))
                .define('F', item("sudustrial:effect_core_alpha"))
                .define('G', item("sudustrial:massive_core"))
                .define('H', item("sudustrial:effect_core_omega"))
                .unlockedBy(
                        "has_standard_core",
                        has(item("sudustrial:standard_core"))
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
