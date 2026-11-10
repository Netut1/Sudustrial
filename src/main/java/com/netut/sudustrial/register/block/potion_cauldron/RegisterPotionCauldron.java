package com.netut.sudustrial.register.block.potion_cauldron;

import com.netut.sudustrial.Sudustrial;
import com.netut.sudustrial.potion_cauldron.block.PotionCauldronBlock;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class RegisterPotionCauldron {
    public static Block POTION_CAULDRON_BLOCK;
    public static Item POTION_CAULDRON_ITEM;

    public static void register(){
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
