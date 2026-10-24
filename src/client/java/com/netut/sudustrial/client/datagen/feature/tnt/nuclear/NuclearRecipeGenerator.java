package com.netut.sudustrial.client.datagen.feature.tnt.nuclear;

import com.netut.sudustrial.client.datagen.core.Registered;
import com.netut.sudustrial.register.block.tnt.RegisterNuclearTnt;
import com.netut.sudustrial.tnt.type.block.NuclearTntType;
import com.netut.sudustrial.tnt.type.item.CoreItemType;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Items;

import static com.netut.sudustrial.client.datagen.util.DatagenUtils.has;

final class NuclearRecipeGenerator {
    private NuclearRecipeGenerator() {}

    static void generate(RecipeProvider provider, RecipeOutput output) {
        provider.shaped(
                        RecipeCategory.REDSTONE,
                        Registered.require(RegisterNuclearTnt.NUCLEAR_TNT_ITEMS, NuclearTntType.NUCLEAR),
                        1
                )
                .pattern("ABC")
                .pattern("D#E")
                .pattern("FGH")
                .define('A', Cores.of(CoreItemType.ELEMENTAL_CORE))
                .define('B', Cores.of(CoreItemType.STANDARD_CORE))
                .define('C', Cores.of(CoreItemType.SPAWNER_CORE))
                .define('D', Cores.of(CoreItemType.MINING_SPHERE_CORE))
                .define('#', Items.HEART_OF_THE_SEA)
                .define('E', Cores.of(CoreItemType.MINING_COLUMN_CORE))
                .define('F', Cores.of(CoreItemType.EFFECT_CORE_ALPHA))
                .define('G', Cores.of(CoreItemType.MASSIVE_CORE))
                .define('H', Cores.of(CoreItemType.EFFECT_CORE_OMEGA))
                .unlockedBy("has_standard_core", has(Cores.of(CoreItemType.STANDARD_CORE)))
                .save(output);
    }
}
