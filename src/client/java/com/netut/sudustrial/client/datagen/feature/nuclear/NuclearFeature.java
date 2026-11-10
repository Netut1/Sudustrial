package com.netut.sudustrial.client.datagen.feature.nuclear;

import com.netut.sudustrial.client.datagen.core.advancement.Stage;
import com.netut.sudustrial.client.datagen.core.block.BlockDefinition;
import com.netut.sudustrial.client.datagen.core.block.BlockTextures;
import com.netut.sudustrial.client.datagen.core.item.ItemDefinition;
import com.netut.sudustrial.client.datagen.feature.DefinitionFeature;
import com.netut.sudustrial.client.datagen.feature.tnt.TntCatalog;
import com.netut.sudustrial.client.datagen.feature.tnt.TntStages;
import com.netut.sudustrial.register.block.nuclear_block.RegisterNuclearAir;
import com.netut.sudustrial.register.block.nuclear_block.RegisterNuclearCore;
import com.netut.sudustrial.tnt.type.block.NuclearTntType;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;

import java.util.List;
import java.util.function.Consumer;

import static com.netut.sudustrial.client.datagen.core.DatagenUtils.blockId;

public final class NuclearFeature extends DefinitionFeature {
    @Override
    protected List<BlockDefinition> blocks() {
        return List.of(
                BlockDefinition.fullBlock(RegisterNuclearAir.NUCLEAR_AIR, BlockTextures.uniform(blockId("nuclear_air")))
                        .asTranslucent()
                        .withoutItem(),
                BlockDefinition.custom(RegisterNuclearCore.NUCLEAR_CORE_BLOCK, blockId("nuclear_core"))
        );
    }

    @Override
    protected List<ItemDefinition> items() {
        return Cores.all().stream().map(ItemDefinition::flat).toList();
    }

    @Override
    public void recipes(RecipeProvider provider, RecipeOutput output) {
        RecipeGenerator.generate(provider, output, TntCatalog.load());
    }

    @Override
    public void advancements(Consumer<AdvancementHolder> consumer) {
        new Stage("nuclear_era",
                TntCatalog.load().item(NuclearTntType.NUCLEAR),
                TntStages.TNT_ERA,
                Cores.all()).save(consumer);
    }
}
