package com.netut.sudustrial.client.datagen.feature.potion_cauldron;

import com.netut.sudustrial.client.datagen.core.DefinitionFeature;
import com.netut.sudustrial.client.datagen.core.item.ItemDefinition;
import com.netut.sudustrial.register.block.potion_cauldron.RegisterPotionCauldron;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;

import java.util.List;

public final class PotionCauldronFeature extends DefinitionFeature {
    @Override
    protected List<ItemDefinition> items() {
        return List.of(ItemDefinition.flat(RegisterPotionCauldron.POTION_CAULDRON_ITEM));
    }

    @Override
    public void blockModels(BlockModelGenerators generator) {
        super.blockModels(generator);
        ModelGenerator.generate(generator);
    }

    @Override
    public void recipes(RecipeProvider provider, RecipeOutput output) {
        RecipeGenerator.generate(provider, output);
    }

    @Override
    public void loot(LootSink loot) {
        super.loot(loot);
        loot.dropSelf(RegisterPotionCauldron.POTION_CAULDRON_BLOCK);
    }
}
