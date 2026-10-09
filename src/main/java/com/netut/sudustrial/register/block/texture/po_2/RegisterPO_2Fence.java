package com.netut.sudustrial.register.block.texture.po_2;

import com.netut.sudustrial.Sudustrial;
import com.netut.sudustrial.texture_block.soviet_architecture.po_2.Po2PanelBlock;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

public final class RegisterPO_2Fence {
    public static Block PO_2_PANEL_BLOCK;
    public static Item PO_2_PANEL_ITEM;

    private RegisterPO_2Fence() {}

    public static void register() {
        Identifier blockId = Identifier.fromNamespaceAndPath(Sudustrial.MOD_ID, "po_2_panel");

        ResourceKey<Block> blockKey = ResourceKey.create(Registries.BLOCK, blockId);

        BlockBehaviour.Properties properties = BlockBehaviour.Properties.of()
                .mapColor(MapColor.STONE)
                .sound(SoundType.STONE)
                .strength(2.0F, 6.0F)
                .noOcclusion()
                .setId(blockKey);

        PO_2_PANEL_BLOCK = Registry.register(
                BuiltInRegistries.BLOCK,
                blockKey,
                new Po2PanelBlock(properties)
        );

        ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, blockId);

        PO_2_PANEL_ITEM = Registry.register(
                BuiltInRegistries.ITEM,
                itemKey,
                new BlockItem(PO_2_PANEL_BLOCK, new Item.Properties().setId(itemKey))
        );
    }
}
