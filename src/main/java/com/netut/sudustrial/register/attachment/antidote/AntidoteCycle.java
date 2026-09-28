package com.netut.sudustrial.register.attachment.antidote;

import com.mojang.serialization.Codec;
import com.netut.sudustrial.Sudustrial;
import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import net.minecraft.resources.Identifier;

public class AntidoteCycle {
    // Момент начала текущего 2-дневного цикла применений.
    public static final AttachmentType<Long> ANTIDOTE_CYCLE_START = AttachmentRegistry.create(
            Identifier.fromNamespaceAndPath(Sudustrial.MOD_ID, "antidote_cycle_start"),
            builder -> builder
                    .initializer(() -> 0L)
                    .persistent(Codec.LONG)
                    .copyOnDeath()
    );

    public static void register() {}
}
