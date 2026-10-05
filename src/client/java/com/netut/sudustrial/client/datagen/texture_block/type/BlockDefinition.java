package com.netut.sudustrial.client.datagen.texture_block.type;

import org.jetbrains.annotations.Nullable;

public record BlockDefinition(String id, BlockModelType type, BlockTextures textures, @Nullable String baseModelId) {
    public BlockDefinition(String id, BlockModelType type, BlockTextures textures) {
        this(id, type, textures, null);
    }
}
