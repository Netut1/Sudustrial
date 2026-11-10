package com.netut.sudustrial.client.datagen.core.block;

import com.netut.sudustrial.client.datagen.core.DatagenUtils;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public record BlockDefinition(Block block, BlockModelType type, @Nullable BlockTextures textures, @Nullable Identifier baseModel,
                              boolean translucent, boolean hasItem) {
    public BlockDefinition {
        Objects.requireNonNull(block, "block (is it registered?)");
        Objects.requireNonNull(type, "type");
        String name = DatagenUtils.path(block);

        if (type.requiresTextures() == (textures == null)) {
            throw invalid(name, type + (type.requiresTextures() ? " requires textures" : " does not use textures"));
        }
        if (type.requiresBaseModel() == (baseModel == null)) {
            throw invalid(name, type + (type.requiresBaseModel() ? " requires a base model" : " does not use a base model"));
        }
        if (translucent && type != BlockModelType.FULL_BLOCK) {
            throw invalid(name, "translucent is supported only by FULL_BLOCK, not by " + type);
        }
        if (translucent && textures != null && textures.hasOverlay()) {
            throw invalid(name, "translucent cannot be combined with an overlay");
        }
        if (textures != null && type != BlockModelType.FULL_BLOCK) {
            if (!textures.hasUniformSides()) {
                throw invalid(name, type + " supports only one side texture (north, east, south and west must match)");
            }
            if (textures.hasOverlay()) {
                throw invalid(name, type + " does not support an overlay");
            }
        }
    }

    public BlockDefinition(Block block, BlockModelType type, BlockTextures textures) {
        this(block, type, textures, null);
    }

    public BlockDefinition(Block block, BlockModelType type, BlockTextures textures, @Nullable Identifier baseModel) {
        this(block, type, textures, baseModel, false, true);
    }

    public static BlockDefinition fullBlock(Block block, BlockTextures textures) {
        return new BlockDefinition(block, BlockModelType.FULL_BLOCK, textures);
    }

    public static BlockDefinition custom(Block block, Identifier model) {
        return new BlockDefinition(block, BlockModelType.CUSTOM, null, model, false, true);
    }

    public BlockDefinition asTranslucent() {
        return new BlockDefinition(block, type, textures, baseModel, true, hasItem);
    }

    public BlockDefinition withoutItem() {
        return new BlockDefinition(block, type, textures, baseModel, translucent, false);
    }

    public BlockTextures requireTextures() {
        if (textures == null) {
            throw invalid(id(), type + " requires textures");
        }
        return textures;
    }

    public Identifier requireBaseModel() {
        if (baseModel == null) {
            throw invalid(id(), type + " requires a base model");
        }
        return baseModel;
    }

    public String id() {
        return DatagenUtils.path(block);
    }

    private static IllegalArgumentException invalid(String name, String message) {
        return new IllegalArgumentException("Block " + name + ": " + message);
    }
}
