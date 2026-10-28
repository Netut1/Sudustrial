package com.netut.sudustrial.client.datagen.core.item;

import net.minecraft.world.item.Item;

public record ItemDefinition(Item item, ItemModelType type) {
    public static ItemDefinition flat(Item item) {
        return new ItemDefinition(item, ItemModelType.FLAT);
    }
}
