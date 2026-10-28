package com.netut.sudustrial.client.datagen.core.block;

import com.netut.sudustrial.Sudustrial;
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

import static com.netut.sudustrial.client.datagen.core.DatagenUtils.id;

public final class BlockModelGenerator {
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

    private static final ModelTemplate FULL_BLOCK_TRANSLUCENT_TEMPLATE =
            new ModelTemplate(
                    Optional.of(id("block/template_decorative_full_translucent")),
                    Optional.empty(),
                    TextureSlot.BOTTOM,
                    TextureSlot.TOP,
                    NORTH,
                    EAST,
                    SOUTH,
                    WEST,
                    TextureSlot.PARTICLE
            );

    private BlockModelGenerator() {}

    public static void generate(BlockModelGenerators generator, BlockDefinition definition) {
        Block block = definition.block();
        validateOptions(definition);
        switch (definition.type()) {
            case FULL_BLOCK -> generateFullBlock(generator, block, definition);
            case STAIRS -> generateStairs(generator, block, definition);
            case SLAB -> generateSlab(generator, block, definition);
            case CUSTOM -> generateCustom(generator, block, definition);
        }
    }

    private static void generateFullBlock(BlockModelGenerators generator, Block block, BlockDefinition definition) {
        BlockTextures textures = definition.requireTextures();
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
        else if (definition.translucent()) {
            template = FULL_BLOCK_TRANSLUCENT_TEMPLATE;
        }
        else {
            template = FULL_BLOCK_TEMPLATE;
        }

        template.create(modelId, mapping, generator.modelOutput);
        generator.blockStateOutput.accept(MultiVariantGenerator.dispatch(block, BlockModelGenerators.plainVariant(modelId)));
        registerItem(generator, block, modelId, definition);
    }

    private static void generateStairs(BlockModelGenerators generator, Block block, BlockDefinition definition) {
        BlockTextures textures = definition.requireTextures();

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
        registerItem(generator, block, stairsModel, definition);
    }

    private static void generateSlab(BlockModelGenerators generator, Block block, BlockDefinition definition) {
        BlockTextures textures = definition.requireTextures();

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
        registerItem(generator, block, slabModel, definition);
    }

    /** Модель уже написана вручную: генерируем только blockstate и (если нужно) модель предмета. */
    private static void generateCustom(BlockModelGenerators generator, Block block, BlockDefinition definition) {
        Identifier modelId = resolveModel(requireBaseModelId(definition));

        generator.blockStateOutput.accept(MultiVariantGenerator.dispatch(block, BlockModelGenerators.plainVariant(modelId)));
        registerItem(generator, block, modelId, definition);
    }

    private static void registerItem(BlockModelGenerators generator, Block block, Identifier model, BlockDefinition definition) {
        if (definition.hasItem()) {
            generator.registerSimpleItemModel(block, model);
        }
    }

    private static TextureMapping stairSlabMapping(BlockTextures textures) {
        return new TextureMapping()
                .put(TextureSlot.BOTTOM, material(textures.bottom()))
                .put(TextureSlot.TOP, material(textures.top()))
                .put(TextureSlot.SIDE, material(textures.north()));
    }

    private static void validateOptions(BlockDefinition definition) {
        if (!definition.translucent()) {return;}

        if (definition.type() != BlockModelType.FULL_BLOCK) {
            throw new IllegalArgumentException("Block " + definition.id() + ": translucent is implemented only for FULL_BLOCK, not for " + definition.type());
        }
        if (definition.requireTextures().hasOverlay()) {
            throw new IllegalArgumentException("Block " + definition.id() + ": translucent together with overlay is not implemented");
        }
    }

    private static void validateVanillaSideTextures(BlockDefinition definition) {
        BlockTextures textures = definition.requireTextures();
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
