package com.netut.sudustrial.client.datagen.core.item;

import net.minecraft.world.item.Item;

import java.util.Objects;

public record ItemDefinition(Item item, ItemModelType type) {
    public ItemDefinition {
        Objects.requireNonNull(item, "item (is it registered?)");
        Objects.requireNonNull(type, "type");
    }

    public static ItemDefinition flat(Item item) {
        return new ItemDefinition(item, ItemModelType.FLAT);
    }
}
