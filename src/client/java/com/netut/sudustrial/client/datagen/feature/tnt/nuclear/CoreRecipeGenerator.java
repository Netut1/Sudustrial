package com.netut.sudustrial.client.datagen.feature.tnt.nuclear;

import com.netut.sudustrial.client.datagen.core.Registered;
import com.netut.sudustrial.client.datagen.core.common.RecipeVariants;
import com.netut.sudustrial.register.block.tnt.RegisterEffectTnt;
import com.netut.sudustrial.register.block.tnt.RegisterElementalTnt;
import com.netut.sudustrial.register.block.tnt.RegisterMiningTnt;
import com.netut.sudustrial.register.block.tnt.RegisterSpawnerTnt;
import com.netut.sudustrial.register.block.tnt.RegisterTnt;
import com.netut.sudustrial.tnt.type.block.EffectTntType;
import com.netut.sudustrial.tnt.type.block.ElementalTntType;
import com.netut.sudustrial.tnt.type.block.MiningTntType;
import com.netut.sudustrial.tnt.type.block.SpawnerTntType;
import com.netut.sudustrial.tnt.type.block.TntType;
import com.netut.sudustrial.tnt.type.item.CoreItemType;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;

import static com.netut.sudustrial.client.datagen.core.DatagenUtils.has;

final class CoreRecipeGenerator {
    private static final String CORE_GROUP = "atomic_tnt_cores";

    private CoreRecipeGenerator() {}

    static void generate(RecipeProvider provider, RecipeOutput output) {
        RecipeVariants recipes = new RecipeVariants(provider, output, RecipeCategory.REDSTONE);

        recipes.grid(CORE_GROUP, Cores.of(CoreItemType.ELEMENTAL_CORE),
                elemental(ElementalTntType.FIRE_TIER_10),
                elemental(ElementalTntType.WOOD_TIER_10),
                elemental(ElementalTntType.SWAMP_TIER_10),
                elemental(ElementalTntType.SCULK_TIER_10),
                elemental(ElementalTntType.EARTH_TIER_10),
                elemental(ElementalTntType.ELECTRIC_TIER_10),
                elemental(ElementalTntType.ENDER_TIER_10),
                elemental(ElementalTntType.OCEAN_TIER_10),
                elemental(ElementalTntType.ICE_TIER_10));

        recipes.grid(CORE_GROUP, Cores.of(CoreItemType.SPAWNER_CORE),
                spawner(SpawnerTntType.ZOMBIE_TIER_10),
                spawner(SpawnerTntType.CREEPER_TIER_10),
                spawner(SpawnerTntType.SHEEP_TIER_10),
                spawner(SpawnerTntType.SHULKER_TIER_10),
                spawner(SpawnerTntType.PIGLIN_BRUTE_TIER_10),
                spawner(SpawnerTntType.PIG_TIER_10),
                spawner(SpawnerTntType.COW_TIER_10),
                spawner(SpawnerTntType.CHICKEN_TIER_10),
                spawner(SpawnerTntType.BEE_TIER_10));

        recipes.grid(CORE_GROUP, Cores.of(CoreItemType.EFFECT_CORE_ALPHA),
                effect(EffectTntType.JUMP_BOOST_TIER_10),
                effect(EffectTntType.FIRE_RESISTANCE_TIER_10),
                effect(EffectTntType.SPEED_TIER_10),
                effect(EffectTntType.SLOWNESS_TIER_10),
                effect(EffectTntType.WATER_BREATHING_TIER_10),
                effect(EffectTntType.POISON_TIER_10),
                effect(EffectTntType.REGENERATION_TIER_10),
                effect(EffectTntType.STRENGTH_TIER_10),
                effect(EffectTntType.WEAKNESS_TIER_10));

        recipes.grid(CORE_GROUP, Cores.of(CoreItemType.EFFECT_CORE_OMEGA),
                effect(EffectTntType.INVISIBILITY_TIER_10),
                effect(EffectTntType.TURTLE_MASTER_TIER_10),
                effect(EffectTntType.HEALING_TIER_10),
                effect(EffectTntType.HARM_TIER_10),
                effect(EffectTntType.SLOW_FALLING_TIER_10),
                effect(EffectTntType.NIGHT_VISION_TIER_10),
                effect(EffectTntType.BLINDNESS_TIER_10),
                effect(EffectTntType.LEVITATION_TIER_10),
                effect(EffectTntType.WITHER_TIER_10));

        smelt(provider, output, mining(MiningTntType.SPHERE_TIER_10), CoreItemType.MINING_SPHERE_CORE);
        smelt(provider, output, mining(MiningTntType.COLUMN_TIER_10), CoreItemType.MINING_COLUMN_CORE);
        smelt(provider, output, standard(TntType.STANDARD_TIER_10), CoreItemType.STANDARD_CORE);
        smelt(provider, output, standard(TntType.MASSIVE_TIER_10), CoreItemType.MASSIVE_CORE);
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

    private static Item elemental(ElementalTntType type) {
        return Registered.require(RegisterElementalTnt.ELEMENTAL_TNT_ITEMS, type);
    }

    private static Item spawner(SpawnerTntType type) {
        return Registered.require(RegisterSpawnerTnt.SPAWNER_TNT_ITEMS, type);
    }

    private static Item effect(EffectTntType type) {
        return Registered.require(RegisterEffectTnt.EFFECT_TNT_ITEMS, type);
    }

    private static Item mining(MiningTntType type) {
        return Registered.require(RegisterMiningTnt.MINING_TNT_ITEMS, type);
    }

    private static Item standard(TntType type) {
        return Registered.require(RegisterTnt.TNT_ITEMS, type);
    }
}
