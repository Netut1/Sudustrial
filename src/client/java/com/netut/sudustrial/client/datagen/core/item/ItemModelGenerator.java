package com.netut.sudustrial.client.datagen.core.item;

import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;

public final class ItemModelGenerator {
    private ItemModelGenerator() {}

    public static void generate(ItemModelGenerators generator, ItemDefinition definition) {
        switch (definition.type()) {
            case FLAT -> generator.generateFlatItem(definition.item(), ModelTemplates.FLAT_ITEM);
        }
    }
}
