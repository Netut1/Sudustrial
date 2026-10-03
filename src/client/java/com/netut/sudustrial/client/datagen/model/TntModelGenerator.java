package com.netut.sudustrial.client.datagen.model;

import com.netut.sudustrial.register.block.tnt.*;
import com.netut.sudustrial.tnt.type.block.*;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;

import java.util.Optional;

import static com.netut.sudustrial.client.datagen.model.ModelUtils.*;

public final class TntModelGenerator {

    private static final TextureSlot OVERLAY = TextureSlot.create("overlay");

    private static final ModelTemplate TNT_TEMPLATE = new ModelTemplate(
            Optional.of(id("block/template_tnt")),
            Optional.empty(),
            TextureSlot.BOTTOM,
            TextureSlot.TOP,
            TextureSlot.SIDE
    );

    private static final ModelTemplate TNT_OVERLAY_TEMPLATE = new ModelTemplate(
            Optional.of(id("block/template_tnt_overlay")),
            Optional.empty(),
            TextureSlot.BOTTOM,
            TextureSlot.TOP,
            TextureSlot.SIDE,
            OVERLAY
    );

    private TntModelGenerator() {}

    public static void generate(BlockModelGenerators generator) {
        generateBaseTnt(generator);
        generateElementalTnt(generator);
        generateSpawnerTnt(generator);
        generateEffectTnt(generator);
        generateMiningTnt(generator);
        generateNuclearTnt(generator);
    }

    private static void generateBaseTnt(BlockModelGenerators generator) {
        for (TntType type : TntType.values()) {
            Block block = requireTntBlock(
                    RegisterTnt.TNT_BLOCKS.get(type),
                    type.getSerializedName()
            );

            // PARTY — особый: свои текстуры и фиксированный tier.
            if (type == TntType.PARTY) {
                buildTntModel(
                        generator, block,
                        "party_tnt_side",
                        "standard_tnt_top",
                        "standard_tnt_bottom",
                        10
                );
                continue;
            }

            generateVariant(generator, block, type.getSerializedName());
        }
    }

    private static void generateElementalTnt(BlockModelGenerators generator) {
        for (ElementalTntType type : ElementalTntType.values()) {
            generateVariant(
                    generator,
                    RegisterElementalTnt.ELEMENTAL_TNT_BLOCKS.get(type),
                    type.getSerializedName()
            );
        }
    }

    private static void generateSpawnerTnt(BlockModelGenerators generator) {
        for (SpawnerTntType type : SpawnerTntType.values()) {
            generateVariant(
                    generator,
                    RegisterSpawnerTnt.SPAWNER_TNT_BLOCKS.get(type),
                    type.getSerializedName()
            );
        }
    }

    private static void generateEffectTnt(BlockModelGenerators generator) {
        for (EffectTntType type : EffectTntType.values()) {
            generateVariant(
                    generator,
                    RegisterEffectTnt.EFFECT_TNT_BLOCKS.get(type),
                    type.getSerializedName()
            );
        }
    }

    private static void generateMiningTnt(BlockModelGenerators generator) {
        for (MiningTntType type : MiningTntType.values()) {
            generateVariant(
                    generator,
                    RegisterMiningTnt.MINING_TNT_BLOCKS.get(type),
                    type.getSerializedName()
            );
        }
    }

    private static void generateNuclearTnt(BlockModelGenerators generator) {
        for (NuclearTntType type : NuclearTntType.values()) {
            generateVariant(
                    generator,
                    RegisterNuclearTnt.NUCLEAR_TNT_BLOCKS.get(type),
                    type.getSerializedName()
            );
        }
    }

    private static void generateVariant(BlockModelGenerators generator, Block block, String name) {
        Block safeBlock = requireTntBlock(block, name);

        int tier = getTier(name);
        String base = getBaseName(name);

        buildTntModel(
                generator,
                safeBlock,
                base + "_side",
                base + "_top",
                base + "_bottom",
                tier
        );
    }

    private static void buildTntModel(BlockModelGenerators generator, Block block, String sideTexture, String topTexture, String bottomTexture, int tier) {
        TextureMapping textures = new TextureMapping()
                .put(TextureSlot.SIDE, blockMaterial(sideTexture))
                .put(TextureSlot.TOP, blockMaterial(topTexture))
                .put(TextureSlot.BOTTOM, blockMaterial(bottomTexture));

        ModelTemplate template = TNT_TEMPLATE;

        if (tier >= 2) {
            template = TNT_OVERLAY_TEMPLATE;
            textures.put(OVERLAY, blockMaterial("overlay_tier_" + tier));
        }

        Identifier model = template.create(block, textures, generator.modelOutput);

        generator.blockStateOutput.accept(
                MultiVariantGenerator.dispatch(
                        block,
                        BlockModelGenerators.plainVariant(model)
                )
        );

        generator.registerSimpleItemModel(block, model);
    }

    private static Block requireTntBlock(Block block, String name) {
        if (block == null) {
            throw new IllegalStateException("No registered TNT block for: " + name);
        }
        return block;
    }
}
