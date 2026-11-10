package com.netut.sudustrial.client.datagen.feature.tnt;

import com.netut.sudustrial.client.datagen.core.recipe.RecipeVariants;
import com.netut.sudustrial.tnt.type.block.TntType;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

final class RecipeGenerator {
    private static final int MAX_TIER = 10;
    private static final String STANDARD = "standard_tnt";
    private static final Set<String> NON_FAMILY = Set.of(STANDARD, "party_tnt", "nuclear_tnt");

    private RecipeGenerator() {}

    static void generate(RecipeProvider provider, RecipeOutput output, TntCatalog catalog) {
        checkFamilies(catalog);
        RecipeVariants recipes = new RecipeVariants(provider, output, RecipeCategory.REDSTONE);
        generateStandard(recipes, catalog);
        for (TntFamilies.Family family : TntFamilies.ALL) {generateFamily(recipes, catalog, family);}
        generateParty(recipes, catalog);
    }

    private static void checkFamilies(TntCatalog catalog) {
        Set<String> registered = new TreeSet<>(catalog.bases());
        registered.removeAll(NON_FAMILY);

        Set<String> described = new TreeSet<>();
        for (TntFamilies.Family family : TntFamilies.ALL) {described.add(family.base());}

        Set<String> withoutRecipe = new TreeSet<>(registered);
        withoutRecipe.removeAll(described);

        Set<String> unknown = new TreeSet<>(described);
        unknown.removeAll(registered);

        if (!withoutRecipe.isEmpty() || !unknown.isEmpty()) {
            throw new IllegalStateException("TNT families mismatch. Registered but no recipe data: " + withoutRecipe + "; listed but not registered: " + unknown);
        }
    }

    private static void generateStandard(RecipeVariants recipes, TntCatalog catalog) {
        List<Item> tiers = new ArrayList<>();
        tiers.add(Items.TNT);
        tiers.addAll(tiers(catalog, STANDARD, 2));

        recipes.chain("tnt", tiers);
    }

    private static void generateFamily(RecipeVariants recipes, TntCatalog catalog, TntFamilies.Family family) {
        recipes.ring(family.base(), catalog.item(family.base(), 1), family.ingredient(), Items.TNT);
        recipes.chain(family.base(), tiers(catalog, family.base(), 1));
    }

    private static void generateParty(RecipeVariants recipes, TntCatalog catalog) {
        recipes.ring("party_tnt", catalog.item(TntType.PARTY), Items.EGG, Items.TNT);
    }

    private static List<Item> tiers(TntCatalog catalog, String base, int firstTier) {
        List<Item> items = new ArrayList<>();
        for (int tier = firstTier; tier <= MAX_TIER; tier++) {
            items.add(catalog.item(base, tier));
        }
        return items;
    }
}
