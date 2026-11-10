package com.netut.sudustrial.register.item.nuclear_antidote;

import com.netut.sudustrial.Sudustrial;
import com.netut.sudustrial.tnt.item.NuclearAntidoteItem;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;

public class RegisterNuclearAntidote {
    public static Item NUCLEAR_ANTIDOTE;

    public static void register() {
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
