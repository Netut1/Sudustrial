package com.netut.sudustrial.client.datagen;

import com.netut.sudustrial.client.datagen.core.DatagenFeature;
import com.netut.sudustrial.client.datagen.core.Features;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import org.jspecify.annotations.NonNull;

public class SudustrialModelProvider extends FabricModelProvider {
    public SudustrialModelProvider(FabricPackOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(@NonNull BlockModelGenerators generator) {
        for (DatagenFeature feature : Features.ALL) {
            feature.blockModels(generator);
        }
    }

    @Override
    public void generateItemModels(@NonNull ItemModelGenerators generator) {
        for (DatagenFeature feature : Features.ALL) {
            feature.itemModels(generator);
        }
    }

    @Override
    public @NonNull String getName() {
        return "SudustrialModelProvider";
    }
}
