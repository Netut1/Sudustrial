package com.netut.sudustrial.client.datagen.core;

import com.netut.sudustrial.Sudustrial;
import net.minecraft.advancements.triggers.Criterion;
import net.minecraft.advancements.triggers.InventoryChangeTrigger;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;

public final class DatagenUtils {

    private DatagenUtils() {}

    public static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(Sudustrial.MOD_ID, path);
    }

    public static Material blockMaterial(String textureName) {
        return new Material(id("block/" + textureName));
    }

    public static Criterion<?> has(Item item) {
        return InventoryChangeTrigger.TriggerInstance.hasItems(item);
    }

    public static Item item(String id) {
        Identifier identifier = Identifier.tryParse(id);
        if (identifier == null) {throw new IllegalArgumentException("Invalid item id: " + id);}

        return BuiltInRegistries.ITEM.get(identifier)
                .orElseThrow(() -> new IllegalStateException("Unknown item for datagen: " + id))
                .value();
    }
}
