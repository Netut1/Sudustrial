package com.netut.sudustrial.client.datagen.concrete.generator;

import com.netut.sudustrial.register.block.concrete.ModConcreteBlocks;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;

import static com.netut.sudustrial.client.datagen.model.ModelUtils.id;

public final class ConcreteBlockModelGenerator {

    private ConcreteBlockModelGenerator() {
    }

    public static void generate(BlockModelGenerators generator) {
        for (DyeColor color : DyeColor.values()) {
            String name = color.getName();

            generateConcrete(generator, color, name);
            generateConcretePowder(generator, color, name);
        }
    }

    private static void generateConcrete(BlockModelGenerators generator, DyeColor color, String colorName) {
        Block stairs = ModConcreteBlocks.CONCRETE_STAIR.get(color);
        Block slab = ModConcreteBlocks.CONCRETE_SLAB.get(color);

        Identifier texture = Identifier.fromNamespaceAndPath("minecraft", "block/" + colorName + "_concrete");

        Identifier stairsModel = id("block/" + colorName + "_concrete_stairs");

        Identifier innerStairsModel = id("block/" + colorName + "_concrete_inner_stairs");

        Identifier outerStairsModel = id("block/" + colorName + "_concrete_outer_stairs");

        Identifier slabModel = id("block/" + colorName + "_concrete_slab");

        Identifier slabTopModel = id("block/" + colorName + "_concrete_slab_top");

        createStairsModels(generator, texture, stairsModel, innerStairsModel, outerStairsModel);

        createSlabModels(generator, texture, slabModel, slabTopModel);

        generator.blockStateOutput.accept(
                BlockModelGenerators.createStairs(
                        stairs,
                        BlockModelGenerators.plainVariant(innerStairsModel),
                        BlockModelGenerators.plainVariant(stairsModel),
                        BlockModelGenerators.plainVariant(outerStairsModel)
                )
        );

        generator.blockStateOutput.accept(
                BlockModelGenerators.createSlab(
                        slab,
                        BlockModelGenerators.plainVariant(slabModel),
                        BlockModelGenerators.plainVariant(slabTopModel),
                        BlockModelGenerators.plainVariant(
                                Identifier.fromNamespaceAndPath(
                                        "minecraft", "block/" + colorName + "_concrete"
                                )
                        )
                )
        );
    }

    private static void generateConcretePowder(BlockModelGenerators generator, DyeColor color, String colorName) {
        Block stairs = ModConcreteBlocks.CONCRETE_POWDER_STAIR.get(color);
        Block slab = ModConcreteBlocks.CONCRETE_POWDER_SLAB.get(color);

        Identifier texture = Identifier.fromNamespaceAndPath("minecraft", "block/" + colorName + "_concrete_powder");

        Identifier stairsModel = id("block/" + colorName + "_concrete_powder_stairs");

        Identifier innerStairsModel = id("block/" + colorName + "_concrete_powder_inner_stairs");

        Identifier outerStairsModel = id("block/" + colorName + "_concrete_powder_outer_stairs");

        Identifier slabModel = id("block/" + colorName + "_concrete_powder_slab");

        Identifier slabTopModel = id("block/" + colorName + "_concrete_powder_slab_top");

        createStairsModels(generator, texture, stairsModel, innerStairsModel, outerStairsModel);

        createSlabModels(generator, texture, slabModel, slabTopModel);

        generator.blockStateOutput.accept(
                BlockModelGenerators.createStairs(
                        stairs,
                        BlockModelGenerators.plainVariant(innerStairsModel),
                        BlockModelGenerators.plainVariant(stairsModel),
                        BlockModelGenerators.plainVariant(outerStairsModel)
                )
        );

        generator.blockStateOutput.accept(
                BlockModelGenerators.createSlab(
                        slab,
                        BlockModelGenerators.plainVariant(slabModel),
                        BlockModelGenerators.plainVariant(slabTopModel),
                        BlockModelGenerators.plainVariant(
                                Identifier.fromNamespaceAndPath(
                                        "minecraft",
                                        "block/" + colorName + "_concrete_powder"
                                )
                        )
                )
        );
    }

    private static void createStairsModels(BlockModelGenerators generator, Identifier texture, Identifier stairsModel, Identifier innerStairsModel, Identifier outerStairsModel) {
        TextureMapping mapping = new TextureMapping()
                .put(TextureSlot.BOTTOM, textureMaterial(texture))
                .put(TextureSlot.TOP, textureMaterial(texture))
                .put(TextureSlot.SIDE, textureMaterial(texture));

        ModelTemplates.STAIRS_STRAIGHT.create(stairsModel, mapping, generator.modelOutput);

        ModelTemplates.STAIRS_INNER.create(innerStairsModel, mapping, generator.modelOutput);

        ModelTemplates.STAIRS_OUTER.create(outerStairsModel, mapping, generator.modelOutput);
    }

    private static void createSlabModels(BlockModelGenerators generator, Identifier texture, Identifier slabModel, Identifier slabTopModel) {
        TextureMapping mapping = new TextureMapping()
                .put(TextureSlot.BOTTOM, textureMaterial(texture))
                .put(TextureSlot.TOP, textureMaterial(texture))
                .put(TextureSlot.SIDE, textureMaterial(texture));

        ModelTemplates.SLAB_BOTTOM.create(slabModel, mapping, generator.modelOutput);

        ModelTemplates.SLAB_TOP.create(slabTopModel, mapping, generator.modelOutput);
    }

    private static Material textureMaterial(Identifier texture) {
        return new Material(texture);
    }
}
