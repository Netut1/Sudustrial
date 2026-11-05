package com.netut.sudustrial.client.datagen.feature.nuclear;

import com.netut.sudustrial.client.datagen.core.recipe.RecipeVariants;
import com.netut.sudustrial.client.datagen.feature.tnt.TntCatalog;
import com.netut.sudustrial.tnt.type.block.EffectTntType;
import com.netut.sudustrial.tnt.type.block.ElementalTntType;
import com.netut.sudustrial.tnt.type.block.MiningTntType;
import com.netut.sudustrial.tnt.type.block.NuclearTntType;
import com.netut.sudustrial.tnt.type.block.SpawnerTntType;
import com.netut.sudustrial.tnt.type.block.TntType;
import com.netut.sudustrial.tnt.type.item.CoreItemType;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;

import static com.netut.sudustrial.client.datagen.core.DatagenUtils.has;

final class RecipeGenerator {
    private static final String CORE_GROUP = "atomic_tnt_cores";

    private RecipeGenerator() {}

    static void generate(RecipeProvider provider, RecipeOutput output, TntCatalog tnt) {
        RecipeVariants recipes = new RecipeVariants(provider, output, RecipeCategory.REDSTONE);

        cores(provider, output, recipes, tnt);
        nuclearTnt(recipes, tnt);
    }

    private static void cores(RecipeProvider provider, RecipeOutput output, RecipeVariants recipes, TntCatalog tnt) {
        recipes.grid(CORE_GROUP, Cores.of(CoreItemType.ELEMENTAL_CORE),
                tnt.item(ElementalTntType.FIRE_TIER_10),
                tnt.item(ElementalTntType.WOOD_TIER_10),
                tnt.item(ElementalTntType.SWAMP_TIER_10),
                tnt.item(ElementalTntType.SCULK_TIER_10),
                tnt.item(ElementalTntType.EARTH_TIER_10),
                tnt.item(ElementalTntType.ELECTRIC_TIER_10),
                tnt.item(ElementalTntType.ENDER_TIER_10),
                tnt.item(ElementalTntType.OCEAN_TIER_10),
                tnt.item(ElementalTntType.ICE_TIER_10));

        recipes.grid(CORE_GROUP, Cores.of(CoreItemType.SPAWNER_CORE),
                tnt.item(SpawnerTntType.ZOMBIE_TIER_10),
                tnt.item(SpawnerTntType.CREEPER_TIER_10),
                tnt.item(SpawnerTntType.SHEEP_TIER_10),
                tnt.item(SpawnerTntType.SHULKER_TIER_10),
                tnt.item(SpawnerTntType.PIGLIN_BRUTE_TIER_10),
                tnt.item(SpawnerTntType.PIG_TIER_10),
                tnt.item(SpawnerTntType.COW_TIER_10),
                tnt.item(SpawnerTntType.CHICKEN_TIER_10),
                tnt.item(SpawnerTntType.BEE_TIER_10));

        recipes.grid(CORE_GROUP, Cores.of(CoreItemType.EFFECT_CORE_ALPHA),
                tnt.item(EffectTntType.JUMP_BOOST_TIER_10),
                tnt.item(EffectTntType.FIRE_RESISTANCE_TIER_10),
                tnt.item(EffectTntType.SPEED_TIER_10),
                tnt.item(EffectTntType.SLOWNESS_TIER_10),
                tnt.item(EffectTntType.WATER_BREATHING_TIER_10),
                tnt.item(EffectTntType.POISON_TIER_10),
                tnt.item(EffectTntType.REGENERATION_TIER_10),
                tnt.item(EffectTntType.STRENGTH_TIER_10),
                tnt.item(EffectTntType.WEAKNESS_TIER_10));

        recipes.grid(CORE_GROUP, Cores.of(CoreItemType.EFFECT_CORE_OMEGA),
                tnt.item(EffectTntType.INVISIBILITY_TIER_10),
                tnt.item(EffectTntType.TURTLE_MASTER_TIER_10),
                tnt.item(EffectTntType.HEALING_TIER_10),
                tnt.item(EffectTntType.HARM_TIER_10),
                tnt.item(EffectTntType.SLOW_FALLING_TIER_10),
                tnt.item(EffectTntType.NIGHT_VISION_TIER_10),
                tnt.item(EffectTntType.BLINDNESS_TIER_10),
                tnt.item(EffectTntType.LEVITATION_TIER_10),
                tnt.item(EffectTntType.WITHER_TIER_10));

        smelt(provider, output, tnt.item(MiningTntType.SPHERE_TIER_10), CoreItemType.MINING_SPHERE_CORE);
        smelt(provider, output, tnt.item(MiningTntType.COLUMN_TIER_10), CoreItemType.MINING_COLUMN_CORE);
        smelt(provider, output, tnt.item(TntType.STANDARD_TIER_10), CoreItemType.STANDARD_CORE);
        smelt(provider, output, tnt.item(TntType.MASSIVE_TIER_10), CoreItemType.MASSIVE_CORE);
    }

    private static void nuclearTnt(RecipeVariants recipes, TntCatalog tnt) {
        recipes.gridUnlockedBy(null,
                tnt.item(NuclearTntType.NUCLEAR),
                Items.HEART_OF_THE_SEA,
                Cores.of(CoreItemType.ELEMENTAL_CORE),
                Cores.of(CoreItemType.STANDARD_CORE),
                Cores.of(CoreItemType.SPAWNER_CORE),
                Cores.of(CoreItemType.MINING_SPHERE_CORE),
                Items.HEART_OF_THE_SEA,
                Cores.of(CoreItemType.MINING_COLUMN_CORE),
                Cores.of(CoreItemType.EFFECT_CORE_ALPHA),
                Cores.of(CoreItemType.MASSIVE_CORE),
                Cores.of(CoreItemType.EFFECT_CORE_OMEGA));
    }

    private static void smelt(RecipeProvider provider, RecipeOutput output, Item ingredient, CoreItemType result) {
        SimpleCookingRecipeBuilder.smelting(
                        Ingredient.of(ingredient),
                        RecipeCategory.MISC,
                        CookingBookCategory.MISC,
                        Cores.of(result),
                        5.0f,
                        400
                )
                .group(CORE_GROUP)
                .unlockedBy("has_ingredient", has(ingredient))
                .save(output);
    }
}
