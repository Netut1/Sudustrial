package com.netut.sudustrial.client.datagen.core;

import net.minecraft.advancements.triggers.Criterion;
import net.minecraft.advancements.triggers.InventoryChangeTrigger;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public final class DatagenUtils {
    public static final String NAMESPACE = "sudustrial";
    private DatagenUtils() {}

    public static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(NAMESPACE, path);
    }

    public static Identifier blockId(String name) {
        return id("block/" + name);
    }

    public static Identifier blockId(Block block) {
        Identifier key = BuiltInRegistries.BLOCK.getKey(block);
        return Identifier.fromNamespaceAndPath(key.getNamespace(), "block/" + key.getPath());
    }

    public static Material blockMaterial(String name) {
        return new Material(blockId(name));
    }

    public static String path(Block block) {
        return BuiltInRegistries.BLOCK.getKey(block).getPath();
    }

    public static String path(Item item) {
        return BuiltInRegistries.ITEM.getKey(item).getPath();
    }

    public static Criterion<?> has(Item item) {
        return InventoryChangeTrigger.TriggerInstance.hasItems(item);
    }
}
