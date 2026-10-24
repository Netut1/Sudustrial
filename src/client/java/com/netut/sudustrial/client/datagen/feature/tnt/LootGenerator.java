package com.netut.sudustrial.client.datagen.feature.tnt;

import com.netut.sudustrial.client.datagen.core.DatagenFeature;

final class LootGenerator {
    private LootGenerator() {}

    static void generate(DatagenFeature.LootSink loot, TntCatalog catalog) {
        for (TntCatalog.Entry entry : catalog.entries()) {
            loot.dropSelf(entry.block());
        }
    }
}