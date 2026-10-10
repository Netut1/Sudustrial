package com.netut.sudustrial.client.datagen.advancement.po_2;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.triggers.InventoryChangeTrigger;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Recipe;

import java.util.function.Consumer;

public final class Po2AdvancementGenerator {

    private Po2AdvancementGenerator() {}

    public static void generate(Consumer<AdvancementHolder> consumer) {
        AdvancementRewards.Builder rewards = new AdvancementRewards.Builder()
                .addRecipe(recipeId("po_2_panel"));

        Advancement.Builder.advancement()
                .parent(Advancement.Builder.advancement().build(Identifier.withDefaultNamespace("recipes/root")))
                .rewards(rewards)
                .addCriterion(
                        "has_cobblestone",
                        InventoryChangeTrigger.TriggerInstance.hasItems(Items.COBBLESTONE)
                )
                .save(consumer, Identifier.fromNamespaceAndPath("sudustrial", "po_2_fence"));
    }

    private static ResourceKey<Recipe<?>> recipeId(String path) {
        return ResourceKey.create(Registries.RECIPE, Identifier.fromNamespaceAndPath("sudustrial", path));
    }
}