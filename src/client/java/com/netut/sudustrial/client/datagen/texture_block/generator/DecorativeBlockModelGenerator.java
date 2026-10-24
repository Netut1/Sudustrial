package com.netut.sudustrial.client.datagen.texture_block.generator;

import com.netut.sudustrial.Sudustrial;
import com.netut.sudustrial.client.datagen.texture_block.type.BlockDefinition;
import com.netut.sudustrial.client.datagen.texture_block.type.BlockTextures;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;

import java.util.Optional;

import static com.netut.sudustrial.client.datagen.util.DatagenUtils.id;

public final class DecorativeBlockModelGenerator {

    private static final TextureSlot NORTH = TextureSlot.create("north");

    private static final TextureSlot EAST = TextureSlot.create("east");

    private static final TextureSlot SOUTH = TextureSlot.create("south");

    private static final TextureSlot WEST = TextureSlot.create("west");

    private static final TextureSlot OVERLAY = TextureSlot.create("overlay");

    private static final ModelTemplate FULL_BLOCK_TEMPLATE =
            new ModelTemplate(
                    Optional.of(id("block/template_decorative_full")),
                    Optional.empty(),
                    TextureSlot.BOTTOM,
                    TextureSlot.TOP,
                    NORTH,
                    EAST,
                    SOUTH,
                    WEST,
                    TextureSlot.PARTICLE
            );

    private static final ModelTemplate FULL_BLOCK_OVERLAY_TEMPLATE =
            new ModelTemplate(
                    Optional.of(id("block/template_decorative_full_overlay")),
                    Optional.empty(),
                    TextureSlot.BOTTOM,
                    TextureSlot.TOP,
                    NORTH,
                    EAST,
                    SOUTH,
                    WEST,
                    TextureSlot.PARTICLE,
                    OVERLAY
            );

    private DecorativeBlockModelGenerator() {}

    public static void generate(BlockModelGenerators generator, BlockDefinition definition) {
        Block block = getBlock(definition.id());

        switch (definition.type()) {
            case FULL_BLOCK -> generateFullBlock(generator, block, definition);
            case STAIRS -> generateStairs(generator, block, definition);
            case SLAB -> generateSlab(generator, block, definition);
            default -> throw new UnsupportedOperationException("Block model type is not implemented: " + definition.type());
        }
    }

    private static void generateFullBlock(BlockModelGenerators generator, Block block, BlockDefinition definition) {
        BlockTextures textures = definition.textures();
        Identifier modelId = id("block/" + definition.id());

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
        else {
            template = FULL_BLOCK_TEMPLATE;
        }

        template.create(modelId, mapping, generator.modelOutput);
        generator.blockStateOutput.accept(MultiVariantGenerator.dispatch(block, BlockModelGenerators.plainVariant(modelId)));
        generator.registerSimpleItemModel(block, modelId);
    }

    private static void generateStairs(BlockModelGenerators generator, Block block, BlockDefinition definition) {
        BlockTextures textures = definition.textures();

        validateVanillaSideTextures(definition);
        Identifier stairsModel = id("block/" + definition.id());
        Identifier innerStairsModel = id("block/" + definition.id().replace("_stairs", "") + "_inner_stairs");
        Identifier outerStairsModel = id("block/" + definition.id().replace("_stairs", "") + "_outer_stairs");

        TextureMapping mapping = stairSlabMapping(textures);

        ModelTemplates.STAIRS_STRAIGHT.create(stairsModel, mapping, generator.modelOutput);
        ModelTemplates.STAIRS_INNER.create(innerStairsModel, mapping, generator.modelOutput);
        ModelTemplates.STAIRS_OUTER.create(outerStairsModel, mapping, generator.modelOutput);

        generator.blockStateOutput.accept(BlockModelGenerators.createStairs(block, BlockModelGenerators.plainVariant(innerStairsModel),
                        BlockModelGenerators.plainVariant(stairsModel), BlockModelGenerators.plainVariant(outerStairsModel)));
        generator.registerSimpleItemModel(block, stairsModel);
    }

    private static void generateSlab(BlockModelGenerators generator, Block block, BlockDefinition definition) {
        BlockTextures textures = definition.textures();

        validateVanillaSideTextures(definition);
        String baseModelId = requireBaseModelId(definition);
        Identifier slabModel = id("block/" + definition.id());
        Identifier slabTopModel = id("block/" + definition.id() + "_top");
        Identifier baseBlockModel = resolveModel(baseModelId);

        TextureMapping mapping = stairSlabMapping(textures);
        ModelTemplates.SLAB_BOTTOM.create(slabModel, mapping, generator.modelOutput);
        ModelTemplates.SLAB_TOP.create(slabTopModel, mapping, generator.modelOutput);
        generator.blockStateOutput.accept(BlockModelGenerators.createSlab(block, BlockModelGenerators.plainVariant(slabModel),
                        BlockModelGenerators.plainVariant(slabTopModel), BlockModelGenerators.plainVariant(baseBlockModel))
        );
        generator.registerSimpleItemModel(block, slabModel);
    }

    private static TextureMapping stairSlabMapping(BlockTextures textures) {
        return new TextureMapping()
                .put(TextureSlot.BOTTOM, material(textures.bottom()))
                .put(TextureSlot.TOP, material(textures.top()))
                .put(TextureSlot.SIDE, material(textures.north()));
    }

    private static void validateVanillaSideTextures(BlockDefinition definition) {
        BlockTextures textures = definition.textures();
        if (!textures.north().equals(textures.east()) || !textures.north().equals(textures.south()) || !textures.north().equals(textures.west())) {
            throw new IllegalArgumentException("Block " + definition.id() + " uses different side textures for " + definition.type() + ". Vanilla stair/slab templates " + "currently support one SIDE texture.");
        }
        if (textures.hasOverlay()) {
            throw new IllegalArgumentException("Block " + definition.id() + " uses overlay, but overlay is not " + "implemented for " + definition.type());
        }
    }

    private static String requireBaseModelId(BlockDefinition definition) {
        String baseModelId = definition.baseModelId();

        if (baseModelId == null || baseModelId.isBlank()) {
            throw new IllegalArgumentException("Block " + definition.id() + " of type " + definition.type() + " requires baseModelId");
        }
        return baseModelId;
    }

    private static Material material(String path) {
        return new Material(resolveTexture(path));
    }

    private static Identifier resolveTexture(String path) {
        if (path.contains(":")) {
            Identifier identifier = Identifier.tryParse(path);
            if (identifier == null) {
                throw new IllegalArgumentException("Invalid texture identifier: " + path);
            }
            return identifier;
        }
        return Identifier.fromNamespaceAndPath(Sudustrial.MOD_ID, path);
    }

    private static Block getBlock(String path) {
        Identifier identifier = Identifier.fromNamespaceAndPath(Sudustrial.MOD_ID, path);

        return BuiltInRegistries.BLOCK
                .get(identifier)
                .orElseThrow(() -> new IllegalStateException("Unknown block for decorative datagen: " + identifier)
                )
                .value();
    }

    private static Identifier resolveModel(String path) {
        if (path.contains(":")) {
            Identifier identifier = Identifier.tryParse(path);

            if (identifier == null) {
                throw new IllegalArgumentException("Invalid model identifier: " + path);
            }
            return identifier;
        }

        return Identifier.fromNamespaceAndPath(Sudustrial.MOD_ID, "block/" + path);
    }
}
