package com.netut.sudustrial.client.datagen.feature.tnt.nuclear.antidote;

import com.netut.sudustrial.client.datagen.core.DefinitionFeature;
import com.netut.sudustrial.client.datagen.core.item.ItemDefinition;
import com.netut.sudustrial.register.item.nuclear_antidote.RegisterNuclearAntidote;
import net.fabricmc.fabric.api.recipe.v1.ingredient.DefaultCustomIngredients;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.component.DataComponents;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.List;

import static com.netut.sudustrial.client.datagen.core.DatagenUtils.has;

public final class NuclearAntidoteFeature extends DefinitionFeature {
    @Override
    protected List<ItemDefinition> items() {
        return List.of(ItemDefinition.flat(RegisterNuclearAntidote.NUCLEAR_ANTIDOTE));
    }

    @Override
    public void recipes(RecipeProvider provider, RecipeOutput output) {
        Ingredient strongHealing = DefaultCustomIngredients.components(
                Ingredient.of(Items.POTION),
                DataComponentPatch.builder()
                        .set(DataComponents.POTION_CONTENTS, new PotionContents(Potions.STRONG_HEALING))
                        .build());

        provider.shaped(RecipeCategory.MISC, RegisterNuclearAntidote.NUCLEAR_ANTIDOTE, 1)
                .pattern("GSG")
                .pattern("SGS")
                .pattern("GSG")
                .define('G', Items.DRAGON_BREATH)
                .define('S', strongHealing)
                .unlockedBy("has_dragon_breath", has(Items.DRAGON_BREATH))
                .save(output);
    }
}
