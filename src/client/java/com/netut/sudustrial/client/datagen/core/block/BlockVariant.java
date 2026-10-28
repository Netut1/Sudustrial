package com.netut.sudustrial.client.datagen.core.block;

public enum BlockVariant {
    STAIRS(BlockModelType.STAIRS, false, 4, 1, "#  ", "## ", "###"),
    SLAB(BlockModelType.SLAB, true, 6, 2, "###");

    private final BlockModelType modelType;
    private final boolean usesBaseModel;
    private final int craftCount;
    private final int stonecutCount;
    private final String[] pattern;

    BlockVariant(BlockModelType modelType, boolean usesBaseModel, int craftCount, int stonecutCount, String... pattern) {
        this.modelType = modelType;
        this.usesBaseModel = usesBaseModel;
        this.craftCount = craftCount;
        this.stonecutCount = stonecutCount;
        this.pattern = pattern;
    }

    public BlockModelType modelType() {
        return modelType;
    }

    public boolean usesBaseModel() {
        return usesBaseModel;
    }

    public int craftCount() {
        return craftCount;
    }

    public int stonecutCount() {
        return stonecutCount;
    }

    public String[] pattern() {
        return pattern.clone();
    }
}
