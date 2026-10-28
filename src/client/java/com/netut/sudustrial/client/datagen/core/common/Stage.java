package com.netut.sudustrial.client.datagen.core.common;

import com.netut.sudustrial.Sudustrial;
import com.netut.sudustrial.client.datagen.core.DatagenUtils;
import com.netut.sudustrial.client.datagen.core.item.ItemIds;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.function.Consumer;

import static com.netut.sudustrial.client.datagen.core.DatagenUtils.has;

public record Stage(String id, Item icon, @Nullable String parent, Collection<Item> triggers) {
    public void save(Consumer<AdvancementHolder> consumer) {
        if (triggers.isEmpty()) {
            throw new IllegalArgumentException("Stage " + id + " has no triggers");
        }

        String langKey = "advancement." + Sudustrial.MOD_ID + "." + id;

        Advancement.Builder builder = Advancement.Builder.advancement();
        if (parent != null) {
            builder.parent(Advancement.Builder.advancement().build(DatagenUtils.id(parent)));
        }

        builder.display(icon,
                Component.translatable(langKey + ".title"),
                Component.translatable(langKey + ".description"),
                null,
                AdvancementType.TASK,
                true,
                true,
                false);

        for (Item item : new LinkedHashSet<>(triggers)) {
            builder.addCriterion("has_" + ItemIds.path(item), has(item));
        }
        builder.requirements(AdvancementRequirements.Strategy.OR);

        builder.save(consumer, DatagenUtils.id(id));
    }
}
