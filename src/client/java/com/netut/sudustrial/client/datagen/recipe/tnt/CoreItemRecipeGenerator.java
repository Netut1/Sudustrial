package com.netut.sudustrial.client.datagen.recipe.tnt;

import net.minecraft.advancements.triggers.Criterion;
import net.minecraft.advancements.triggers.InventoryChangeTrigger;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;

public final class CoreItemRecipeGenerator {

    private static final String CORE_GROUP = "atomic_tnt_cores";

    private CoreItemRecipeGenerator() {}

    private static Criterion<?> has(Item item) {
        return InventoryChangeTrigger.TriggerInstance.hasItems(item);
    }

    public static void generate(RecipeProvider provider, RecipeOutput output) {
        generateElementalCore(provider, output);
        generateSpawnerCore(provider, output);
        generateEffectCoreAlpha(provider, output);
        generateEffectCoreOmega(provider, output);

        generateSmelting(provider, output,
                "sudustrial:mining_sphere_tnt_tier_10",
                "sudustrial:mining_sphere_core");

        generateSmelting(provider, output,
                "sudustrial:mining_column_tnt_tier_10",
                "sudustrial:mining_column_core");

        generateSmelting(provider, output,
                "sudustrial:standard_tnt_tier_10",
                "sudustrial:standard_core");

        generateSmelting(provider, output,
                "sudustrial:massive_tnt_tier_10",
                "sudustrial:massive_core");
    }

    private static void generateElementalCore(RecipeProvider provider, RecipeOutput output) {
        provider.shaped(
                        RecipeCategory.REDSTONE,
                        item("sudustrial:elemental_core"),
                        1
                )
                .group(CORE_GROUP)
                .pattern("ABC")
                .pattern("DEF")
                .pattern("GHI")
                .define('A', item("sudustrial:fire_tnt_tier_10"))
                .define('B', item("sudustrial:wood_tnt_tier_10"))
                .define('C', item("sudustrial:swamp_tnt_tier_10"))
                .define('D', item("sudustrial:sculk_tnt_tier_10"))
                .define('E', item("sudustrial:earth_tnt_tier_10"))
                .define('F', item("sudustrial:electric_tnt_tier_10"))
                .define('G', item("sudustrial:ender_tnt_tier_10"))
                .define('H', item("sudustrial:ocean_tnt_tier_10"))
                .define('I', item("sudustrial:ice_tnt_tier_10"))
                .unlockedBy(
                        "has_fire_tnt_tier_10",
                        has(item("sudustrial:fire_tnt_tier_10"))
                )
                .save(output);
    }

    private static void generateSpawnerCore(RecipeProvider provider, RecipeOutput output) {
        provider.shaped(
                        RecipeCategory.REDSTONE,
                        item("sudustrial:spawner_core"),
                        1
                )
                .group(CORE_GROUP)
                .pattern("ABC")
                .pattern("DEF")
                .pattern("GHI")
                .define('A', item("sudustrial:zombie_tnt_tier_10"))
                .define('B', item("sudustrial:creeper_tnt_tier_10"))
                .define('C', item("sudustrial:sheep_tnt_tier_10"))
                .define('D', item("sudustrial:shulker_tnt_tier_10"))
                .define('E', item("sudustrial:piglin_brute_tnt_tier_10"))
                .define('F', item("sudustrial:pig_tnt_tier_10"))
                .define('G', item("sudustrial:cow_tnt_tier_10"))
                .define('H', item("sudustrial:chicken_tnt_tier_10"))
                .define('I', item("sudustrial:bee_tnt_tier_10"))
                .unlockedBy(
                        "has_zombie_tnt_tier_10",
                        has(item("sudustrial:zombie_tnt_tier_10"))
                )
                .save(output);
    }

    private static void generateEffectCoreAlpha(RecipeProvider provider, RecipeOutput output) {
        provider.shaped(
                        RecipeCategory.REDSTONE,
                        item("sudustrial:effect_core_alpha"),
                        1
                )
                .group(CORE_GROUP)
                .pattern("ABC")
                .pattern("DEF")
                .pattern("GHI")
                .define('A', item("sudustrial:jump_boost_tnt_tier_10"))
                .define('B', item("sudustrial:fire_resistance_tnt_tier_10"))
                .define('C', item("sudustrial:speed_tnt_tier_10"))
                .define('D', item("sudustrial:slowness_tnt_tier_10"))
                .define('E', item("sudustrial:water_breathing_tnt_tier_10"))
                .define('F', item("sudustrial:poison_tnt_tier_10"))
                .define('G', item("sudustrial:regeneration_tnt_tier_10"))
                .define('H', item("sudustrial:strength_tnt_tier_10"))
                .define('I', item("sudustrial:weakness_tnt_tier_10"))
                .unlockedBy(
                        "has_jump_boost_tnt_tier_10",
                        has(item("sudustrial:jump_boost_tnt_tier_10"))
                )
                .save(output);
    }

    private static void generateEffectCoreOmega(RecipeProvider provider, RecipeOutput output) {
        provider.shaped(
                        RecipeCategory.REDSTONE,
                        item("sudustrial:effect_core_omega"),
                        1
                )
                .group(CORE_GROUP)
                .pattern("ABC")
                .pattern("DEF")
                .pattern("GHI")
                .define('A', item("sudustrial:invisibility_tnt_tier_10"))
                .define('B', item("sudustrial:turtle_master_tnt_tier_10"))
                .define('C', item("sudustrial:healing_tnt_tier_10"))
                .define('D', item("sudustrial:harm_tnt_tier_10"))
                .define('E', item("sudustrial:slow_falling_tnt_tier_10"))
                .define('F', item("sudustrial:night_vision_tnt_tier_10"))
                .define('G', item("sudustrial:blindness_tnt_tier_10"))
                .define('H', item("sudustrial:levitation_tnt_tier_10"))
                .define('I', item("sudustrial:wither_tnt_tier_10"))
                .unlockedBy(
                        "has_invisibility_tnt_tier_10",
                        has(item("sudustrial:invisibility_tnt_tier_10"))
                )
                .save(output);
    }

    private static void generateSmelting(RecipeProvider provider, RecipeOutput output, String ingredientId, String resultId) {
        SimpleCookingRecipeBuilder.smelting(
                        Ingredient.of(item(ingredientId)),
                        RecipeCategory.MISC,
                        CookingBookCategory.MISC,
                        item(resultId),
                        5.0f,
                        400
                )
                .group(CORE_GROUP)
                .unlockedBy(
                        "has_ingredient",
                        has(item(ingredientId))
                )
                .save(output);
    }

    private static Item item(String id) {
        Identifier identifier = Identifier.tryParse(id);

        if (identifier == null) {throw new IllegalArgumentException("Invalid item id: " + id);}

        return BuiltInRegistries.ITEM.get(identifier)
                .orElseThrow(() -> new IllegalStateException("Unknown item for datagen recipe: " + id))
                .value();
    }
}
