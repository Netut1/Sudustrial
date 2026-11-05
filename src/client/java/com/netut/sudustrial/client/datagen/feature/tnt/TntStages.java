package com.netut.sudustrial.client.datagen.feature.tnt;

import com.netut.sudustrial.client.datagen.core.Registered;
import com.netut.sudustrial.client.datagen.core.advancement.Stage;
import com.netut.sudustrial.register.item.core.RegisterCore;
import com.netut.sudustrial.tnt.type.item.CoreItemType;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public final class TntStages {
    public static final String FIRST_CUSTOM = "unlock_custom_tnt_tier_1";
    public static final String TNT_ERA = "tnt_era";

    private TntStages() {}

    static void generate(Consumer<AdvancementHolder> consumer, TntCatalog catalog) {
        new Stage(FIRST_CUSTOM, catalog.item("massive_tnt", 1), null, List.of(Items.TNT)).save(consumer);

        List<Item> firstCustom = new ArrayList<>();
        for (TntFamilies.Family family : TntFamilies.ALL) {
            firstCustom.add(catalog.item(family.base(), 1));
        }
        firstCustom.add(catalog.item("standard_tnt", 2));

        new Stage(TNT_ERA, Registered.require(RegisterCore.CORE_ITEMS, CoreItemType.MASSIVE_CORE), FIRST_CUSTOM, firstCustom)
                .save(consumer);
    }
}
