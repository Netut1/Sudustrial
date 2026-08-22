package com.netut.sudustrial.register;

import com.netut.sudustrial.Sudustrial;
import com.netut.sudustrial.item.tnt.nuclear.CoreItemType;
import com.netut.sudustrial.item.tnt.nuclear.NuclearAntidoteItem;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;

import java.util.HashMap;
import java.util.Map;

public class ModItems {
    public static final Map<CoreItemType, Item> CORE_ITEMS = new HashMap<>();
    public static Item NUCLEAR_ANTIDOTE;

    public static void registerModItems() {
        for (CoreItemType type : CoreItemType.values()) {
            Identifier location = Identifier.fromNamespaceAndPath(Sudustrial.MOD_ID, type.getSerializedName());
            ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, location);

            Item.Properties itemProperties = new Item.Properties().setId(itemKey);
            Item item = new Item(itemProperties);
            Registry.register(BuiltInRegistries.ITEM, itemKey, item);
            CORE_ITEMS.put(type, item);
        }

        Identifier antidoteLocation = Identifier.fromNamespaceAndPath(Sudustrial.MOD_ID, "nuclear_antidote");
        ResourceKey<Item> antidoteKey = ResourceKey.create(Registries.ITEM, antidoteLocation);

        FoodProperties antidoteFood = new FoodProperties.Builder()
                .nutrition(0)
                .saturationModifier(0.0F)
                .alwaysEdible() // можно есть даже на полный голод
                .build();

        Item.Properties antidoteProperties = new Item.Properties()
                .setId(antidoteKey)
                .food(antidoteFood);

        NUCLEAR_ANTIDOTE = new NuclearAntidoteItem(antidoteProperties);
        Registry.register(BuiltInRegistries.ITEM, antidoteKey, NUCLEAR_ANTIDOTE);
    }
}
