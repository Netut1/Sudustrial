package com.netut.sudustrial.client.datagen.feature.tnt;

import com.netut.sudustrial.tnt.type.block.TntType;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.Set;
import java.util.TreeSet;

import static com.netut.sudustrial.client.datagen.util.DatagenUtils.has;

final class RecipeGenerator {
    private static final int MAX_TIER = 10;
    private static final String STANDARD = "standard_tnt";
    private static final Set<String> NON_FAMILY = Set.of(STANDARD, "party_tnt", "nuclear_tnt");

    private RecipeGenerator() {}

    static void generate(RecipeProvider provider, RecipeOutput output, TntCatalog catalog) {
        checkFamilies(catalog);

        generateStandard(provider, output, catalog);

        for (TntFamilies.Family family : TntFamilies.ALL) {
            generateTierOne(provider, output, catalog, family);
            generateHigherTiers(provider, output, catalog, family);
        }

        generateParty(provider, output, catalog);
    }

    private static void checkFamilies(TntCatalog catalog) {
        Set<String> registered = new TreeSet<>(catalog.bases());
        registered.removeAll(NON_FAMILY);

        Set<String> described = new TreeSet<>();
        for (TntFamilies.Family family : TntFamilies.ALL) {
            described.add(family.base());
        }

        Set<String> withoutRecipe = new TreeSet<>(registered);
        withoutRecipe.removeAll(described);

        Set<String> unknown = new TreeSet<>(described);
        unknown.removeAll(registered);

        if (!withoutRecipe.isEmpty() || !unknown.isEmpty()) {
            throw new IllegalStateException(
                    "TNT families mismatch. Registered but no recipe data: " + withoutRecipe
                            + "; listed but not registered: " + unknown);
        }
    }

    private static void generateStandard(RecipeProvider provider, RecipeOutput output, TntCatalog catalog) {
        for (int tier = 2; tier <= MAX_TIER; tier++) {
            provider.shaped(RecipeCategory.REDSTONE, catalog.item(STANDARD, tier), 1)
                    .group("tnt")
                    .pattern("###")
                    .pattern("###")
                    .pattern("###")
                    .define('#', Items.TNT)
                    .unlockedBy("has_tnt", has(Items.TNT))
                    .save(output);
        }
    }

    private static void generateTierOne(RecipeProvider provider, RecipeOutput output, TntCatalog catalog, TntFamilies.Family family) {
        provider.shaped(RecipeCategory.REDSTONE, catalog.item(family.base(), 1), 1)
                .group(family.base())
                .pattern("###")
                .pattern("#&#")
                .pattern("###")
                .define('#', family.ingredient())
                .define('&', Items.TNT)
                .unlockedBy("has_" + family.id() + "_ingredient", has(family.ingredient()))
                .save(output);
    }

    private static void generateHigherTiers(RecipeProvider provider, RecipeOutput output, TntCatalog catalog, TntFamilies.Family family) {
        for (int tier = 2; tier <= MAX_TIER; tier++) {
            Item previous = catalog.item(family.base(), tier - 1);

            provider.shaped(RecipeCategory.REDSTONE, catalog.item(family.base(), tier), 1)
                    .group(family.base())
                    .pattern("###")
                    .pattern("###")
                    .pattern("###")
                    .define('#', previous)
                    .unlockedBy("has_" + family.id() + "_tier_" + (tier - 1), has(previous))
                    .save(output);
        }
    }

    private static void generateParty(RecipeProvider provider, RecipeOutput output, TntCatalog catalog) {
        provider.shaped(RecipeCategory.REDSTONE, catalog.item(TntType.PARTY), 1)
                .group("party_tnt")
                .pattern("###")
                .pattern("#&#")
                .pattern("###")
                .define('#', Items.EGG)
                .define('&', Items.TNT)
                .unlockedBy("has_egg", has(Items.EGG))
                .save(output);
    }
}
