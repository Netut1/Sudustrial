package com.netut.sudustrial.register.block.tnt;

import com.netut.sudustrial.Sudustrial;
import com.netut.sudustrial.tnt.block.MiningTntBlock;
import com.netut.sudustrial.tnt.type.block.MiningTntType;
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

public class RegisterMiningTnt {
    public static final Map<MiningTntType, Block> MINING_TNT_BLOCKS = new HashMap<>();
    public static final Map<MiningTntType, Item> MINING_TNT_ITEMS = new HashMap<>();

    public static void register() {
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
    }
}
