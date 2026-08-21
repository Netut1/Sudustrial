package com.netut.sudustrial.action.tnt.revival.nuclear;

import com.netut.sudustrial.effect.tnt.nuclear.NuclearDecayEffect;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class NuclearDecayCarryOver {
    private NuclearDecayCarryOver() { }

    public static void init() {
        ServerPlayerEvents.COPY_FROM.register((oldPlayer, newPlayer, alive) -> {
            AttributeInstance oldAttribute = oldPlayer.getAttribute(Attributes.MAX_HEALTH);
            if (oldAttribute == null) return;

            AttributeModifier existing = oldAttribute.getModifier(NuclearDecayEffect.DECAY_MODIFIER_ID);
            if (existing == null) return; // радиации не было — переносить нечего

            AttributeInstance newAttribute = newPlayer.getAttribute(Attributes.MAX_HEALTH);
            if (newAttribute == null) return;

            newAttribute.addPermanentModifier(existing);
        });
    }
}
