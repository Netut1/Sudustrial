package com.netut.sudustrial.client.datagen.model.tnt;

import com.netut.sudustrial.register.block.nuclear_block.RegisterNuclearAir;
import com.netut.sudustrial.register.block.nuclear_block.RegisterNuclearCore;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.resources.Identifier;

import static com.netut.sudustrial.client.datagen.model.ModelUtils.id;

public final class NuclearModelGenerator {

    private NuclearModelGenerator() {}

    public static void generate(BlockModelGenerators generator) {
        generateNuclearAir(generator);
        generateNuclearCore(generator);
    }

    private static void generateNuclearAir(BlockModelGenerators generator) {
        Identifier model = id("block/nuclear_air");

        generator.blockStateOutput.accept(
                MultiVariantGenerator.dispatch(
                        RegisterNuclearAir.NUCLEAR_AIR,
                        BlockModelGenerators.plainVariant(model)
                )
        );
    }

    private static void generateNuclearCore(BlockModelGenerators generator) {
        Identifier model = id("block/nuclear_core");

        generator.blockStateOutput.accept(
                MultiVariantGenerator.dispatch(
                        RegisterNuclearCore.NUCLEAR_CORE_BLOCK,
                        BlockModelGenerators.plainVariant(model)
                )
        );

        generator.registerSimpleItemModel(RegisterNuclearCore.NUCLEAR_CORE_BLOCK, model);
    }
}
