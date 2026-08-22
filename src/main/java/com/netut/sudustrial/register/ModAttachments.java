package com.netut.sudustrial.register;

import com.mojang.serialization.Codec;
import com.netut.sudustrial.Sudustrial;
import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import net.minecraft.resources.Identifier;

public class ModAttachments {
    // Момент начала текущего 2-дневного цикла применений.
    public static final AttachmentType<Long> ANTIDOTE_CYCLE_START = AttachmentRegistry.create(
            Identifier.fromNamespaceAndPath(Sudustrial.MOD_ID, "antidote_cycle_start"),
            builder -> builder
                    .initializer(() -> 0L)
                    .persistent(Codec.LONG)
                    .copyOnDeath()
    );

    // Сколько раз антидот уже применялся в текущем цикле.
    public static final AttachmentType<Integer> ANTIDOTE_USES_THIS_CYCLE = AttachmentRegistry.create(
            Identifier.fromNamespaceAndPath(Sudustrial.MOD_ID, "antidote_uses_this_cycle"),
            builder -> builder
                    .initializer(() -> 0)
                    .persistent(Codec.INT)
                    .copyOnDeath()
    );

    public static void registerModAttachments() {}
}
