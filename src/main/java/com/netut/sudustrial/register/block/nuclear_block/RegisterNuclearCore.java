package com.netut.sudustrial.register.block.nuclear_block;

import com.netut.sudustrial.Sudustrial;
import com.netut.sudustrial.tnt.block.NuclearCoreBlock;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class RegisterNuclearCore {
    public static Block NUCLEAR_CORE_BLOCK;
    public static Item NUCLEAR_CORE_ITEM;

    public static void register(){
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
    }
}
