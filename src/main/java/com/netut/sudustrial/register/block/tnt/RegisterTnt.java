package com.netut.sudustrial.register.block.tnt;

import com.netut.sudustrial.Sudustrial;
import com.netut.sudustrial.tnt.block.CustomTntBlock;
import com.netut.sudustrial.tnt.type.block.TntType;
import com.netut.sudustrial.tnt.block.TntVariant;
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

public class RegisterTnt {
    public static final Map<TntType, Block> TNT_BLOCKS = new HashMap<>();
    public static final Map<TntType, Item> TNT_ITEMS = new HashMap<>();

    // Общие для всех видов: поиск по id (нужен сущности) и по варианту (блок для рендера)
    private static final Map<String, TntVariant> BY_ID = new HashMap<>();
    private static final Map<String, Block> BLOCK_BY_ID = new HashMap<>();

    public static void register() {
        registerAll(TntType.values(), TNT_BLOCKS, TNT_ITEMS);
    }

    public static <T extends TntVariant> void registerAll(T[] types, Map<T, Block> blocks, Map<T, Item> items) {
        for (T type : types) {
            Identifier location = Identifier.fromNamespaceAndPath(Sudustrial.MOD_ID, type.getSerializedName());
            ResourceKey<Block> blockKey = ResourceKey.create(Registries.BLOCK, location);
            ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, location);

            // 1. Создание и регистрация Блока
            BlockBehaviour.Properties blockProperties = BlockBehaviour.Properties.ofFullCopy(Blocks.TNT).setId(blockKey);
            Block block = new CustomTntBlock(type, blockProperties);
            Registry.register(BuiltInRegistries.BLOCK, blockKey, block);
            blocks.put(type, block);

            // 2. Создание и регистрация Предмета
            Item.Properties itemProperties = new Item.Properties().setId(itemKey);
            Item item = new BlockItem(block, itemProperties);
            Registry.register(BuiltInRegistries.ITEM, itemKey, item);
            items.put(type, item);

            BY_ID.put(type.getSerializedName(), type);
            BLOCK_BY_ID.put(type.getSerializedName(), block);
        }
    }

    public static TntVariant byId(String id) {
        return BY_ID.get(id);
    }

    public static Block blockOf(TntVariant variant) {
        return BLOCK_BY_ID.get(variant.getSerializedName());
    }
}
