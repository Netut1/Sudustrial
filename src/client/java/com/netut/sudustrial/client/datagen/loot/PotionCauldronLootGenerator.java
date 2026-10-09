package com.netut.sudustrial.client.datagen.loot;

import com.netut.sudustrial.register.block.potion_cauldron.RegisterPotionCauldron;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

import java.util.function.BiConsumer;

public final class PotionCauldronLootGenerator {

    private PotionCauldronLootGenerator() {}

    public static void generate(BiConsumer<Block, LootTable.Builder> add) {
        Block cauldron = RegisterPotionCauldron.POTION_CAULDRON_BLOCK;

        add.accept(cauldron, LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(cauldron))));
    }
}
