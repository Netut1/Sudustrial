package com.netut.sudustrial.client.datagen.feature.po_2;

import com.netut.sudustrial.client.datagen.core.recipe.RecipeVariants;
import com.netut.sudustrial.client.datagen.feature.DatagenFeature;
import com.netut.sudustrial.decorative.soviet_architecture.po_2.Po2Fence;
import com.netut.sudustrial.decorative.soviet_architecture.po_2.Po2PanelBlock;
import com.netut.sudustrial.register.block.decorative.po_2.RegisterPO_2Fence;
import net.minecraft.advancements.predicates.BlockPredicate;
import net.minecraft.advancements.predicates.StatePropertiesPredicate;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.ExplosionCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchBlock;
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProviders;

public final class Po2Feature implements DatagenFeature {
    @Override
    public void blockModels(BlockModelGenerators generator) {
        ModelGenerator.generate(generator);
    }

    @Override
    public void recipes(RecipeProvider provider, RecipeOutput output) {
        new RecipeVariants(provider, output, RecipeCategory.BUILDING_BLOCKS)
                .ring(null, RegisterPO_2Fence.PO_2_PANEL_ITEM, Items.COBBLESTONE, Items.IRON_INGOT);
    }

    @Override
    public void loot(LootSink loot) {
        Block panel = RegisterPO_2Fence.PO_2_PANEL_BLOCK;

        HolderGetter<Block> blocks = BuiltInRegistries.BLOCK;

        loot.add(panel, LootTable.lootTable().withPool(
                LootPool.lootPool()
                        .setRolls(ContextIntProviders.exactly(1))
                        .add(LootItem.lootTableItem(panel))
                        .when(ExplosionCondition.survivesExplosion())
                        .when(Holder.direct(new MatchBlock(
                                BlockPredicate.Builder.block()
                                        .of(blocks, panel)
                                        .setProperties(StatePropertiesPredicate.Builder.properties()
                                                .hasProperty(Po2PanelBlock.COLUMN, 0)
                                                .hasProperty(Po2PanelBlock.ROW, Po2Fence.HEIGHT - 1))
                                        .build())))));
    }
}
