package com.netut.sudustrial.client.datagen.core.common;

import com.netut.sudustrial.client.datagen.core.item.ItemIds;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static com.netut.sudustrial.client.datagen.core.DatagenUtils.has;

public class RecipeVariants {
    private final RecipeProvider provider;
    private final RecipeOutput output;
    private final RecipeCategory category;

    public RecipeVariants(RecipeProvider provider, RecipeOutput output, RecipeCategory category) {
        this.provider = provider;
        this.output = output;
        this.category = category;
    }

    public void ring(@Nullable String group, Item result, Item ring, Item center) {
        var builder = provider.shaped(category, result, 1);
        if (group != null) {
            builder.group(group);
        }

        builder.pattern("###")
                .pattern("#&#")
                .pattern("###")
                .define('#', ring)
                .define('&', center)
                .unlockedBy(criterion(ring), has(ring))
                .save(output);
    }

    public void compress(@Nullable String group, Item result, Item input) {
        var builder = provider.shaped(category, result, 1);
        if (group != null) {
            builder.group(group);
        }

        builder.pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', input)
                .unlockedBy(criterion(input), has(input))
                .save(output);
    }

    public void chain(String group, List<Item> items) {
        if (items.size() < 2) {
            throw new IllegalArgumentException("Recipe chain needs at least 2 items, group: " + group);
        }

        for (int i = 1; i < items.size(); i++) {
            compress(group, items.get(i), items.get(i - 1));
        }
    }

    public void grid(@Nullable String group, Item result, Item... nine) {
        gridUnlockedBy(group, result, List.of(nine), nine);
    }

    public void gridUnlockedBy(@Nullable String group, Item result, Item unlockedBy, Item... nine) {
        gridUnlockedBy(group, result, List.of(unlockedBy), nine);
    }

    private void gridUnlockedBy(@Nullable String group, Item result, List<Item> unlockers, Item[] nine) {
        if (nine.length != 9) {
            throw new IllegalArgumentException("Grid recipe needs 9 ingredients: " + ItemIds.path(result));
        }

        var builder = provider.shaped(category, result, 1);
        if (group != null) {
            builder.group(group);
        }

        builder.pattern("ABC")
                .pattern("DEF")
                .pattern("GHI");
        for (int i = 0; i < nine.length; i++) {
            builder.define((char) ('A' + i), nine[i]);
        }

        Set<Item> unique = new LinkedHashSet<>(unlockers);
        for (Item item : unique) {
            builder.unlockedBy(criterion(item), has(item));
        }
        builder.save(output);
    }

    private static String criterion(Item item) {
        return "has_" + ItemIds.path(item);
    }
}
