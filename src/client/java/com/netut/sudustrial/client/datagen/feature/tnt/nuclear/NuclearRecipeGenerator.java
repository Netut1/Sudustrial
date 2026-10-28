package com.netut.sudustrial.client.datagen.feature.tnt.nuclear;

import com.netut.sudustrial.client.datagen.core.common.RecipeVariants;
import com.netut.sudustrial.client.datagen.core.Registered;
import com.netut.sudustrial.register.block.tnt.RegisterNuclearTnt;
import com.netut.sudustrial.tnt.type.block.NuclearTntType;
import com.netut.sudustrial.tnt.type.item.CoreItemType;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Items;

final class NuclearRecipeGenerator {
    private NuclearRecipeGenerator() {}

    static void generate(RecipeProvider provider, RecipeOutput output) {
        RecipeVariants recipes = new RecipeVariants(provider, output, RecipeCategory.REDSTONE);

        recipes.gridUnlockedBy(null,
                Registered.require(RegisterNuclearTnt.NUCLEAR_TNT_ITEMS, NuclearTntType.NUCLEAR),
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
}
