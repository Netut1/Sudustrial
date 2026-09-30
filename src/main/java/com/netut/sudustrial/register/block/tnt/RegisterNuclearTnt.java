package com.netut.sudustrial.register.block.tnt;

import com.netut.sudustrial.tnt.type.block.NuclearTntType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.HashMap;
import java.util.Map;

public class RegisterNuclearTnt {
    public static final Map<NuclearTntType, Block> NUCLEAR_TNT_BLOCKS = new HashMap<>();
    public static final Map<NuclearTntType, Item> NUCLEAR_TNT_ITEMS = new HashMap<>();

    public static void register() {
        RegisterTnt.registerAll(NuclearTntType.values(), NUCLEAR_TNT_BLOCKS, NUCLEAR_TNT_ITEMS);
    }
}
