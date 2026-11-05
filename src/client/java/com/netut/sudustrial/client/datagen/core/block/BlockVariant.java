package com.netut.sudustrial.client.datagen.core.block;

import java.util.List;

public enum BlockVariant {
    STAIRS(BlockModelType.STAIRS, 4, 1, "#  ", "## ", "###"),
    SLAB(BlockModelType.SLAB, 6, 2, "###");

    private final BlockModelType modelType;
    private final int craftCount;
    private final int stonecutCount;
    private final List<String> pattern;

    BlockVariant(BlockModelType modelType, int craftCount, int stonecutCount, String... pattern) {
        this.modelType = modelType;
        this.craftCount = craftCount;
        this.stonecutCount = stonecutCount;
        this.pattern = List.of(pattern);
    }

    public BlockModelType modelType() {
        return modelType;
    }

    public int craftCount() {
        return craftCount;
    }

    public int stonecutCount() {
        return stonecutCount;
    }

    public List<String> pattern() {
        return pattern;
    }
}
