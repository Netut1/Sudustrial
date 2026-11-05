package com.netut.sudustrial.client.datagen.core.block;

import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public record BlockTextures(Identifier bottom, Identifier top, Identifier north, Identifier east, Identifier south, Identifier west,
                            Identifier particle, @Nullable Identifier overlay) {
    public BlockTextures {
        Objects.requireNonNull(bottom, "bottom texture");
        Objects.requireNonNull(top, "top texture");
        Objects.requireNonNull(north, "north texture");
        Objects.requireNonNull(east, "east texture");
        Objects.requireNonNull(south, "south texture");
        Objects.requireNonNull(west, "west texture");
        Objects.requireNonNull(particle, "particle texture");
    }

    public static BlockTextures uniform(Identifier texture) {
        return new BlockTextures(texture, texture, texture, texture, texture, texture, texture, null);
    }

    public static BlockTextures sides(Identifier bottom, Identifier top, Identifier side, @Nullable Identifier overlay) {
        return new BlockTextures(bottom, top, side, side, side, side, side, overlay);
    }

    public boolean hasOverlay() {
        return overlay != null;
    }

    public boolean hasUniformSides() {
        return north.equals(east) && east.equals(south) && south.equals(west);
    }
}
