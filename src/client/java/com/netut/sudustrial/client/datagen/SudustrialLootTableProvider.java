package com.netut.sudustrial.client.datagen;

import com.netut.sudustrial.register.block.nuclear_block.RegisterNuclearCore;
import com.netut.sudustrial.register.block.tnt.*;
import com.netut.sudustrial.tnt.type.block.*;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;
import net.minecraft.core.HolderLookup;
import com.netut.sudustrial.register.block.potion_cauldron.RegisterPotionCauldron;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class SudustrialLootTableProvider extends FabricBlockLootSubProvider {

    public SudustrialLootTableProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    public void generate() {
        for (TntType type : TntType.values()) {
            dropSelf(RegisterTnt.TNT_BLOCKS.get(type));
        }
        for (ElementalTntType type : ElementalTntType.values()) {
            dropSelf(RegisterElementalTnt.ELEMENTAL_TNT_BLOCKS.get(type));
        }

        for (SpawnerTntType type : SpawnerTntType.values()) {
            dropSelf(RegisterSpawnerTnt.SPAWNER_TNT_BLOCKS.get(type));
        }

        for (EffectTntType type : EffectTntType.values()) {
            dropSelf(RegisterEffectTnt.EFFECT_TNT_BLOCKS.get(type));
        }

        for (MiningTntType type : MiningTntType.values()) {
            dropSelf(RegisterMiningTnt.MINING_TNT_BLOCKS.get(type));
        }

        for (NuclearTntType type : NuclearTntType.values()) {
            dropSelf(RegisterNuclearTnt.NUCLEAR_TNT_BLOCKS.get(type));
        }

        dropSelf(RegisterNuclearCore.NUCLEAR_CORE_BLOCK);

        Block cauldron = RegisterPotionCauldron.POTION_CAULDRON_BLOCK;

        add(cauldron, LootTable.lootTable().
                withPool(LootPool.lootPool().setRolls(
                        ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(cauldron)
                ))
        );
    }

    @Override
    public @NonNull String getName() {
        return "SudustrialLootTableProvider";
    }
}
