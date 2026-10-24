package com.netut.sudustrial.client.datagen.feature.tnt.nuclear;

import com.netut.sudustrial.register.item.core.RegisterCore;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.world.item.Item;

final class CoreModelGenerator {
    private CoreModelGenerator() {}

    static void generate(ItemModelGenerators generator) {
        for (Item item : RegisterCore.CORE_ITEMS.values()) {
            generator.generateFlatItem(item, ModelTemplates.FLAT_ITEM);
        }
    }
}
