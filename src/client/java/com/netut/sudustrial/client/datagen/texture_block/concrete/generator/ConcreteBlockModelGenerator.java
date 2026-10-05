package com.netut.sudustrial.client.datagen.texture_block.concrete.generator;

import com.netut.sudustrial.client.datagen.texture_block.generator.DecorativeBlockModelGenerator;
import com.netut.sudustrial.client.datagen.texture_block.concrete.ConcreteBlockDefinitions;
import com.netut.sudustrial.client.datagen.texture_block.type.BlockDefinition;
import net.minecraft.client.data.models.BlockModelGenerators;

public final class ConcreteBlockModelGenerator {

    private ConcreteBlockModelGenerator() {}

    public static void generate(BlockModelGenerators generator) {
        for (BlockDefinition definition : ConcreteBlockDefinitions.concrete()) {
            DecorativeBlockModelGenerator.generate(generator, definition);
        }
    }
}