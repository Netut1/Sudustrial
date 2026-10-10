package com.netut.sudustrial.client.datagen;

import com.netut.sudustrial.client.datagen.model.cauldron.PotionCauldronModelGenerator;
import com.netut.sudustrial.client.datagen.model.po_2.Po2ModelGenerator;
import com.netut.sudustrial.client.datagen.model.tnt.CoreItemModelGenerator;
import com.netut.sudustrial.client.datagen.model.tnt.NuclearModelGenerator;
import com.netut.sudustrial.client.datagen.model.tnt.TntModelGenerator;
import com.netut.sudustrial.register.block.potion_cauldron.RegisterPotionCauldron;
import com.netut.sudustrial.register.item.nuclear_antidote.RegisterNuclearAntidote;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;
import org.jspecify.annotations.NonNull;

public class SudustrialModelProvider extends FabricModelProvider {

    public SudustrialModelProvider(FabricPackOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(@NonNull BlockModelGenerators generator) {
        TntModelGenerator.generate(generator);
        NuclearModelGenerator.generate(generator);
        PotionCauldronModelGenerator.generate(generator);
        Po2ModelGenerator.generate(generator);
    }

    @Override
    public void generateItemModels(@NonNull ItemModelGenerators generator) {
        CoreItemModelGenerator.generate(generator);

        generator.generateFlatItem(RegisterNuclearAntidote.NUCLEAR_ANTIDOTE, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(RegisterPotionCauldron.POTION_CAULDRON_ITEM, ModelTemplates.FLAT_ITEM);
    }

    @Override
    public @NonNull String getName() {
        return "SudustrialModelProvider";
    }
}
