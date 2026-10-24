package com.netut.sudustrial.client.datagen.feature.potion_cauldron;

import com.netut.sudustrial.client.datagen.core.DatagenFeature;
import com.netut.sudustrial.register.block.potion_cauldron.RegisterPotionCauldron;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;

public final class PotionCauldronFeature implements DatagenFeature {
    @Override
    public void blockModels(BlockModelGenerators generator) {
        ModelGenerator.generate(generator);
    }

    @Override
    public void itemModels(ItemModelGenerators generator) {
        generator.generateFlatItem(RegisterPotionCauldron.POTION_CAULDRON_ITEM, ModelTemplates.FLAT_ITEM);
    }

    @Override
    public void recipes(RecipeProvider provider, RecipeOutput output) {
        RecipeGenerator.generate(provider, output);
    }

    @Override
    public void loot(LootSink loot) {
        LootGenerator.generate(loot);
    }
}
