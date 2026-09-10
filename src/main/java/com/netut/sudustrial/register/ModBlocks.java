package com.netut.sudustrial.register;

import com.netut.sudustrial.Sudustrial;
import com.netut.sudustrial.block.cauldron.PotionCauldronBlock;
import com.netut.sudustrial.block.tnt.nuclear_block.NuclearAirBlock;
import com.netut.sudustrial.block.tnt.nuclear_block.NuclearCoreBlock;
import com.netut.sudustrial.block.tnt.state_block.*;
import com.netut.sudustrial.block.tnt.type.*;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.HashMap;
import java.util.Map;

public class ModBlocks {
    public static final Map<TntType, Block> TNT_BLOCKS = new HashMap<>();
    public static final Map<TntType, Item> TNT_ITEMS = new HashMap<>();

    public static final Map<ElementalTntType, Block> ELEMENTAL_TNT_BLOCKS = new HashMap<>();
    public static final Map<ElementalTntType, Item> ELEMENTAL_TNT_ITEMS = new HashMap<>();

    public static final Map<SpawnerTntType, Block> SPAWNER_TNT_BLOCKS = new HashMap<>();
    public static final Map<SpawnerTntType, Item> SPAWNER_TNT_ITEMS = new HashMap<>();

    public static final Map<EffectTntType, Block> EFFECT_TNT_BLOCKS = new HashMap<>();
    public static final Map<EffectTntType, Item> EFFECT_TNT_ITEMS = new HashMap<>();

    public static final Map<MiningTntType, Block> MINING_TNT_BLOCKS = new HashMap<>();
    public static final Map<MiningTntType, Item> MINING_TNT_ITEMS = new HashMap<>();

    public static final Map<NuclearTntType, Block> NUCLEAR_TNT_BLOCKS = new HashMap<>();
    public static final Map<NuclearTntType, Item> NUCLEAR_TNT_ITEMS = new HashMap<>();

    public static Block NUCLEAR_AIR;
    public static Block NUCLEAR_CORE_BLOCK;
    public static Item NUCLEAR_CORE_ITEM;

    public static Block POTION_CAULDRON_BLOCK;
    public static Item POTION_CAULDRON_ITEM;

    public static void registerModBlocks() {
        for (TntType type : TntType.values()) {
            Identifier location = Identifier.fromNamespaceAndPath(Sudustrial.MOD_ID, type.getSerializedName());
            ResourceKey<Block> blockKey = ResourceKey.create(Registries.BLOCK, location);
            ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, location);

            // 1. Создание и регистрация Блока
            BlockBehaviour.Properties blockProperties = BlockBehaviour.Properties.ofFullCopy(Blocks.TNT).setId(blockKey);
            Block block = new CustomTntBlock(type, blockProperties);
            Registry.register(BuiltInRegistries.BLOCK, blockKey, block);
            TNT_BLOCKS.put(type, block);

            // 2. Создание и регистрация Предмета
            Item.Properties itemProperties = new Item.Properties().setId(itemKey);
            Item item = new BlockItem(block, itemProperties);
            Registry.register(BuiltInRegistries.ITEM, itemKey, item);
            TNT_ITEMS.put(type, item);
        }

        for (ElementalTntType type : ElementalTntType.values()) {
            Identifier location = Identifier.fromNamespaceAndPath(Sudustrial.MOD_ID, type.getSerializedName());
            ResourceKey<Block> blockKey = ResourceKey.create(Registries.BLOCK, location);
            ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, location);

            BlockBehaviour.Properties blockProperties = BlockBehaviour.Properties.ofFullCopy(Blocks.TNT).setId(blockKey);
            Block block = new ElementalTntBlock(type, blockProperties);
            Registry.register(BuiltInRegistries.BLOCK, blockKey, block);
            ELEMENTAL_TNT_BLOCKS.put(type, block);

            Item.Properties itemProperties = new Item.Properties().setId(itemKey);
            Item item = new BlockItem(block, itemProperties);
            Registry.register(BuiltInRegistries.ITEM, itemKey, item);
            ELEMENTAL_TNT_ITEMS.put(type, item);
        }

        for (SpawnerTntType type : SpawnerTntType.values()) {
            Identifier location = Identifier.fromNamespaceAndPath(Sudustrial.MOD_ID, type.getSerializedName());
            ResourceKey<Block> blockKey = ResourceKey.create(Registries.BLOCK, location);
            ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, location);

            BlockBehaviour.Properties blockProperties = BlockBehaviour.Properties.ofFullCopy(Blocks.TNT).setId(blockKey);
            Block block = new SpawnerTntBlock(type, blockProperties);
            Registry.register(BuiltInRegistries.BLOCK, blockKey, block);
            SPAWNER_TNT_BLOCKS.put(type, block);

            Item.Properties itemProperties = new Item.Properties().setId(itemKey);
            Item item = new BlockItem(block, itemProperties);
            Registry.register(BuiltInRegistries.ITEM, itemKey, item);
            SPAWNER_TNT_ITEMS.put(type, item);
        }

        for (EffectTntType type : EffectTntType.values()) {
            Identifier location = Identifier.fromNamespaceAndPath(Sudustrial.MOD_ID, type.getSerializedName());
            ResourceKey<Block> blockKey = ResourceKey.create(Registries.BLOCK, location);
            ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, location);

            BlockBehaviour.Properties blockProperties = BlockBehaviour.Properties.ofFullCopy(Blocks.TNT).setId(blockKey);
            Block block = new EffectTntBlock(type, blockProperties);
            Registry.register(BuiltInRegistries.BLOCK, blockKey, block);
            EFFECT_TNT_BLOCKS.put(type, block);

            Item.Properties itemProperties = new Item.Properties().setId(itemKey);
            Item item = new BlockItem(block, itemProperties);
            Registry.register(BuiltInRegistries.ITEM, itemKey, item);
            EFFECT_TNT_ITEMS.put(type, item);
        }

        for (MiningTntType type : MiningTntType.values()) {
            Identifier location = Identifier.fromNamespaceAndPath(Sudustrial.MOD_ID, type.getSerializedName());
            ResourceKey<Block> blockKey = ResourceKey.create(Registries.BLOCK, location);
            ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, location);

            BlockBehaviour.Properties blockProperties = BlockBehaviour.Properties.ofFullCopy(Blocks.TNT).setId(blockKey);
            Block block = new MiningTntBlock(type, blockProperties);
            Registry.register(BuiltInRegistries.BLOCK, blockKey, block);
            MINING_TNT_BLOCKS.put(type, block);

            Item.Properties itemProperties = new Item.Properties().setId(itemKey);
            Item item = new BlockItem(block, itemProperties);
            Registry.register(BuiltInRegistries.ITEM, itemKey, item);
            MINING_TNT_ITEMS.put(type, item);
        }

        for (NuclearTntType type : NuclearTntType.values()) {
            Identifier location = Identifier.fromNamespaceAndPath(Sudustrial.MOD_ID, type.getSerializedName());
            ResourceKey<Block> blockKey = ResourceKey.create(Registries.BLOCK, location);
            ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, location);

            BlockBehaviour.Properties blockProperties = BlockBehaviour.Properties.ofFullCopy(Blocks.TNT).setId(blockKey);
            Block block = new NuclearTntBlock(type, blockProperties);
            Registry.register(BuiltInRegistries.BLOCK, blockKey, block);
            NUCLEAR_TNT_BLOCKS.put(type, block);

            Item.Properties itemProperties = new Item.Properties().setId(itemKey);
            Item item = new BlockItem(block, itemProperties);
            Registry.register(BuiltInRegistries.ITEM, itemKey, item);
            NUCLEAR_TNT_ITEMS.put(type, item);
        }

        // Ядерный воздух: без предмета, ставится только кодом
        Identifier nuclearAirLoc = Identifier.fromNamespaceAndPath(Sudustrial.MOD_ID, "nuclear_air");
        ResourceKey<Block> nuclearAirKey = ResourceKey.create(Registries.BLOCK, nuclearAirLoc);
        BlockBehaviour.Properties nuclearAirProperties = BlockBehaviour.Properties.of()
                .noCollision()
                .replaceable()
                .noOcclusion()
                .lightLevel(state -> 3)
                .noLootTable()
                .setId(nuclearAirKey);
        NUCLEAR_AIR = new NuclearAirBlock(nuclearAirProperties);
        Registry.register(BuiltInRegistries.BLOCK, nuclearAirKey, NUCLEAR_AIR);

        // Ядро: добываемый кубический блок с предметом
        Identifier nuclearCoreLoc = Identifier.fromNamespaceAndPath(Sudustrial.MOD_ID, "nuclear_core");
        ResourceKey<Block> nuclearCoreBlockKey = ResourceKey.create(Registries.BLOCK, nuclearCoreLoc);
        ResourceKey<Item> nuclearCoreItemKey = ResourceKey.create(Registries.ITEM, nuclearCoreLoc);

        BlockBehaviour.Properties nuclearCoreProperties = BlockBehaviour.Properties.of()
                .strength(100.0F, 1200.0F)
                .noOcclusion()
                .lightLevel(state -> 3)
                .setId(nuclearCoreBlockKey);
        NUCLEAR_CORE_BLOCK = new NuclearCoreBlock(nuclearCoreProperties);
        Registry.register(BuiltInRegistries.BLOCK, nuclearCoreBlockKey, NUCLEAR_CORE_BLOCK);

        Item.Properties nuclearCoreItemProperties = new Item.Properties().setId(nuclearCoreItemKey);
        NUCLEAR_CORE_ITEM = new BlockItem(NUCLEAR_CORE_BLOCK, nuclearCoreItemProperties);
        Registry.register(BuiltInRegistries.ITEM, nuclearCoreItemKey, NUCLEAR_CORE_ITEM);

        // Таинственное зелье
        Identifier cauldronLoc = Identifier.fromNamespaceAndPath(Sudustrial.MOD_ID, "potion_cauldron");
        ResourceKey<Block> cauldronBlockKey = ResourceKey.create(Registries.BLOCK, cauldronLoc);
        ResourceKey<Item> cauldronItemKey = ResourceKey.create(Registries.ITEM, cauldronLoc);

        BlockBehaviour.Properties cauldronProperties = BlockBehaviour.Properties.of()
                .strength(2.0F, 6.0F)
                .noOcclusion()
                .setId(cauldronBlockKey);
        POTION_CAULDRON_BLOCK = new PotionCauldronBlock(cauldronProperties);
        Registry.register(BuiltInRegistries.BLOCK, cauldronBlockKey, POTION_CAULDRON_BLOCK);

        Item.Properties cauldronItemProperties = new Item.Properties().setId(cauldronItemKey);
        POTION_CAULDRON_ITEM = new BlockItem(POTION_CAULDRON_BLOCK, cauldronItemProperties);
        Registry.register(BuiltInRegistries.ITEM, cauldronItemKey, POTION_CAULDRON_ITEM);
    }
}
