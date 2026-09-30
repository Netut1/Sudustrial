package com.netut.sudustrial.register.block.tnt;

import com.netut.sudustrial.tnt.type.block.ElementalTntType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.HashMap;
import java.util.Map;

public class RegisterElementalTnt {
    public static final Map<ElementalTntType, Block> ELEMENTAL_TNT_BLOCKS = new HashMap<>();
    public static final Map<ElementalTntType, Item> ELEMENTAL_TNT_ITEMS = new HashMap<>();

    public static void register() {
        RegisterTnt.registerAll(ElementalTntType.values(), ELEMENTAL_TNT_BLOCKS, ELEMENTAL_TNT_ITEMS);
    }
}
