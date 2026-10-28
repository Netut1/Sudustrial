package com.netut.sudustrial.client.datagen.core.block;

import com.netut.sudustrial.client.datagen.core.Registered;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;

import java.util.EnumMap;
import java.util.Map;
import java.util.Set;

public record BlockSet(Block source, Map<BlockVariant, Block> blocks) {
    public BlockSet {
        blocks = new EnumMap<>(blocks);
    }

    public static BlockSet of(Block source, Block stairs, Block slab) {
        Map<BlockVariant, Block> blocks = new EnumMap<>(BlockVariant.class);
        blocks.put(BlockVariant.STAIRS, stairs);
        blocks.put(BlockVariant.SLAB, slab);
        return new BlockSet(source, blocks);
    }

    public String name() {
        return BlockIds.path(source);
    }

    public Set<BlockVariant> variants() {
        return blocks.keySet();
    }

    public Block block(BlockVariant variant) {
        return Registered.require(blocks, variant);
    }

    public String texture() {
        Identifier key = BuiltInRegistries.BLOCK.getKey(source);
        return key.getNamespace() + ":block/" + key.getPath();
    }

    public BlockDefinition definition(BlockVariant variant) {
        String texture = texture();
        BlockTextures textures = new BlockTextures(texture, texture, texture, texture, texture, texture, texture);
        return new BlockDefinition(block(variant), variant.modelType(), textures, variant.usesBaseModel() ? texture : null);
    }
}
