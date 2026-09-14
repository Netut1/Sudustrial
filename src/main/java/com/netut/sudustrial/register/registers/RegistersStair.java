package com.netut.sudustrial.register.registers;

import com.netut.sudustrial.Sudustrial;
import com.netut.sudustrial.block.concrete.ConcretePowderStairBlock;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.Map;

public class RegistersStair{
    public static void registerStair(String path, Block baseBlock, DyeColor color, Map<DyeColor, Block> blockMap, Map<DyeColor, Item> itemMap) {
        Identifier loc = Identifier.fromNamespaceAndPath(Sudustrial.MOD_ID, path);
        ResourceKey<Block> blockKey = ResourceKey.create(Registries.BLOCK, loc);
        ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, loc);

        BlockBehaviour.Properties props = BlockBehaviour.Properties.ofFullCopy(baseBlock)
                .noOcclusion()
                .setId(blockKey);
        Block stairBlock = new StairBlock(baseBlock.defaultBlockState(), props);

        Registry.register(BuiltInRegistries.BLOCK, blockKey, stairBlock);
        blockMap.put(color, stairBlock);

        Item.Properties itemProps = new Item.Properties().setId(itemKey);
        Item item = new BlockItem(stairBlock, itemProps);

        Registry.register(BuiltInRegistries.ITEM, itemKey, item);
        itemMap.put(color, item);
    }

    public static void registerPowderStair(String path, Block basePowderBlock, Block concreteStairBlock, DyeColor color, Map<DyeColor, Block> blockMap, Map<DyeColor, Item> itemMap) {
        Identifier loc = Identifier.fromNamespaceAndPath(Sudustrial.MOD_ID, path);
        ResourceKey<Block> blockKey = ResourceKey.create(Registries.BLOCK, loc);
        ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, loc);

        BlockBehaviour.Properties props = BlockBehaviour.Properties.ofFullCopy(basePowderBlock)
                .noOcclusion()
                .setId(blockKey);
        Block stairBlock = new ConcretePowderStairBlock(concreteStairBlock, basePowderBlock.defaultBlockState(), props);

        Registry.register(BuiltInRegistries.BLOCK, blockKey, stairBlock);
        blockMap.put(color, stairBlock);

        Item.Properties itemProps = new Item.Properties().setId(itemKey);
        Item item = new BlockItem(stairBlock, itemProps);

        Registry.register(BuiltInRegistries.ITEM, itemKey, item);
        itemMap.put(color, item);
    }
}
