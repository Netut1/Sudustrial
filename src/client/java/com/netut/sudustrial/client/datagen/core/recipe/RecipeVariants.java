package com.netut.sudustrial.client.datagen.core.recipe;

import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import static com.netut.sudustrial.client.datagen.core.DatagenUtils.has;
import static com.netut.sudustrial.client.datagen.core.DatagenUtils.path;

public class RecipeVariants {
    private static final List<String> RING = List.of("###", "#&#", "###");
    private static final List<String> SQUARE = List.of("###", "###", "###");
    private static final List<String> GRID = List.of("ABC", "DEF", "GHI");

    private final RecipeProvider provider;
    private final RecipeOutput output;
    private final RecipeCategory category;

    public RecipeVariants(RecipeProvider provider, RecipeOutput output, RecipeCategory category) {
        this.provider = provider;
        this.output = output;
        this.category = category;
    }

    public void shaped(@Nullable String group, Item result, int count, Item ingredient, List<String> rows) {
        if (count < 1) {
            throw new IllegalArgumentException("Recipe count must be positive: " + path(result));
        }
        if (rows.isEmpty() || rows.stream().anyMatch(row -> !row.matches("[# ]+"))) {
            throw new IllegalArgumentException("Single-ingredient pattern may contain only '#' and ' ': " + path(result) + " " + rows);
        }

        Map<Character, Item> keys = new LinkedHashMap<>();
        keys.put('#', ingredient);
        save(group, result, count, rows, keys, List.of(ingredient));
    }

    public void ring(@Nullable String group, Item result, Item ring, Item center) {
        Map<Character, Item> keys = new LinkedHashMap<>();
        keys.put('#', ring);
        keys.put('&', center);
        save(group, result, 1, RING, keys, List.of(ring));
    }

    public void compress(@Nullable String group, Item result, Item input) {
        shaped(group, result, 1, input, SQUARE);
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

    public void stonecut(Item result, Item base, int count) {
        provider.stonecutterResultFromBase(category, result, base, count);
    }

    private void gridUnlockedBy(@Nullable String group, Item result, List<Item> unlockers, Item[] nine) {
        if (nine.length != 9) {
            throw new IllegalArgumentException("Grid recipe needs 9 ingredients: " + path(result));
        }

        Map<Character, Item> keys = new LinkedHashMap<>();
        for (int i = 0; i < nine.length; i++) {
            keys.put((char) ('A' + i), nine[i]);
        }
        save(group, result, 1, GRID, keys, unlockers);
    }

    private void save(@Nullable String group, Item result, int count, List<String> rows, Map<Character, Item> keys, Collection<Item> unlockedBy) {
        var builder = provider.shaped(category, result, count);
        if (group != null) {
            builder.group(group);
        }

        for (String row : rows) {
            builder.pattern(row);
        }
        for (Map.Entry<Character, Item> key : keys.entrySet()) {
            builder.define(key.getKey(), key.getValue());
        }
        for (Item item : new LinkedHashSet<>(unlockedBy)) {
            builder.unlockedBy(criterion(item), has(item));
        }
        builder.save(output);
    }

    private static String criterion(Item item) {
        return "has_" + path(item);
    }
}
