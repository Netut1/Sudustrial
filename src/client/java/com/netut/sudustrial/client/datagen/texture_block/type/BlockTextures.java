package com.netut.sudustrial.client.datagen.texture_block.type;

import org.jetbrains.annotations.Nullable;

public record BlockTextures(
        String bottom,
        String top,
        String north,
        String east,
        String south,
        String west,
        String particle,
        @Nullable String overlay
) {

    public BlockTextures(String bottom, String top, String north, String east, String south, String west, String particle) {
        this(bottom, top, north, east, south, west, particle, null);
    }

    public boolean hasOverlay() {
        return overlay != null && !overlay.isBlank();
    }
}
