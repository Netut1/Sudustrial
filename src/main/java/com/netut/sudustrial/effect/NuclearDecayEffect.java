package com.netut.sudustrial.effect;

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

    public NuclearDecayEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        // Проверяем каждый тик — реальная периодичность считается внутри applyEffectTick
        // по АБСОЛЮТНОМУ игровому времени, а не по остатку duration. Так эффект работает
        // корректно независимо от того, как часто его переналагают (блок, облако — не важно).
        return true;
    }

    @Override
    public boolean applyEffectTick(@NonNull ServerLevel level, @NonNull LivingEntity entity, int amplifier) {
        if (level.getGameTime() % INTERVAL_TICKS != 0) {
            return true;
        }

        AttributeInstance maxHealthAttribute = entity.getAttribute(Attributes.MAX_HEALTH);
        if (maxHealthAttribute != null) {
            Identifier modifierId = Identifier.fromNamespaceAndPath(
                    Sudustrial.MOD_ID,
                    "nuclear_decay_" + entity.getUUID() + "_" + level.getGameTime()
            );

            AttributeModifier modifier = new AttributeModifier(
                    modifierId,
                    -HEALTH_LOSS_PER_INTERVAL,
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
