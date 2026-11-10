package com.netut.sudustrial.client.datagen.core.advancement;

import com.netut.sudustrial.client.datagen.core.DatagenUtils;
import net.minecraft.advancements.*;
import net.minecraft.core.ClientAsset;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStackTemplate;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.Consumer;

import static com.netut.sudustrial.client.datagen.core.DatagenUtils.has;
import static com.netut.sudustrial.client.datagen.core.DatagenUtils.path;

public record Stage(String id, Item icon, @Nullable String parent, Collection<Item> triggers) {
    public Stage {
        if (Objects.requireNonNull(id, "id").isBlank()) {
            throw new IllegalArgumentException("Stage id must not be blank");
        }
        Objects.requireNonNull(icon, "icon of stage " + id);
        if (parent != null && parent.isBlank()) {
            throw new IllegalArgumentException("Stage " + id + ": parent must be null or non-blank");
        }
        triggers = List.copyOf(new LinkedHashSet<>(Objects.requireNonNull(triggers, "triggers of stage " + id)));
        if (triggers.isEmpty()) {
            throw new IllegalArgumentException("Stage " + id + " has no triggers");
        }
    }

    public void save(Consumer<AdvancementHolder> consumer) {
        Identifier advancementId = DatagenUtils.id(id);
        String langKey = "advancement." + advancementId.getNamespace() + "." + advancementId.getPath();

        Advancement.Builder builder = Advancement.Builder.advancement();
        if (parent != null) {
            builder.parent(Advancement.Builder.advancement().build(DatagenUtils.id(parent)));
        }

        Identifier background = parent == null ? DatagenUtils.id("gui/advancements/backgrounds/background") : null;

        builder.display(new DisplayInfo(
                new ItemStackTemplate(icon),
                Component.translatable(langKey + ".title"),
                Component.translatable(langKey + ".description"),
                Optional.ofNullable(background).map(ClientAsset.ResourceTexture::new),
                AdvancementType.TASK,
                true,   // showToast
                true,   // announceChat
                false   // hidden
        ));

        for (Item item : triggers) {
            builder.addCriterion("has_" + path(item), has(item));
        }
        builder.requirements(AdvancementRequirements.Strategy.OR);

        builder.save(consumer, advancementId);
    }
}
