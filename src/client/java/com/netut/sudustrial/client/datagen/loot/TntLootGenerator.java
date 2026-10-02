package com.netut.sudustrial.client.datagen.loot;

import com.netut.sudustrial.register.block.tnt.*;
import com.netut.sudustrial.tnt.type.block.*;
import net.minecraft.world.level.block.Block;

import java.util.Map;
import java.util.function.Consumer;

public final class TntLootGenerator {

    private TntLootGenerator() {}

    public static void generate(Consumer<Block> dropSelf) {
        dropAll(dropSelf, TntType.values(),              RegisterTnt.TNT_BLOCKS);
        dropAll(dropSelf, ElementalTntType.values(),     RegisterElementalTnt.ELEMENTAL_TNT_BLOCKS);
        dropAll(dropSelf, SpawnerTntType.values(),       RegisterSpawnerTnt.SPAWNER_TNT_BLOCKS);
        dropAll(dropSelf, EffectTntType.values(),        RegisterEffectTnt.EFFECT_TNT_BLOCKS);
        dropAll(dropSelf, MiningTntType.values(),        RegisterMiningTnt.MINING_TNT_BLOCKS);
        dropAll(dropSelf, NuclearTntType.values(),       RegisterNuclearTnt.NUCLEAR_TNT_BLOCKS);
    }

    private static <K> void dropAll(
            Consumer<Block> dropSelf,
            K[] keys,
            Map<K, Block> registry
    ) {
        for (K key : keys) {
            Block block = registry.get(key);
            if (block == null) {
                throw new IllegalStateException(
                        "No block registered for TNT type: " + key
                );
            }
            dropSelf.accept(block);
        }
    }
}
