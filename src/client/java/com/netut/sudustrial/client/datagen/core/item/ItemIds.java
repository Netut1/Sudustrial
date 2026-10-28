package com.netut.sudustrial.client.datagen.core.item;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;

public final class ItemIds {
    private ItemIds() {}

    public static String path(Item item) {
        return BuiltInRegistries.ITEM.getKey(item).getPath();
    }
}
