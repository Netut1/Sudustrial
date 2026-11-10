package com.netut.sudustrial.tnt.type.block;

import com.netut.sudustrial.tnt.action.explosion.Explodes;
import com.netut.sudustrial.tnt.block.TntVariant;
import com.netut.sudustrial.tnt.entity.CustomTntEntity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import org.jspecify.annotations.NonNull;

import java.util.function.Supplier;

public enum EffectTntType implements TntVariant {
    JUMP_BOOST_TIER_1("jump_boost_tnt_tier_1", () -> MobEffects.JUMP_BOOST, 1, true),
    JUMP_BOOST_TIER_2("jump_boost_tnt_tier_2", () -> MobEffects.JUMP_BOOST, 2, true),
    JUMP_BOOST_TIER_3("jump_boost_tnt_tier_3", () -> MobEffects.JUMP_BOOST, 3, true),
    JUMP_BOOST_TIER_4("jump_boost_tnt_tier_4", () -> MobEffects.JUMP_BOOST, 4, true),
    JUMP_BOOST_TIER_5("jump_boost_tnt_tier_5", () -> MobEffects.JUMP_BOOST, 5, true),
    JUMP_BOOST_TIER_6("jump_boost_tnt_tier_6", () -> MobEffects.JUMP_BOOST, 6, true),
    JUMP_BOOST_TIER_7("jump_boost_tnt_tier_7", () -> MobEffects.JUMP_BOOST, 7, true),
    JUMP_BOOST_TIER_8("jump_boost_tnt_tier_8", () -> MobEffects.JUMP_BOOST, 8, true),
    JUMP_BOOST_TIER_9("jump_boost_tnt_tier_9", () -> MobEffects.JUMP_BOOST, 9, true),
    JUMP_BOOST_TIER_10("jump_boost_tnt_tier_10", () -> MobEffects.JUMP_BOOST, 10, true),

    FIRE_RESISTANCE_TIER_1("fire_resistance_tnt_tier_1", () -> MobEffects.FIRE_RESISTANCE, 1, true),
    FIRE_RESISTANCE_TIER_2("fire_resistance_tnt_tier_2", () -> MobEffects.FIRE_RESISTANCE, 2, true),
    FIRE_RESISTANCE_TIER_3("fire_resistance_tnt_tier_3", () -> MobEffects.FIRE_RESISTANCE, 3, true),
    FIRE_RESISTANCE_TIER_4("fire_resistance_tnt_tier_4", () -> MobEffects.FIRE_RESISTANCE, 4, true),
    FIRE_RESISTANCE_TIER_5("fire_resistance_tnt_tier_5", () -> MobEffects.FIRE_RESISTANCE, 5, true),
    FIRE_RESISTANCE_TIER_6("fire_resistance_tnt_tier_6", () -> MobEffects.FIRE_RESISTANCE, 6, true),
    FIRE_RESISTANCE_TIER_7("fire_resistance_tnt_tier_7", () -> MobEffects.FIRE_RESISTANCE, 7, true),
    FIRE_RESISTANCE_TIER_8("fire_resistance_tnt_tier_8", () -> MobEffects.FIRE_RESISTANCE, 8, true),
    FIRE_RESISTANCE_TIER_9("fire_resistance_tnt_tier_9", () -> MobEffects.FIRE_RESISTANCE, 9, true),
    FIRE_RESISTANCE_TIER_10("fire_resistance_tnt_tier_10", () -> MobEffects.FIRE_RESISTANCE, 10, true),

    SPEED_TIER_1("speed_tnt_tier_1", () -> MobEffects.SPEED, 1, true),
    SPEED_TIER_2("speed_tnt_tier_2", () -> MobEffects.SPEED, 2, true),
    SPEED_TIER_3("speed_tnt_tier_3", () -> MobEffects.SPEED, 3, true),
    SPEED_TIER_4("speed_tnt_tier_4", () -> MobEffects.SPEED, 4, true),
    SPEED_TIER_5("speed_tnt_tier_5", () -> MobEffects.SPEED, 5, true),
    SPEED_TIER_6("speed_tnt_tier_6", () -> MobEffects.SPEED, 6, true),
    SPEED_TIER_7("speed_tnt_tier_7", () -> MobEffects.SPEED, 7, true),
    SPEED_TIER_8("speed_tnt_tier_8", () -> MobEffects.SPEED, 8, true),
    SPEED_TIER_9("speed_tnt_tier_9", () -> MobEffects.SPEED, 9, true),
    SPEED_TIER_10("speed_tnt_tier_10", () -> MobEffects.SPEED, 10, true),

    SLOWNESS_TIER_1("slowness_tnt_tier_1", () -> MobEffects.SLOWNESS, 1, true),
    SLOWNESS_TIER_2("slowness_tnt_tier_2", () -> MobEffects.SLOWNESS, 2, true),
    SLOWNESS_TIER_3("slowness_tnt_tier_3", () -> MobEffects.SLOWNESS, 3, true),
    SLOWNESS_TIER_4("slowness_tnt_tier_4", () -> MobEffects.SLOWNESS, 4, true),
    SLOWNESS_TIER_5("slowness_tnt_tier_5", () -> MobEffects.SLOWNESS, 5, true),
    SLOWNESS_TIER_6("slowness_tnt_tier_6", () -> MobEffects.SLOWNESS, 6, true),
    SLOWNESS_TIER_7("slowness_tnt_tier_7", () -> MobEffects.SLOWNESS, 7, true),
    SLOWNESS_TIER_8("slowness_tnt_tier_8", () -> MobEffects.SLOWNESS, 8, true),
    SLOWNESS_TIER_9("slowness_tnt_tier_9", () -> MobEffects.SLOWNESS, 9, true),
    SLOWNESS_TIER_10("slowness_tnt_tier_10", () -> MobEffects.SLOWNESS, 10, true),

    WATER_BREATHING_TIER_1("water_breathing_tnt_tier_1", () -> MobEffects.WATER_BREATHING, 1, true),
    WATER_BREATHING_TIER_2("water_breathing_tnt_tier_2", () -> MobEffects.WATER_BREATHING, 2, true),
    WATER_BREATHING_TIER_3("water_breathing_tnt_tier_3", () -> MobEffects.WATER_BREATHING, 3, true),
    WATER_BREATHING_TIER_4("water_breathing_tnt_tier_4", () -> MobEffects.WATER_BREATHING, 4, true),
    WATER_BREATHING_TIER_5("water_breathing_tnt_tier_5", () -> MobEffects.WATER_BREATHING, 5, true),
    WATER_BREATHING_TIER_6("water_breathing_tnt_tier_6", () -> MobEffects.WATER_BREATHING, 6, true),
    WATER_BREATHING_TIER_7("water_breathing_tnt_tier_7", () -> MobEffects.WATER_BREATHING, 7, true),
    WATER_BREATHING_TIER_8("water_breathing_tnt_tier_8", () -> MobEffects.WATER_BREATHING, 8, true),
    WATER_BREATHING_TIER_9("water_breathing_tnt_tier_9", () -> MobEffects.WATER_BREATHING, 9, true),
    WATER_BREATHING_TIER_10("water_breathing_tnt_tier_10", () -> MobEffects.WATER_BREATHING, 10, true),

    POISON_TIER_1("poison_tnt_tier_1", () -> MobEffects.POISON, 1, true),
    POISON_TIER_2("poison_tnt_tier_2", () -> MobEffects.POISON, 2, true),
    POISON_TIER_3("poison_tnt_tier_3", () -> MobEffects.POISON, 3, true),
    POISON_TIER_4("poison_tnt_tier_4", () -> MobEffects.POISON, 4, true),
    POISON_TIER_5("poison_tnt_tier_5", () -> MobEffects.POISON, 5, true),
    POISON_TIER_6("poison_tnt_tier_6", () -> MobEffects.POISON, 6, true),
    POISON_TIER_7("poison_tnt_tier_7", () -> MobEffects.POISON, 7, true),
    POISON_TIER_8("poison_tnt_tier_8", () -> MobEffects.POISON, 8, true),
    POISON_TIER_9("poison_tnt_tier_9", () -> MobEffects.POISON, 9, true),
    POISON_TIER_10("poison_tnt_tier_10", () -> MobEffects.POISON, 10, true),

    REGENERATION_TIER_1("regeneration_tnt_tier_1", () -> MobEffects.REGENERATION, 1, true),
    REGENERATION_TIER_2("regeneration_tnt_tier_2", () -> MobEffects.REGENERATION, 2, true),
    REGENERATION_TIER_3("regeneration_tnt_tier_3", () -> MobEffects.REGENERATION, 3, true),
    REGENERATION_TIER_4("regeneration_tnt_tier_4", () -> MobEffects.REGENERATION, 4, true),
    REGENERATION_TIER_5("regeneration_tnt_tier_5", () -> MobEffects.REGENERATION, 5, true),
    REGENERATION_TIER_6("regeneration_tnt_tier_6", () -> MobEffects.REGENERATION, 6, true),
    REGENERATION_TIER_7("regeneration_tnt_tier_7", () -> MobEffects.REGENERATION, 7, true),
    REGENERATION_TIER_8("regeneration_tnt_tier_8", () -> MobEffects.REGENERATION, 8, true),
    REGENERATION_TIER_9("regeneration_tnt_tier_9", () -> MobEffects.REGENERATION, 9, true),
    REGENERATION_TIER_10("regeneration_tnt_tier_10", () -> MobEffects.REGENERATION, 10, true),

    STRENGTH_TIER_1("strength_tnt_tier_1", () -> MobEffects.STRENGTH, 1, true),
    STRENGTH_TIER_2("strength_tnt_tier_2", () -> MobEffects.STRENGTH, 2, true),
    STRENGTH_TIER_3("strength_tnt_tier_3", () -> MobEffects.STRENGTH, 3, true),
    STRENGTH_TIER_4("strength_tnt_tier_4", () -> MobEffects.STRENGTH, 4, true),
    STRENGTH_TIER_5("strength_tnt_tier_5", () -> MobEffects.STRENGTH, 5, true),
    STRENGTH_TIER_6("strength_tnt_tier_6", () -> MobEffects.STRENGTH, 6, true),
    STRENGTH_TIER_7("strength_tnt_tier_7", () -> MobEffects.STRENGTH, 7, true),
    STRENGTH_TIER_8("strength_tnt_tier_8", () -> MobEffects.STRENGTH, 8, true),
    STRENGTH_TIER_9("strength_tnt_tier_9", () -> MobEffects.STRENGTH, 9, true),
    STRENGTH_TIER_10("strength_tnt_tier_10", () -> MobEffects.STRENGTH, 10, true),

    WEAKNESS_TIER_1("weakness_tnt_tier_1", () -> MobEffects.WEAKNESS, 1, true),
    WEAKNESS_TIER_2("weakness_tnt_tier_2", () -> MobEffects.WEAKNESS, 2, true),
    WEAKNESS_TIER_3("weakness_tnt_tier_3", () -> MobEffects.WEAKNESS, 3, true),
    WEAKNESS_TIER_4("weakness_tnt_tier_4", () -> MobEffects.WEAKNESS, 4, true),
    WEAKNESS_TIER_5("weakness_tnt_tier_5", () -> MobEffects.WEAKNESS, 5, true),
    WEAKNESS_TIER_6("weakness_tnt_tier_6", () -> MobEffects.WEAKNESS, 6, true),
    WEAKNESS_TIER_7("weakness_tnt_tier_7", () -> MobEffects.WEAKNESS, 7, true),
    WEAKNESS_TIER_8("weakness_tnt_tier_8", () -> MobEffects.WEAKNESS, 8, true),
    WEAKNESS_TIER_9("weakness_tnt_tier_9", () -> MobEffects.WEAKNESS, 9, true),
    WEAKNESS_TIER_10("weakness_tnt_tier_10", () -> MobEffects.WEAKNESS, 10, true),

    INVISIBILITY_TIER_1("invisibility_tnt_tier_1", () -> MobEffects.INVISIBILITY, 1, false),
    INVISIBILITY_TIER_2("invisibility_tnt_tier_2", () -> MobEffects.INVISIBILITY, 2, false),
    INVISIBILITY_TIER_3("invisibility_tnt_tier_3", () -> MobEffects.INVISIBILITY, 3, false),
    INVISIBILITY_TIER_4("invisibility_tnt_tier_4", () -> MobEffects.INVISIBILITY, 4, false),
    INVISIBILITY_TIER_5("invisibility_tnt_tier_5", () -> MobEffects.INVISIBILITY, 5, false),
    INVISIBILITY_TIER_6("invisibility_tnt_tier_6", () -> MobEffects.INVISIBILITY, 6, false),
    INVISIBILITY_TIER_7("invisibility_tnt_tier_7", () -> MobEffects.INVISIBILITY, 7, false),
    INVISIBILITY_TIER_8("invisibility_tnt_tier_8", () -> MobEffects.INVISIBILITY, 8, false),
    INVISIBILITY_TIER_9("invisibility_tnt_tier_9", () -> MobEffects.INVISIBILITY, 9, false),
    INVISIBILITY_TIER_10("invisibility_tnt_tier_10", () -> MobEffects.INVISIBILITY, 10, false),

    TURTLE_MASTER_TIER_1("turtle_master_tnt_tier_1", () -> MobEffects.RESISTANCE, 1, false),
    TURTLE_MASTER_TIER_2("turtle_master_tnt_tier_2", () -> MobEffects.RESISTANCE, 2, false),
    TURTLE_MASTER_TIER_3("turtle_master_tnt_tier_3", () -> MobEffects.RESISTANCE, 3, false),
    TURTLE_MASTER_TIER_4("turtle_master_tnt_tier_4", () -> MobEffects.RESISTANCE, 4, false),
    TURTLE_MASTER_TIER_5("turtle_master_tnt_tier_5", () -> MobEffects.RESISTANCE, 5, false),
    TURTLE_MASTER_TIER_6("turtle_master_tnt_tier_6", () -> MobEffects.RESISTANCE, 6, false),
    TURTLE_MASTER_TIER_7("turtle_master_tnt_tier_7", () -> MobEffects.RESISTANCE, 7, false),
    TURTLE_MASTER_TIER_8("turtle_master_tnt_tier_8", () -> MobEffects.RESISTANCE, 8, false),
    TURTLE_MASTER_TIER_9("turtle_master_tnt_tier_9", () -> MobEffects.RESISTANCE, 9, false),
    TURTLE_MASTER_TIER_10("turtle_master_tnt_tier_10", () -> MobEffects.RESISTANCE, 10, false),

    HEALING_TIER_1("healing_tnt_tier_1", () -> MobEffects.INSTANT_HEALTH, 1, false),
    HEALING_TIER_2("healing_tnt_tier_2", () -> MobEffects.INSTANT_HEALTH, 2, false),
    HEALING_TIER_3("healing_tnt_tier_3", () -> MobEffects.INSTANT_HEALTH, 3, false),
    HEALING_TIER_4("healing_tnt_tier_4", () -> MobEffects.INSTANT_HEALTH, 4, false),
    HEALING_TIER_5("healing_tnt_tier_5", () -> MobEffects.INSTANT_HEALTH, 5, false),
    HEALING_TIER_6("healing_tnt_tier_6", () -> MobEffects.INSTANT_HEALTH, 6, false),
    HEALING_TIER_7("healing_tnt_tier_7", () -> MobEffects.INSTANT_HEALTH, 7, false),
    HEALING_TIER_8("healing_tnt_tier_8", () -> MobEffects.INSTANT_HEALTH, 8, false),
    HEALING_TIER_9("healing_tnt_tier_9", () -> MobEffects.INSTANT_HEALTH, 9, false),
    HEALING_TIER_10("healing_tnt_tier_10", () -> MobEffects.INSTANT_HEALTH, 10, false),

    HARM_TIER_1("harm_tnt_tier_1", () -> MobEffects.INSTANT_DAMAGE, 1, false),
    HARM_TIER_2("harm_tnt_tier_2", () -> MobEffects.INSTANT_DAMAGE, 2, false),
    HARM_TIER_3("harm_tnt_tier_3", () -> MobEffects.INSTANT_DAMAGE, 3, false),
    HARM_TIER_4("harm_tnt_tier_4", () -> MobEffects.INSTANT_DAMAGE, 4, false),
    HARM_TIER_5("harm_tnt_tier_5", () -> MobEffects.INSTANT_DAMAGE, 5, false),
    HARM_TIER_6("harm_tnt_tier_6", () -> MobEffects.INSTANT_DAMAGE, 6, false),
    HARM_TIER_7("harm_tnt_tier_7", () -> MobEffects.INSTANT_DAMAGE, 7, false),
    HARM_TIER_8("harm_tnt_tier_8", () -> MobEffects.INSTANT_DAMAGE, 8, false),
    HARM_TIER_9("harm_tnt_tier_9", () -> MobEffects.INSTANT_DAMAGE, 9, false),
    HARM_TIER_10("harm_tnt_tier_10", () -> MobEffects.INSTANT_DAMAGE, 10, false),

    SLOW_FALLING_TIER_1("slow_falling_tnt_tier_1", () -> MobEffects.SLOW_FALLING, 1, false),
    SLOW_FALLING_TIER_2("slow_falling_tnt_tier_2", () -> MobEffects.SLOW_FALLING, 2, false),
    SLOW_FALLING_TIER_3("slow_falling_tnt_tier_3", () -> MobEffects.SLOW_FALLING, 3, false),
    SLOW_FALLING_TIER_4("slow_falling_tnt_tier_4", () -> MobEffects.SLOW_FALLING, 4, false),
    SLOW_FALLING_TIER_5("slow_falling_tnt_tier_5", () -> MobEffects.SLOW_FALLING, 5, false),
    SLOW_FALLING_TIER_6("slow_falling_tnt_tier_6", () -> MobEffects.SLOW_FALLING, 6, false),
    SLOW_FALLING_TIER_7("slow_falling_tnt_tier_7", () -> MobEffects.SLOW_FALLING, 7, false),
    SLOW_FALLING_TIER_8("slow_falling_tnt_tier_8", () -> MobEffects.SLOW_FALLING, 8, false),
    SLOW_FALLING_TIER_9("slow_falling_tnt_tier_9", () -> MobEffects.SLOW_FALLING, 9, false),
    SLOW_FALLING_TIER_10("slow_falling_tnt_tier_10", () -> MobEffects.SLOW_FALLING, 10, false),

    NIGHT_VISION_TIER_1("night_vision_tnt_tier_1", () -> MobEffects.NIGHT_VISION, 1, false),
    NIGHT_VISION_TIER_2("night_vision_tnt_tier_2", () -> MobEffects.NIGHT_VISION, 2, false),
    NIGHT_VISION_TIER_3("night_vision_tnt_tier_3", () -> MobEffects.NIGHT_VISION, 3, false),
    NIGHT_VISION_TIER_4("night_vision_tnt_tier_4", () -> MobEffects.NIGHT_VISION, 4, false),
    NIGHT_VISION_TIER_5("night_vision_tnt_tier_5", () -> MobEffects.NIGHT_VISION, 5, false),
    NIGHT_VISION_TIER_6("night_vision_tnt_tier_6", () -> MobEffects.NIGHT_VISION, 6, false),
    NIGHT_VISION_TIER_7("night_vision_tnt_tier_7", () -> MobEffects.NIGHT_VISION, 7, false),
    NIGHT_VISION_TIER_8("night_vision_tnt_tier_8", () -> MobEffects.NIGHT_VISION, 8, false),
    NIGHT_VISION_TIER_9("night_vision_tnt_tier_9", () -> MobEffects.NIGHT_VISION, 9, false),
    NIGHT_VISION_TIER_10("night_vision_tnt_tier_10", () -> MobEffects.NIGHT_VISION, 10, false),

    BLINDNESS_TIER_1("blindness_tnt_tier_1", () -> MobEffects.BLINDNESS, 1, false),
    BLINDNESS_TIER_2("blindness_tnt_tier_2", () -> MobEffects.BLINDNESS, 2, false),
    BLINDNESS_TIER_3("blindness_tnt_tier_3", () -> MobEffects.BLINDNESS, 3, false),
    BLINDNESS_TIER_4("blindness_tnt_tier_4", () -> MobEffects.BLINDNESS, 4, false),
    BLINDNESS_TIER_5("blindness_tnt_tier_5", () -> MobEffects.BLINDNESS, 5, false),
    BLINDNESS_TIER_6("blindness_tnt_tier_6", () -> MobEffects.BLINDNESS, 6, false),
    BLINDNESS_TIER_7("blindness_tnt_tier_7", () -> MobEffects.BLINDNESS, 7, false),
    BLINDNESS_TIER_8("blindness_tnt_tier_8", () -> MobEffects.BLINDNESS, 8, false),
    BLINDNESS_TIER_9("blindness_tnt_tier_9", () -> MobEffects.BLINDNESS, 9, false),
    BLINDNESS_TIER_10("blindness_tnt_tier_10", () -> MobEffects.BLINDNESS, 10, false),

    LEVITATION_TIER_1("levitation_tnt_tier_1", () -> MobEffects.LEVITATION, 1, false),
    LEVITATION_TIER_2("levitation_tnt_tier_2", () -> MobEffects.LEVITATION, 2, false),
    LEVITATION_TIER_3("levitation_tnt_tier_3", () -> MobEffects.LEVITATION, 3, false),
    LEVITATION_TIER_4("levitation_tnt_tier_4", () -> MobEffects.LEVITATION, 4, false),
    LEVITATION_TIER_5("levitation_tnt_tier_5", () -> MobEffects.LEVITATION, 5, false),
    LEVITATION_TIER_6("levitation_tnt_tier_6", () -> MobEffects.LEVITATION, 6, false),
    LEVITATION_TIER_7("levitation_tnt_tier_7", () -> MobEffects.LEVITATION, 7, false),
    LEVITATION_TIER_8("levitation_tnt_tier_8", () -> MobEffects.LEVITATION, 8, false),
    LEVITATION_TIER_9("levitation_tnt_tier_9", () -> MobEffects.LEVITATION, 9, false),
    LEVITATION_TIER_10("levitation_tnt_tier_10", () -> MobEffects.LEVITATION, 10, false),

    WITHER_TIER_1("wither_tnt_tier_1", () -> MobEffects.WITHER, 1, false),
    WITHER_TIER_2("wither_tnt_tier_2", () -> MobEffects.WITHER, 2, false),
    WITHER_TIER_3("wither_tnt_tier_3", () -> MobEffects.WITHER, 3, false),
    WITHER_TIER_4("wither_tnt_tier_4", () -> MobEffects.WITHER, 4, false),
    WITHER_TIER_5("wither_tnt_tier_5", () -> MobEffects.WITHER, 5, false),
    WITHER_TIER_6("wither_tnt_tier_6", () -> MobEffects.WITHER, 6, false),
    WITHER_TIER_7("wither_tnt_tier_7", () -> MobEffects.WITHER, 7, false),
    WITHER_TIER_8("wither_tnt_tier_8", () -> MobEffects.WITHER, 8, false),
    WITHER_TIER_9("wither_tnt_tier_9", () -> MobEffects.WITHER, 9, false),
    WITHER_TIER_10("wither_tnt_tier_10", () -> MobEffects.WITHER, 10, false);

    private final String name;
    private final Supplier<Holder<MobEffect>> effectSupplier;
    private final float radius;
    private final int duration; // Время жизни зоны в тиках
    private final int amplifier; // уровень эффекта (0 = I)
    private final boolean linger; // true = накладывает постоянный эффект, false = работает только внутри зоны

    EffectTntType(String name, Supplier<Holder<MobEffect>> effectSupplier, int tier, boolean linger) {
        this.name = name;
        this.effectSupplier = effectSupplier;
        this.radius = TntTiers.effectRadius(tier);
        this.duration = TntTiers.effectDuration(tier);
        this.amplifier = TntTiers.effectAmplifier(tier);
        this.linger = linger;
    }

    public Holder<MobEffect> getEffect() { return effectSupplier.get(); }
    public float getRadius() { return radius; }
    public int getDuration() { return duration; }
    public int getAmplifier() { return amplifier; }
    public boolean isLinger() { return linger; }

    @Override
    public void explode(ServerLevel level, CustomTntEntity tnt) {
        Explodes.effectExplode(level, tnt, this);
    }

    @Override
    @NonNull
    public String getSerializedName() { return this.name; }
}
