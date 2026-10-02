package com.netut.sudustrial.client.datagen.concrete.generator;

import com.netut.sudustrial.concrete.GetVanillaColorfulBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.triggers.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Recipe;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class ConcreteAdvancementGenerator extends FabricAdvancementProvider {

    public ConcreteAdvancementGenerator(
            FabricPackOutput output,
            CompletableFuture<HolderLookup.Provider> registries
    ) {
        super(output, registries);
    }

    @Override
    public void generateAdvancement(
            HolderLookup.@NonNull Provider registries,
            @NonNull Consumer<AdvancementHolder> consumer
    ) {
        for (DyeColor color : DyeColor.values()) {
            String colorName = color.getName();

            generateConcreteAdvancement(
                    consumer,
                    colorName,
                    GetVanillaColorfulBlocks.getVanillaConcrete(color).asItem()
            );

            generateConcretePowderAdvancement(
                    consumer,
                    colorName,
                    GetVanillaColorfulBlocks.getVanillaConcretePowder(color).asItem()
            );
        }
    }

    private void generateConcreteAdvancement(
            Consumer<AdvancementHolder> consumer,
            String colorName,
            Item concreteItem
    ) {
        String advancementName = colorName + "_concrete_decorations";

        AdvancementRewards.Builder rewards = new AdvancementRewards.Builder()
                .addRecipe(recipeId(colorName + "_concrete_stairs"))
                .addRecipe(recipeId(colorName + "_concrete_slab"))
                .addRecipe(recipeId(colorName + "_concrete_stairs_from_stonecutting"))
                .addRecipe(recipeId(colorName + "_concrete_slab_from_stonecutting"));

        Advancement.Builder.advancement()
                .parent(
                        createPlaceholder(
                                Identifier.withDefaultNamespace("recipes/root")
                        )
                )
                .rewards(rewards)
                .addCriterion(
                        "has_" + colorName + "_concrete",
                        InventoryChangeTrigger.TriggerInstance.hasItems(concreteItem)
                )
                .save(
                        consumer,
                        Identifier.fromNamespaceAndPath(
                                "sudustrial",
                                advancementName
                        )
                );
    }

    private void generateConcretePowderAdvancement(
            Consumer<AdvancementHolder> consumer,
            String colorName,
            Item concretePowderItem
    ) {
        String advancementName = colorName + "_concrete_powder_decorations";

        AdvancementRewards.Builder rewards = new AdvancementRewards.Builder()
                .addRecipe(recipeId(colorName + "_concrete_powder_stairs"))
                .addRecipe(recipeId(colorName + "_concrete_powder_slab"))
                .addRecipe(recipeId(colorName + "_concrete_powder_stairs_from_stonecutting"))
                .addRecipe(recipeId(colorName + "_concrete_powder_slab_from_stonecutting"));

        Advancement.Builder.advancement()
                .parent(
                        createPlaceholder(
                                Identifier.withDefaultNamespace("recipes/root")
                        )
                )
                .rewards(rewards)
                .addCriterion(
                        "has_" + colorName + "_concrete_powder",
                        InventoryChangeTrigger.TriggerInstance.hasItems(concretePowderItem)
                )
                .save(
                        consumer,
                        Identifier.fromNamespaceAndPath(
                                "sudustrial",
                                advancementName
                        )
                );
    }

    private static ResourceKey<Recipe<?>> recipeId(
            String path
    ) {
        return ResourceKey.create(
                Registries.RECIPE,
                Identifier.fromNamespaceAndPath(
                        "sudustrial",
                        path
                )
        );
    }

    @Override
    public @NonNull String getName() {
        return "ConcreteAdvancementGenerator";
    }
}
