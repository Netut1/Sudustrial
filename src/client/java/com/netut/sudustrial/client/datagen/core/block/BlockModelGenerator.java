package com.netut.sudustrial.client.datagen.core.block;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;

import java.util.Optional;

import static com.netut.sudustrial.client.datagen.core.DatagenUtils.blockId;

public final class BlockModelGenerator {
    private static final TextureSlot NORTH = TextureSlot.create("north");
    private static final TextureSlot EAST = TextureSlot.create("east");
    private static final TextureSlot SOUTH = TextureSlot.create("south");
    private static final TextureSlot WEST = TextureSlot.create("west");
    private static final TextureSlot OVERLAY = TextureSlot.create("overlay");

    private static final ModelTemplate FULL_BLOCK_TEMPLATE = fullBlockTemplate("template_decorative_full", false);
    private static final ModelTemplate FULL_BLOCK_OVERLAY_TEMPLATE = fullBlockTemplate("template_decorative_full_overlay", true);
    private static final ModelTemplate FULL_BLOCK_TRANSLUCENT_TEMPLATE = fullBlockTemplate("template_decorative_full_translucent", false);

    private BlockModelGenerator() {}

    public static void generate(BlockModelGenerators generator, BlockDefinition definition) {
        Identifier itemModel = switch (definition.type()) {
            case FULL_BLOCK -> generateFullBlock(generator, definition);
            case STAIRS -> generateStairs(generator, definition);
            case SLAB -> generateSlab(generator, definition);
            case CUSTOM -> generateCustom(generator, definition);
        };

        if (definition.hasItem()) {
            generator.registerSimpleItemModel(definition.block(), itemModel);
        }
    }

    private static Identifier generateFullBlock(BlockModelGenerators generator, BlockDefinition definition) {
        BlockTextures textures = definition.requireTextures();
        Identifier model = blockId(definition.block());

        TextureMapping mapping = new TextureMapping()
                .put(TextureSlot.BOTTOM, material(textures.bottom()))
                .put(TextureSlot.TOP, material(textures.top()))
                .put(NORTH, material(textures.north()))
                .put(EAST, material(textures.east()))
                .put(SOUTH, material(textures.south()))
                .put(WEST, material(textures.west()))
                .put(TextureSlot.PARTICLE, material(textures.particle()));
        ModelTemplate template;

        if (textures.hasOverlay()) {
            mapping.put(OVERLAY, material(textures.overlay()));
            template = FULL_BLOCK_OVERLAY_TEMPLATE;
        }
        else if (definition.translucent()) {
            template = FULL_BLOCK_TRANSLUCENT_TEMPLATE;
        }
        else {
            template = FULL_BLOCK_TEMPLATE;
        }

        template.create(model, mapping, generator.modelOutput);
        generator.blockStateOutput.accept(MultiVariantGenerator.dispatch(definition.block(), BlockModelGenerators.plainVariant(model)));
        return model;
    }

    private static Identifier generateStairs(BlockModelGenerators generator, BlockDefinition definition) {
        Block block = definition.block();
        Identifier straight = blockId(block);
        String stem = withoutSuffix(definition.id(), "_stairs");
        Identifier inner = sibling(straight, stem + "_inner_stairs");
        Identifier outer = sibling(straight, stem + "_outer_stairs");

        TextureMapping mapping = stairSlabMapping(definition.requireTextures());
        ModelTemplates.STAIRS_STRAIGHT.create(straight, mapping, generator.modelOutput);
        ModelTemplates.STAIRS_INNER.create(inner, mapping, generator.modelOutput);
        ModelTemplates.STAIRS_OUTER.create(outer, mapping, generator.modelOutput);

        generator.blockStateOutput.accept(BlockModelGenerators.createStairs(block, BlockModelGenerators.plainVariant(inner),
                BlockModelGenerators.plainVariant(straight), BlockModelGenerators.plainVariant(outer)));
        return straight;
    }

    private static Identifier generateSlab(BlockModelGenerators generator, BlockDefinition definition) {
        Block block = definition.block();
        Identifier bottom = blockId(block);
        Identifier top = sibling(bottom, definition.id() + "_top");
        Identifier full = definition.requireBaseModel();

        TextureMapping mapping = stairSlabMapping(definition.requireTextures());
        ModelTemplates.SLAB_BOTTOM.create(bottom, mapping, generator.modelOutput);
        ModelTemplates.SLAB_TOP.create(top, mapping, generator.modelOutput);

        generator.blockStateOutput.accept(BlockModelGenerators.createSlab(block, BlockModelGenerators.plainVariant(bottom),
                BlockModelGenerators.plainVariant(top), BlockModelGenerators.plainVariant(full)));
        return bottom;
    }

    private static Identifier generateCustom(BlockModelGenerators generator, BlockDefinition definition) {
        Identifier model = definition.requireBaseModel();

        generator.blockStateOutput.accept(MultiVariantGenerator.dispatch(definition.block(), BlockModelGenerators.plainVariant(model)));
        return model;
    }

    private static TextureMapping stairSlabMapping(BlockTextures textures) {
        return new TextureMapping()
                .put(TextureSlot.BOTTOM, material(textures.bottom()))
                .put(TextureSlot.TOP, material(textures.top()))
                .put(TextureSlot.SIDE, material(textures.north()));
    }

    private static ModelTemplate fullBlockTemplate(String parentName, boolean withOverlay) {
        TextureSlot[] required = withOverlay
                ? new TextureSlot[]{TextureSlot.BOTTOM, TextureSlot.TOP, NORTH, EAST, SOUTH, WEST, TextureSlot.PARTICLE, OVERLAY}
                : new TextureSlot[]{TextureSlot.BOTTOM, TextureSlot.TOP, NORTH, EAST, SOUTH, WEST, TextureSlot.PARTICLE};
        return new ModelTemplate(Optional.of(blockId(parentName)), Optional.empty(), required);
    }

    private static Identifier sibling(Identifier model, String name) {
        return Identifier.fromNamespaceAndPath(model.getNamespace(), "block/" + name);
    }

    private static String withoutSuffix(String value, String suffix) {
        return value.endsWith(suffix) ? value.substring(0, value.length() - suffix.length()) : value;
    }

    private static Material material(Identifier texture) {
        return new Material(texture);
    }
}
