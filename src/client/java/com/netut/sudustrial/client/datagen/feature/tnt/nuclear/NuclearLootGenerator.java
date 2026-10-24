package com.netut.sudustrial.client.datagen.feature.tnt.nuclear;

import com.netut.sudustrial.client.datagen.core.DatagenFeature;
import com.netut.sudustrial.register.block.nuclear_block.RegisterNuclearCore;

final class NuclearLootGenerator {
    private NuclearLootGenerator() {}

    static void generate(DatagenFeature.LootSink loot) {
        loot.dropSelf(RegisterNuclearCore.NUCLEAR_CORE_BLOCK);
    }
}
