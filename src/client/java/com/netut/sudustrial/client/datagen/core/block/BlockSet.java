package com.netut.sudustrial.client.datagen.core.block;

import com.netut.sudustrial.client.datagen.core.DatagenUtils;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public record BlockSet(Block source, Map<BlockVariant, Block> blocks) {
    public BlockSet {
        Objects.requireNonNull(source, "source block (is it registered?)");
        Objects.requireNonNull(blocks, "blocks");
        if (blocks.isEmpty()) {
            throw new IllegalArgumentException("BlockSet of " + DatagenUtils.path(source) + " has no variants");
        }

        Map<BlockVariant, Block> copy = new EnumMap<>(BlockVariant.class);
        for (Map.Entry<BlockVariant, Block> entry : blocks.entrySet()) {
            BlockVariant variant = Objects.requireNonNull(entry.getKey(), "variant");
            copy.put(variant, Objects.requireNonNull(entry.getValue(), "block of variant " + variant));
        }
        blocks = Collections.unmodifiableMap(copy);
    }

    public static BlockSet of(Block source, Block stairs, Block slab) {
        Map<BlockVariant, Block> blocks = new EnumMap<>(BlockVariant.class);
        blocks.put(BlockVariant.STAIRS, stairs);
        blocks.put(BlockVariant.SLAB, slab);
        return new BlockSet(source, blocks);
    }

    public String name() {
        return DatagenUtils.path(source);
    }

    public Set<BlockVariant> variants() {
        return blocks.keySet();
    }

    public Block block(BlockVariant variant) {
        Block block = blocks.get(variant);
        if (block == null) {
            throw new IllegalArgumentException("BlockSet " + name() + " has no variant " + variant);
        }
        return block;
    }

    public BlockDefinition definition(BlockVariant variant) {
        Identifier cube = DatagenUtils.blockId(source);
        BlockModelType type = variant.modelType();
        return new BlockDefinition(block(variant), type, BlockTextures.uniform(cube), type.requiresBaseModel() ? cube : null);
    }
}
