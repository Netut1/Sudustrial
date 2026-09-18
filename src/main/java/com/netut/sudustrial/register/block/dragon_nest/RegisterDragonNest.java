package com.netut.sudustrial.register.block.dragon_nest;

import com.netut.sudustrial.Sudustrial;
import com.netut.sudustrial.dragon.block.DragonNestBlock;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class RegisterDragonNest {
    public static Block DRAGON_NEST_BLOCK;
    public static Item DRAGON_NEST_ITEM;

    public static void register() {
        Identifier nestLoc = Identifier.fromNamespaceAndPath(Sudustrial.MOD_ID, "dragon_nest");
        ResourceKey<Block> nestBlockKey = ResourceKey.create(Registries.BLOCK, nestLoc);
        ResourceKey<Item> nestItemKey = ResourceKey.create(Registries.ITEM, nestLoc);

        BlockBehaviour.Properties nestProperties = BlockBehaviour.Properties.of()
                .strength(2.0F, 5.0F)
                .noOcclusion()
                .setId(nestBlockKey);
        DRAGON_NEST_BLOCK = new DragonNestBlock(nestProperties);
        Registry.register(BuiltInRegistries.BLOCK, nestBlockKey, DRAGON_NEST_BLOCK);
        FlammableBlockRegistry.getDefaultInstance().add(DRAGON_NEST_BLOCK, 30, 60);

        Item.Properties nestItemProperties = new Item.Properties().setId(nestItemKey);
        DRAGON_NEST_ITEM = new BlockItem(DRAGON_NEST_BLOCK, nestItemProperties);
        Registry.register(BuiltInRegistries.ITEM, nestItemKey, DRAGON_NEST_ITEM);
    }
}
