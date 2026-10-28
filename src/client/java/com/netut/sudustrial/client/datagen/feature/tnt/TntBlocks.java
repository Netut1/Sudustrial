package com.netut.sudustrial.client.datagen.feature.tnt;

import com.netut.sudustrial.client.datagen.core.block.BlockDefinition;
import com.netut.sudustrial.client.datagen.core.block.BlockTextures;
import com.netut.sudustrial.tnt.type.block.TntType;
import net.minecraft.world.level.block.Block;

import java.util.List;

final class TntBlocks {
    private TntBlocks() {}

    static List<BlockDefinition> definitions(TntCatalog catalog) {
        return catalog.entries().stream().map(TntBlocks::definition).toList();
    }

    private static BlockDefinition definition(TntCatalog.Entry entry) {
        if (entry.variant() == TntType.PARTY) {
            return definition(entry.block(), "party_tnt_side", "standard_tnt_top", "standard_tnt_bottom", 10);
        }
        return definition(entry.block(), entry.base() + "_side", entry.base() + "_top", entry.base() + "_bottom", entry.tier());
    }

    private static BlockDefinition definition(Block block, String side, String top, String bottom, int tier) {
        String overlay = tier >= 2 ? "block/overlay_tier_" + tier : null;
        return BlockDefinition.fullBlock(block, BlockTextures.sides("block/" + bottom, "block/" + top, "block/" + side, overlay));
    }
}
