package com.netut.sudustrial.effect.tnt.nuclear;

import com.netut.sudustrial.Sudustrial;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import org.jspecify.annotations.NonNull;

public class NuclearDecayEffect extends MobEffect {
    private static final int INTERVAL_TICKS = 400; // раз в 20 секунд
    private static final double HEALTH_LOSS_PER_INTERVAL = 1.0D;
    private static final double MAX_PENALTY_MAGNITUDE = 19.0D;

    public static final Identifier DECAY_MODIFIER_ID = Identifier.fromNamespaceAndPath(Sudustrial.MOD_ID, "nuclear_decay");

    public NuclearDecayEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        // Проверяем каждый тик — реальная периодичность считается внутри applyEffectTick по абсолютному игровому времени.
        return true;
    }

    @Override
    public boolean applyEffectTick(@NonNull ServerLevel level, @NonNull LivingEntity entity, int amplifier) {
        if (level.getGameTime() % INTERVAL_TICKS != 0) {
            return true;
        }

        AttributeInstance maxHealthAttribute = entity.getAttribute(Attributes.MAX_HEALTH);
        if (maxHealthAttribute != null) {
            double currentPenalty = 0.0D;
            AttributeModifier existing = maxHealthAttribute.getModifier(DECAY_MODIFIER_ID);
            if (existing != null) {
                currentPenalty = existing.amount();
                maxHealthAttribute.removeModifier(DECAY_MODIFIER_ID);
            }

            double newPenalty = Math.max(-MAX_PENALTY_MAGNITUDE, currentPenalty - HEALTH_LOSS_PER_INTERVAL);

            AttributeModifier modifier = new AttributeModifier(
                    DECAY_MODIFIER_ID,
                    newPenalty,
                    AttributeModifier.Operation.ADD_VALUE
            );
            maxHealthAttribute.addPermanentModifier(modifier);

            if (entity.getHealth() > entity.getMaxHealth()) {
                entity.setHealth(entity.getMaxHealth());
            }
        }
        return true;
    }
}
