package com.netut.sudustrial.client.datagen.feature.tnt.nuclear;

import com.netut.sudustrial.client.datagen.core.DefinitionFeature;
import com.netut.sudustrial.client.datagen.core.Registered;
import com.netut.sudustrial.client.datagen.core.block.BlockDefinition;
import com.netut.sudustrial.client.datagen.core.block.BlockTextures;
import com.netut.sudustrial.client.datagen.core.common.Stage;
import com.netut.sudustrial.client.datagen.core.item.ItemDefinition;
import com.netut.sudustrial.client.datagen.feature.tnt.TntStages;
import com.netut.sudustrial.register.block.nuclear_block.RegisterNuclearAir;
import com.netut.sudustrial.register.block.nuclear_block.RegisterNuclearCore;
import com.netut.sudustrial.register.block.tnt.RegisterNuclearTnt;
import com.netut.sudustrial.register.item.core.RegisterCore;
import com.netut.sudustrial.tnt.type.block.NuclearTntType;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;

import java.util.List;
import java.util.function.Consumer;

public final class NuclearFeature extends DefinitionFeature {
    @Override
    protected List<BlockDefinition> blocks() {
        return List.of(
                BlockDefinition.fullBlock(RegisterNuclearAir.NUCLEAR_AIR, BlockTextures.uniform("block/nuclear_air"))
                        .asTranslucent()
                        .withoutItem(),
                BlockDefinition.custom(RegisterNuclearCore.NUCLEAR_CORE_BLOCK, "nuclear_core")
        );
    }

    @Override
    protected List<ItemDefinition> items() {
        return RegisterCore.CORE_ITEMS.values().stream().map(ItemDefinition::flat).toList();
    }

    @Override
    public void recipes(RecipeProvider provider, RecipeOutput output) {
        CoreRecipeGenerator.generate(provider, output);
        NuclearRecipeGenerator.generate(provider, output);
    }

    @Override
    public void advancements(Consumer<AdvancementHolder> consumer) {
        new Stage("nuclear_era",
                Registered.require(RegisterNuclearTnt.NUCLEAR_TNT_ITEMS, NuclearTntType.NUCLEAR),
                TntStages.TNT_ERA,
                RegisterCore.CORE_ITEMS.values()).save(consumer);
    }
}
