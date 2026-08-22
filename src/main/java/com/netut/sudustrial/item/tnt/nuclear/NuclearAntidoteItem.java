package com.netut.sudustrial.item.tnt.nuclear;

import com.netut.sudustrial.effect.tnt.nuclear.NuclearDecayEffect;
import com.netut.sudustrial.register.ModAttachments;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.NonNull;

public class NuclearAntidoteItem extends Item {
    private static final long CYCLE_LENGTH_TICKS = 48000L; // 2 игровых дня
    private static final double HEAL_PER_USE = 1.0D; // абсолютное восстановление максимума хп за применение

    private static final int MINUTE_TICKS = 1200;
    private static final int HALF_MINUTE_TICKS = 600;

    public NuclearAntidoteItem(Properties properties) {
        super(properties);
    }

    @Override
    @NonNull
    public ItemStack finishUsingItem(@NonNull ItemStack stack, @NonNull Level level, @NonNull LivingEntity entity) {
        ItemStack result = super.finishUsingItem(stack, level, entity);

        if (!level.isClientSide() && level instanceof ServerLevel) {
            applyAntidote(level, entity);
        }

        return result;
    }

    private void applyAntidote(Level level, LivingEntity entity) {
        long now = level.getGameTime();
        long cycleStart = entity.getAttachedOrCreate(ModAttachments.ANTIDOTE_CYCLE_START);
        int usesThisCycle = entity.getAttachedOrCreate(ModAttachments.ANTIDOTE_USES_THIS_CYCLE);

        if (now - cycleStart >= CYCLE_LENGTH_TICKS) {
            cycleStart = now;
            usesThisCycle = 0;
        }

        int useIndex = usesThisCycle;

        if (useIndex == 0) {
            healMaxHealth(entity);
            entity.addEffect(new MobEffectInstance(MobEffects.NAUSEA, MINUTE_TICKS, 1, false, true, true));
            entity.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, HALF_MINUTE_TICKS, 0, false, true, true));
        } else if (useIndex == 1) {
            healMaxHealth(entity);
            entity.addEffect(new MobEffectInstance(MobEffects.NAUSEA, MINUTE_TICKS, 1, false, true, true));
            entity.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, HALF_MINUTE_TICKS, 0, false, true, true));
            entity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, MINUTE_TICKS, 2, false, true, true));
        } else {
            // сверх лимита — хп не восстанавливаем
            entity.addEffect(new MobEffectInstance(MobEffects.NAUSEA, MINUTE_TICKS, 1, false, true, true));
            entity.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, HALF_MINUTE_TICKS, 0, false, true, true));
            entity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, MINUTE_TICKS, 2, false, true, true));
            entity.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, MINUTE_TICKS, 2, false, true, true));
        }

        entity.setAttached(ModAttachments.ANTIDOTE_CYCLE_START, cycleStart);
        entity.setAttached(ModAttachments.ANTIDOTE_USES_THIS_CYCLE, usesThisCycle + 1);
    }

    private void healMaxHealth(LivingEntity entity) {
        AttributeInstance maxHealthAttribute = entity.getAttribute(Attributes.MAX_HEALTH);
        if (maxHealthAttribute == null) return;

        AttributeModifier existing = maxHealthAttribute.getModifier(NuclearDecayEffect.DECAY_MODIFIER_ID);
        if (existing == null) return;

        double currentPenalty = existing.amount();
        maxHealthAttribute.removeModifier(NuclearDecayEffect.DECAY_MODIFIER_ID);

        double newPenalty = Math.min(0.0D, currentPenalty + HEAL_PER_USE);

        if (newPenalty < -0.0001D) {
            maxHealthAttribute.addPermanentModifier(new AttributeModifier(
                    NuclearDecayEffect.DECAY_MODIFIER_ID, newPenalty, AttributeModifier.Operation.ADD_VALUE));
        }
    }
}
