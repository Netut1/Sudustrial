package com.netut.sudustrial.client.datagen.feature.tnt.nuclear;

import com.netut.sudustrial.client.datagen.core.Registered;
import com.netut.sudustrial.register.item.core.RegisterCore;
import com.netut.sudustrial.tnt.type.item.CoreItemType;
import net.minecraft.world.item.Item;
final class Cores {
    private Cores() {}

    static Item of(CoreItemType type) {
        return Registered.require(RegisterCore.CORE_ITEMS, type);
    }
}
