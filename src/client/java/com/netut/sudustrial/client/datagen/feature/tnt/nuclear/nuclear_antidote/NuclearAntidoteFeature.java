package com.netut.sudustrial.client.datagen.feature.tnt.nuclear.nuclear_antidote;

import com.netut.sudustrial.client.datagen.core.DatagenFeature;
import com.netut.sudustrial.register.item.nuclear_antidote.RegisterNuclearAntidote;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;

public final class NuclearAntidoteFeature implements DatagenFeature {
    @Override
    public void itemModels(ItemModelGenerators generator) {
        generator.generateFlatItem(RegisterNuclearAntidote.NUCLEAR_ANTIDOTE, ModelTemplates.FLAT_ITEM);
    }
}
