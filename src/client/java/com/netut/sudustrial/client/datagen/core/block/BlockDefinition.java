package com.netut.sudustrial.client.datagen.core.block;

import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;

public record BlockDefinition(Block block, BlockModelType type, @Nullable BlockTextures textures, @Nullable String baseModelId, boolean translucent, boolean hasItem) {
    public BlockDefinition(Block block, BlockModelType type, BlockTextures textures) {
        this(block, type, textures, null);
    }

    public BlockDefinition(Block block, BlockModelType type, BlockTextures textures, @Nullable String baseModelId) {
        this(block, type, textures, baseModelId, false, true);
    }

    public static BlockDefinition fullBlock(Block block, BlockTextures textures) {
        return new BlockDefinition(block, BlockModelType.FULL_BLOCK, textures);
    }

    public static BlockDefinition custom(Block block, String modelId) {
        return new BlockDefinition(block, BlockModelType.CUSTOM, null, modelId, false, true);
    }

    public BlockDefinition asTranslucent() {
        return new BlockDefinition(block, type, textures, baseModelId, true, hasItem);
    }

    public BlockDefinition withoutItem() {
        return new BlockDefinition(block, type, textures, baseModelId, translucent, false);
    }

    public BlockTextures requireTextures() {
        if (textures == null) {throw new IllegalStateException("Block " + id() + " of type " + type + " requires textures");}
        return textures;
    }

    public String id() {
        return BlockIds.path(block);
    }
}
