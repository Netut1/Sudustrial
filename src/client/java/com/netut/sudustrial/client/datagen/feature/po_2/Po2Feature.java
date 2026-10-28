package com.netut.sudustrial.client.datagen.feature.po_2;

import com.netut.sudustrial.client.datagen.core.DatagenFeature;
import com.netut.sudustrial.decorative.soviet_architecture.po_2.Po2Fence;
import com.netut.sudustrial.decorative.soviet_architecture.po_2.Po2PanelBlock;
import com.netut.sudustrial.register.block.decorative.po_2.RegisterPO_2Fence;
import net.minecraft.advancements.predicates.StatePropertiesPredicate;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.ExplosionCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

public final class Po2Feature implements DatagenFeature {
    @Override
    public void blockModels(BlockModelGenerators generator) {
        ModelGenerator.generate(generator);
    }

    @Override
    public void recipes(RecipeProvider provider, RecipeOutput output) {
        RecipeGenerator.generate(provider, output);
    }

    @Override
    public void loot(LootSink loot) {
        Block panel = RegisterPO_2Fence.PO_2_PANEL_BLOCK;

        loot.add(panel, LootTable.lootTable().withPool(
                LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(panel))
                        .when(ExplosionCondition.survivesExplosion())
                        .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(panel)
                                .setProperties(StatePropertiesPredicate.Builder.properties()
                                        .hasProperty(Po2PanelBlock.COLUMN, 0)
                                        .hasProperty(Po2PanelBlock.ROW, Po2Fence.HEIGHT - 1)))));
    }
}
