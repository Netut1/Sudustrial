package com.netut.sudustrial.register.registers;

import com.netut.sudustrial.Sudustrial;
import com.netut.sudustrial.block.concrete.ConcretePowderSlabBlock;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.Map;

public class RegistersSlab {
    public static void registerSlab(String path, Block baseBlock, DyeColor color, Map<DyeColor, Block> blockMap, Map<DyeColor, Item> itemMap) {
        Identifier loc = Identifier.fromNamespaceAndPath(Sudustrial.MOD_ID, path);
        ResourceKey<Block> blockKey = ResourceKey.create(Registries.BLOCK, loc);
        ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, loc);

        BlockBehaviour.Properties props = BlockBehaviour.Properties.ofFullCopy(baseBlock)
                .noOcclusion()
                .setId(blockKey);
        Block slabBlock = new SlabBlock(props);

        Registry.register(BuiltInRegistries.BLOCK, blockKey, slabBlock);
        blockMap.put(color, slabBlock);

        Item.Properties itemProps = new Item.Properties().setId(itemKey);
        Item item = new BlockItem(slabBlock, itemProps);

        Registry.register(BuiltInRegistries.ITEM, itemKey, item);
        itemMap.put(color, item);
    }

    public static void registerPowderSlab(String path, Block basePowderBlock, Block concreteSlabBlock, DyeColor color, Map<DyeColor, Block> blockMap, Map<DyeColor, Item> itemMap) {
        Identifier loc = Identifier.fromNamespaceAndPath(Sudustrial.MOD_ID, path);
        ResourceKey<Block> blockKey = ResourceKey.create(Registries.BLOCK, loc);
        ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, loc);

        BlockBehaviour.Properties props = BlockBehaviour.Properties.ofFullCopy(basePowderBlock)
                .noOcclusion()
                .setId(blockKey);
        Block slabBlock = new ConcretePowderSlabBlock(concreteSlabBlock, props);

        Registry.register(BuiltInRegistries.BLOCK, blockKey, slabBlock);
        blockMap.put(color, slabBlock);

        Item.Properties itemProps = new Item.Properties().setId(itemKey);
        Item item = new BlockItem(slabBlock, itemProps);

        Registry.register(BuiltInRegistries.ITEM, itemKey, item);
        itemMap.put(color, item);
    }
}
