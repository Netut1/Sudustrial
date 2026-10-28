package com.netut.sudustrial.client.datagen.core.block;

import org.jetbrains.annotations.Nullable;

public record BlockTextures(String bottom, String top, String north, String east, String south, String west, String particle, @Nullable String overlay) {
    public BlockTextures(String bottom, String top, String north, String east, String south, String west, String particle) {
        this(bottom, top, north, east, south, west, particle, null);
    }

    public static BlockTextures uniform(String texture) {
        return new BlockTextures(texture, texture, texture, texture, texture, texture, texture);
    }

    public static BlockTextures sides(String bottom, String top, String side, @Nullable String overlay) {
        return new BlockTextures(bottom, top, side, side, side, side, side, overlay);
    }

    public boolean hasOverlay() {
        return overlay != null && !overlay.isBlank();
    }
}
