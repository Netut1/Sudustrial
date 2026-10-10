package com.netut.sudustrial.client.datagen.model.cauldron;

import com.netut.sudustrial.potion_cauldron.block.PotionCauldronBlock;
import com.netut.sudustrial.register.block.potion_cauldron.RegisterPotionCauldron;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;

import static com.netut.sudustrial.client.datagen.model.ModelUtils.id;

public final class PotionCauldronModelGenerator {

    private PotionCauldronModelGenerator() {}

    public static void generate(BlockModelGenerators generator) {
        generator.blockStateOutput.accept(
                MultiVariantGenerator.dispatch(RegisterPotionCauldron.POTION_CAULDRON_BLOCK)
                        .with(
                                PropertyDispatch.initial(PotionCauldronBlock.EFFECT_COUNT)
                                        .generate(count ->
                                                BlockModelGenerators.plainVariant(
                                                        id("block/potion_cauldron_" + count)
                                                )
                                        )
                        )
        );
    }
}
