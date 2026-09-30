package com.netut.sudustrial.register.block.tnt;

import com.netut.sudustrial.tnt.type.block.MiningTntType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.HashMap;
import java.util.Map;

public class RegisterMiningTnt {
    public static final Map<MiningTntType, Block> MINING_TNT_BLOCKS = new HashMap<>();
    public static final Map<MiningTntType, Item> MINING_TNT_ITEMS = new HashMap<>();

    public static void register() {
        RegisterTnt.registerAll(MiningTntType.values(), MINING_TNT_BLOCKS, MINING_TNT_ITEMS);
    }
}
