package com.netut.sudustrial.client.datagen.core.block;

public enum BlockModelType {
    FULL_BLOCK(true, false),
    STAIRS(true, false),
    SLAB(true, true),
    CUSTOM(false, true);

    private final boolean requiresTextures;
    private final boolean requiresBaseModel;

    BlockModelType(boolean requiresTextures, boolean requiresBaseModel) {
        this.requiresTextures = requiresTextures;
        this.requiresBaseModel = requiresBaseModel;
    }

    public boolean requiresTextures() {
        return requiresTextures;
    }

    public boolean requiresBaseModel() {
        return requiresBaseModel;
    }
}
