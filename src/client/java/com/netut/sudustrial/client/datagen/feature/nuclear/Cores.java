package com.netut.sudustrial.client.datagen.feature.nuclear;

import com.netut.sudustrial.client.datagen.core.Registered;
import com.netut.sudustrial.register.item.core.RegisterCore;
import com.netut.sudustrial.tnt.type.item.CoreItemType;
import net.minecraft.world.item.Item;

import java.util.Arrays;
import java.util.List;

final class Cores {
    private Cores() {}

    static Item of(CoreItemType type) {
        return Registered.require(RegisterCore.CORE_ITEMS, type);
    }

    static List<Item> all() {
        return Arrays.stream(CoreItemType.values()).map(Cores::of).toList();
    }
}
