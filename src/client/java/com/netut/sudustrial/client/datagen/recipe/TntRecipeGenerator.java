package com.netut.sudustrial.client.datagen.recipe;

import net.minecraft.advancements.triggers.Criterion;
import net.minecraft.advancements.triggers.InventoryChangeTrigger;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

public final class TntRecipeGenerator {

    private static final String MOD_ID = "sudustrial:";

    private TntRecipeGenerator() {}

    private static Criterion<?> has(Item item) {
        return InventoryChangeTrigger.TriggerInstance.hasItems(item);
    }

    public static void generate(RecipeProvider provider, RecipeOutput output) {
        generateStandard(provider, output);
        generateFamilies(provider, output);
        generateParty(provider, output);
    }

    private static void generateStandard(RecipeProvider provider, RecipeOutput output) {
        for (int tier = 2; tier <= 10; tier++) {
            String resultId = MOD_ID + "standard_tnt_tier_" + tier;

            provider.shaped(
                            RecipeCategory.REDSTONE,
                            item(resultId),
                            1
                    )
                    .group("tnt")
                    .pattern("###")
                    .pattern("###")
                    .pattern("###")
                    .define('#', Items.TNT)
                    .unlockedBy("has_tnt", has(Items.TNT))
                    .save(output);
        }
    }

    private static void generateFamilies(RecipeProvider provider, RecipeOutput output) {
        Family[] families = {
                new Family("bee", "minecraft:honeycomb"),
                new Family("blindness", "minecraft:ink_sac"),
                new Family("chicken", "minecraft:chicken"),
                new Family("cow", "minecraft:beef"),
                new Family("creeper", "minecraft:gunpowder"),
                new Family("earth", "minecraft:dirt"),
                new Family("electric", "minecraft:redstone"),
                new Family("ender", "minecraft:ender_pearl"),
                new Family("fire_resistance", "minecraft:magma_cream"),
                new Family("fire", "minecraft:fire_charge"),
                new Family("harm", "minecraft:glowstone_dust"),
                new Family("healing", "minecraft:glistering_melon_slice"),
                new Family("ice", "minecraft:ice"),
                new Family("invisibility", "minecraft:golden_carrot"),
                new Family("jump_boost", "minecraft:rabbit_foot"),
                new Family("levitation", "minecraft:feather"),
                new Family("massive", "minecraft:gold_block"),
                new Family("mining_column", "minecraft:gold_ingot"),
                new Family("mining_sphere", "minecraft:iron_ingot"),
                new Family("night_vision", "minecraft:golden_apple"),
                new Family("ocean", "minecraft:prismarine_shard"),
                new Family("pig", "minecraft:porkchop"),
                new Family("piglin_brute", "minecraft:raw_gold"),
                new Family("poison", "minecraft:spider_eye"),
                new Family("regeneration", "minecraft:ghast_tear"),
                new Family("sculk", "minecraft:sculk"),
                new Family("sheep", "minecraft:white_wool"),
                new Family("shulker", "minecraft:shulker_shell"),
                new Family("slow_falling", "minecraft:phantom_membrane"),
                new Family("slowness", "minecraft:string"),
                new Family("speed", "minecraft:sugar"),
                new Family("strength", "minecraft:blaze_powder"),
                new Family("swamp", "minecraft:slime_ball"),
                new Family("turtle_master", "minecraft:turtle_helmet"),
                new Family("water_breathing", "minecraft:pufferfish"),
                new Family("weakness", "minecraft:bone"),
                new Family("wither", "minecraft:wither_skeleton_skull"),
                new Family("wood", "minecraft:oak_log"),
                new Family("zombie", "minecraft:rotten_flesh")
        };

        for (Family family : families) {
            generateFamily(provider, output, family);
        }
    }

    private static void generateFamily(RecipeProvider provider, RecipeOutput output, Family family) {
        generateTierOne(provider, output, family);
        generateHigherTiers(provider, output, family);
    }

    private static void generateTierOne(RecipeProvider provider, RecipeOutput output, Family family) {
        String resultId = MOD_ID + family.id() + "_tnt_tier_1";

        provider.shaped(
                        RecipeCategory.REDSTONE,
                        item(resultId),
                        1
                )
                .group(family.id() + "_tnt")
                .pattern("###")
                .pattern("#&#")
                .pattern("###")
                .define('#', item(family.ingredient()))
                .define('&', Items.TNT)
                .unlockedBy(
                        "has_" + family.id() + "_ingredient",
                        has(item(family.ingredient()))
                )
                .save(output);
    }

    private static void generateHigherTiers(RecipeProvider provider, RecipeOutput output, Family family) {
        for (int tier = 2; tier <= 10; tier++) {
            String previousId = MOD_ID + family.id() + "_tnt_tier_" + (tier - 1);
            String resultId = MOD_ID + family.id() + "_tnt_tier_" + tier;

            provider.shaped(
                            RecipeCategory.REDSTONE,
                            item(resultId),
                            1
                    )
                    .group(family.id() + "_tnt")
                    .pattern("###")
                    .pattern("###")
                    .pattern("###")
                    .define('#', item(previousId))
                    .unlockedBy(
                            "has_" + family.id() + "_tier_" + (tier - 1),
                            has(item(previousId))
                    )
                    .save(output);
        }
    }

    private static void generateParty(RecipeProvider provider, RecipeOutput output) {
        provider.shaped(
                        RecipeCategory.REDSTONE,
                        item(MOD_ID + "party_tnt"),
                        1
                )
                .group("party_tnt")
                .pattern("###")
                .pattern("#&#")
                .pattern("###")
                .define('#', Items.EGG)
                .define('&', Items.TNT)
                .unlockedBy("has_egg", has(Items.EGG))
                .save(output);
    }

    private static Item item(String id) {
        Identifier identifier = Identifier.tryParse(id);

        if (identifier == null) {
            throw new IllegalArgumentException("Invalid item id: " + id);
        }

        return BuiltInRegistries.ITEM.get(identifier)
                .orElseThrow(() ->
                        new IllegalStateException(
                                "Unknown item for datagen recipe: " + id
                        )
                )
                .value();
    }

    private record Family(String id, String ingredient) { }
}
