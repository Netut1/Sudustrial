package com.netut.sudustrial.client.datagen.texture_block.concrete.generator;

import com.netut.sudustrial.action.GetVanillaColorfulBlocks;
import com.netut.sudustrial.register.block.decorative.concrete.RegisterConcreteBlocks;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.item.DyeColor;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class ConcreteRecipeGenerator extends FabricRecipeProvider {

    public ConcreteRecipeGenerator(
            FabricPackOutput output,
            CompletableFuture<HolderLookup.Provider> registries
    ) {
        super(output, registries);
    }

    @Override
    protected @NonNull RecipeProvider createRecipeProvider(
            HolderLookup.@NonNull Provider registries,
            @NonNull RecipeOutput output
    ) {
        return new Provider(registries, output);
    }

    @Override
    public @NonNull String getName() {
        return "ConcreteRecipeGenerator";
    }

    private static class Provider extends RecipeProvider {

        private Provider(
                HolderLookup.Provider registries,
                RecipeOutput output
        ) {
            super(registries, output);
        }

        @Override
        public void buildRecipes() {

            for (DyeColor color : DyeColor.values()) {

                String colorName = color.getName();

                var concrete = GetVanillaColorfulBlocks.getVanillaConcrete(color);
                var concretePowder =
                        GetVanillaColorfulBlocks.getVanillaConcretePowder(color);

                var concreteStairs =
                        RegisterConcreteBlocks.CONCRETE_STAIR.get(color);
                var concreteSlab =
                        RegisterConcreteBlocks.CONCRETE_SLAB.get(color);

                var concretePowderStairs =
                        RegisterConcreteBlocks.CONCRETE_POWDER_STAIR.get(color);
                var concretePowderSlab =
                        RegisterConcreteBlocks.CONCRETE_POWDER_SLAB.get(color);

                /*
                 * CONCRETE STAIRS
                 */

                shaped(
                        RecipeCategory.BUILDING_BLOCKS,
                        concreteStairs.asItem(),
                        4
                )
                        .pattern("#  ")
                        .pattern("## ")
                        .pattern("###")
                        .define('#', concrete.asItem())
                        .unlockedBy(
                                "has_" + colorName + "_concrete",
                                has(concrete.asItem())
                        )
                        .save(output);

                /*
                 * CONCRETE SLAB
                 */

                shaped(
                        RecipeCategory.BUILDING_BLOCKS,
                        concreteSlab.asItem(),
                        6
                )
                        .pattern("###")
                        .define('#', concrete.asItem())
                        .unlockedBy(
                                "has_" + colorName + "_concrete",
                                has(concrete.asItem())
                        )
                        .save(output);

                /*
                 * CONCRETE STAIRS — STONECUTTING
                 */

                stonecutterResultFromBase(
                        RecipeCategory.BUILDING_BLOCKS,
                        concreteStairs.asItem(),
                        concrete.asItem(),
                        1
                );

                /*
                 * CONCRETE SLAB — STONECUTTING
                 */

                stonecutterResultFromBase(
                        RecipeCategory.BUILDING_BLOCKS,
                        concreteSlab.asItem(),
                        concrete.asItem(),
                        2
                );

                /*
                 * CONCRETE POWDER STAIRS
                 */

                shaped(
                        RecipeCategory.BUILDING_BLOCKS,
                        concretePowderStairs.asItem(),
                        4
                )
                        .pattern("#  ")
                        .pattern("## ")
                        .pattern("###")
                        .define('#', concretePowder.asItem())
                        .unlockedBy(
                                "has_" + colorName + "_concrete_powder",
                                has(concretePowder.asItem())
                        )
                        .save(output);

                /*
                 * CONCRETE POWDER SLAB
                 */

                shaped(
                        RecipeCategory.BUILDING_BLOCKS,
                        concretePowderSlab.asItem(),
                        6
                )
                        .pattern("###")
                        .define('#', concretePowder.asItem())
                        .unlockedBy(
                                "has_" + colorName + "_concrete_powder",
                                has(concretePowder.asItem())
                        )
                        .save(output);

                /*
                 * CONCRETE POWDER STAIRS — STONECUTTING
                 */

                stonecutterResultFromBase(
                        RecipeCategory.BUILDING_BLOCKS,
                        concretePowderStairs.asItem(),
                        concretePowder.asItem(),
                        1
                );

                /*
                 * CONCRETE POWDER SLAB — STONECUTTING
                 */


                stonecutterResultFromBase(
                        RecipeCategory.BUILDING_BLOCKS,
                        concretePowderSlab.asItem(),
                        concretePowder.asItem(),
                        2
                );
            }
        }
    }
}
