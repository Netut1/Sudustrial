package com.netut.sudustrial.client.datagen.feature.potion_cauldron;

import com.netut.sudustrial.client.datagen.core.DatagenFeature;
import com.netut.sudustrial.register.block.potion_cauldron.RegisterPotionCauldron;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

final class LootGenerator {
    private LootGenerator() {}

    static void generate(DatagenFeature.LootSink loot) {
        Block cauldron = RegisterPotionCauldron.POTION_CAULDRON_BLOCK;

        loot.add(cauldron, LootTable.lootTable().withPool(
                LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(cauldron))));
    }
}
