package com.netut.sudustrial.register.block.tnt;

import com.netut.sudustrial.tnt.type.block.SpawnerTntType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.HashMap;
import java.util.Map;

public class RegisterSpawnerTnt {
    public static final Map<SpawnerTntType, Block> SPAWNER_TNT_BLOCKS = new HashMap<>();
    public static final Map<SpawnerTntType, Item> SPAWNER_TNT_ITEMS = new HashMap<>();

    public static void register() {
        RegisterTnt.registerAll(SpawnerTntType.values(), SPAWNER_TNT_BLOCKS, SPAWNER_TNT_ITEMS);
    }
}
