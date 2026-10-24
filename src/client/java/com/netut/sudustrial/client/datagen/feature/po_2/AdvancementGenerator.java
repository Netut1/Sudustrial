package com.netut.sudustrial.client.datagen.feature.po_2;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.world.item.Items;

import java.util.function.Consumer;

import static com.netut.sudustrial.client.datagen.util.DatagenUtils.*;

public class AdvancementGenerator {
    private AdvancementGenerator() {}

    static void generate(Consumer<AdvancementHolder> consumer) {
        AdvancementRewards.Builder rewards = new AdvancementRewards.Builder().addRecipe(recipeKey("po_2_panel"));

        Advancement.Builder.advancement()
                .parent(recipesRoot())
                .rewards(rewards)
                .addCriterion("has_cobblestone", has(Items.COBBLESTONE))
                .save(consumer, id("po_2_fence"));
    }
}
