package com.netut.sudustrial.register.block.tnt;

import com.netut.sudustrial.Sudustrial;
import com.netut.sudustrial.tnt.block.NuclearTntBlock;
import com.netut.sudustrial.tnt.type.block.NuclearTntType;
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

public class RegisterNuclearTnt {
    public static final Map<NuclearTntType, Block> NUCLEAR_TNT_BLOCKS = new HashMap<>();
    public static final Map<NuclearTntType, Item> NUCLEAR_TNT_ITEMS = new HashMap<>();

    public static void register() {
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
    }
}
