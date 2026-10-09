package com.netut.sudustrial.register.block.texture.po_2;

import com.netut.sudustrial.Sudustrial;
import com.netut.sudustrial.texture_block.soviet_architecture.po_2.Po2CornerBlock;
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

import java.util.function.Function;


public class RegisterPO_2Fence {
    public static Block PO_2_PANEL_BLOCK;
    public static Item PO_2_PANEL_ITEM;
    public static Block PO_2_CORNER_BLOCK;

    public static void register() {
        PO_2_PANEL_BLOCK = registerBlock("po_2_panel", Po2PanelBlock::new);
        PO_2_PANEL_ITEM = registerItem(PO_2_PANEL_BLOCK);

        PO_2_CORNER_BLOCK = registerBlock("po_2_corner", Po2CornerBlock::new);
    }

    private static Block registerBlock(String path, Function<BlockBehaviour.Properties, Block> factory) {
        Identifier loc = Identifier.fromNamespaceAndPath(Sudustrial.MOD_ID, path);
        ResourceKey<Block> blockKey = ResourceKey.create(Registries.BLOCK, loc);

        BlockBehaviour.Properties properties = BlockBehaviour.Properties.of()
                .mapColor(MapColor.STONE)
                .sound(SoundType.STONE)
                .strength(2.0F, 6.0F)
                .noOcclusion()
                .setId(blockKey);

        return Registry.register(BuiltInRegistries.BLOCK, blockKey, factory.apply(properties));
    }

    private static Item registerItem(Block block) {
        Identifier loc = Identifier.fromNamespaceAndPath(Sudustrial.MOD_ID, "po_2_panel");
        ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, loc);

        Item.Properties properties = new Item.Properties().setId(itemKey);
        return Registry.register(BuiltInRegistries.ITEM, itemKey, new BlockItem(block, properties));
    }
}
