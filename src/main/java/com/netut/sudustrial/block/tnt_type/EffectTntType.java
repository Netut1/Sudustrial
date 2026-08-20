package com.netut.sudustrial.block.tnt_type;

import net.minecraft.core.Holder;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import org.jspecify.annotations.NonNull;

import java.util.function.Supplier;

public enum EffectTntType implements StringRepresentable {
    JUMP_BOOST_TIER_1("jump_boost_tnt_tier_1", () -> MobEffects.JUMP_BOOST, 5.0F, 20, 0, true),
    JUMP_BOOST_TIER_2("jump_boost_tnt_tier_2", () -> MobEffects.JUMP_BOOST, 5.0F, 30, 1, true),
    JUMP_BOOST_TIER_3("jump_boost_tnt_tier_3", () -> MobEffects.JUMP_BOOST, 10.0F, 40, 1, true),
    JUMP_BOOST_TIER_4("jump_boost_tnt_tier_4", () -> MobEffects.JUMP_BOOST, 10.0F, 60, 2, true),
    JUMP_BOOST_TIER_5("jump_boost_tnt_tier_5", () -> MobEffects.JUMP_BOOST, 15.0F, 80, 2, true),
    JUMP_BOOST_TIER_6("jump_boost_tnt_tier_6", () -> MobEffects.JUMP_BOOST, 15.0F, 100, 3, true),
    JUMP_BOOST_TIER_7("jump_boost_tnt_tier_7", () -> MobEffects.JUMP_BOOST, 20.0F, 130, 3, true),
    JUMP_BOOST_TIER_8("jump_boost_tnt_tier_8", () -> MobEffects.JUMP_BOOST, 20.0F, 160, 4, true),
    JUMP_BOOST_TIER_9("jump_boost_tnt_tier_9", () -> MobEffects.JUMP_BOOST, 25.0F, 190, 4, true),
    JUMP_BOOST_TIER_10("jump_boost_tnt_tier_10", () -> MobEffects.JUMP_BOOST, 30.0F, 250, 5, true),

    FIRE_RESISTANCE_TIER_1("fire_resistance_tnt_tier_1", () -> MobEffects.FIRE_RESISTANCE, 5.0F, 20, 0, true),
    FIRE_RESISTANCE_TIER_2("fire_resistance_tnt_tier_2", () -> MobEffects.FIRE_RESISTANCE, 5.0F, 30, 1, true),
    FIRE_RESISTANCE_TIER_3("fire_resistance_tnt_tier_3", () -> MobEffects.FIRE_RESISTANCE, 10.0F, 40, 1, true),
    FIRE_RESISTANCE_TIER_4("fire_resistance_tnt_tier_4", () -> MobEffects.FIRE_RESISTANCE, 10.0F, 60, 2, true),
    FIRE_RESISTANCE_TIER_5("fire_resistance_tnt_tier_5", () -> MobEffects.FIRE_RESISTANCE, 15.0F, 80, 2, true),
    FIRE_RESISTANCE_TIER_6("fire_resistance_tnt_tier_6", () -> MobEffects.FIRE_RESISTANCE, 15.0F, 100, 3, true),
    FIRE_RESISTANCE_TIER_7("fire_resistance_tnt_tier_7", () -> MobEffects.FIRE_RESISTANCE, 20.0F, 130, 3, true),
    FIRE_RESISTANCE_TIER_8("fire_resistance_tnt_tier_8", () -> MobEffects.FIRE_RESISTANCE, 20.0F, 160, 4, true),
    FIRE_RESISTANCE_TIER_9("fire_resistance_tnt_tier_9", () -> MobEffects.FIRE_RESISTANCE, 25.0F, 190, 4, true),
    FIRE_RESISTANCE_TIER_10("fire_resistance_tnt_tier_10", () -> MobEffects.FIRE_RESISTANCE, 30.0F, 250, 5, true),

    SPEED_TIER_1("speed_tnt_tier_1", () -> MobEffects.SPEED, 5.0F, 20, 0, true),
    SPEED_TIER_2("speed_tnt_tier_2", () -> MobEffects.SPEED, 5.0F, 30, 1, true),
    SPEED_TIER_3("speed_tnt_tier_3", () -> MobEffects.SPEED, 10.0F, 40, 1, true),
    SPEED_TIER_4("speed_tnt_tier_4", () -> MobEffects.SPEED, 10.0F, 60, 2, true),
    SPEED_TIER_5("speed_tnt_tier_5", () -> MobEffects.SPEED, 15.0F, 80, 2, true),
    SPEED_TIER_6("speed_tnt_tier_6", () -> MobEffects.SPEED, 15.0F, 100, 3, true),
    SPEED_TIER_7("speed_tnt_tier_7", () -> MobEffects.SPEED, 20.0F, 130, 3, true),
    SPEED_TIER_8("speed_tnt_tier_8", () -> MobEffects.SPEED, 20.0F, 160, 4, true),
    SPEED_TIER_9("speed_tnt_tier_9", () -> MobEffects.SPEED, 25.0F, 190, 4, true),
    SPEED_TIER_10("speed_tnt_tier_10", () -> MobEffects.SPEED, 30.0F, 250, 5, true),

    SLOWNESS_TIER_1("slowness_tnt_tier_1", () -> MobEffects.SLOWNESS, 5.0F, 20, 0, true),
    SLOWNESS_TIER_2("slowness_tnt_tier_2", () -> MobEffects.SLOWNESS, 5.0F, 30, 1, true),
    SLOWNESS_TIER_3("slowness_tnt_tier_3", () -> MobEffects.SLOWNESS, 10.0F, 40, 1, true),
    SLOWNESS_TIER_4("slowness_tnt_tier_4", () -> MobEffects.SLOWNESS, 10.0F, 60, 2, true),
    SLOWNESS_TIER_5("slowness_tnt_tier_5", () -> MobEffects.SLOWNESS, 15.0F, 80, 2, true),
    SLOWNESS_TIER_6("slowness_tnt_tier_6", () -> MobEffects.SLOWNESS, 15.0F, 100, 3, true),
    SLOWNESS_TIER_7("slowness_tnt_tier_7", () -> MobEffects.SLOWNESS, 20.0F, 130, 3, true),
    SLOWNESS_TIER_8("slowness_tnt_tier_8", () -> MobEffects.SLOWNESS, 20.0F, 160, 4, true),
    SLOWNESS_TIER_9("slowness_tnt_tier_9", () -> MobEffects.SLOWNESS, 25.0F, 190, 4, true),
    SLOWNESS_TIER_10("slowness_tnt_tier_10", () -> MobEffects.SLOWNESS, 30.0F, 250, 5, true),

    WATER_BREATHING_TIER_1("water_breathing_tnt_tier_1", () -> MobEffects.WATER_BREATHING, 5.0F, 20, 0, true),
    WATER_BREATHING_TIER_2("water_breathing_tnt_tier_2", () -> MobEffects.WATER_BREATHING, 5.0F, 30, 1, true),
    WATER_BREATHING_TIER_3("water_breathing_tnt_tier_3", () -> MobEffects.WATER_BREATHING, 10.0F, 40, 1, true),
    WATER_BREATHING_TIER_4("water_breathing_tnt_tier_4", () -> MobEffects.WATER_BREATHING, 10.0F, 60, 2, true),
    WATER_BREATHING_TIER_5("water_breathing_tnt_tier_5", () -> MobEffects.WATER_BREATHING, 15.0F, 80, 2, true),
    WATER_BREATHING_TIER_6("water_breathing_tnt_tier_6", () -> MobEffects.WATER_BREATHING, 15.0F, 100, 3, true),
    WATER_BREATHING_TIER_7("water_breathing_tnt_tier_7", () -> MobEffects.WATER_BREATHING, 20.0F, 130, 3, true),
    WATER_BREATHING_TIER_8("water_breathing_tnt_tier_8", () -> MobEffects.WATER_BREATHING, 20.0F, 160, 4, true),
    WATER_BREATHING_TIER_9("water_breathing_tnt_tier_9", () -> MobEffects.WATER_BREATHING, 25.0F, 190, 4, true),
    WATER_BREATHING_TIER_10("water_breathing_tnt_tier_10", () -> MobEffects.WATER_BREATHING, 30.0F, 250, 5, true),

    POISON_TIER_1("poison_tnt_tier_1", () -> MobEffects.POISON, 5.0F, 20, 0, true),
    POISON_TIER_2("poison_tnt_tier_2", () -> MobEffects.POISON, 5.0F, 30, 1, true),
    POISON_TIER_3("poison_tnt_tier_3", () -> MobEffects.POISON, 10.0F, 40, 1, true),
    POISON_TIER_4("poison_tnt_tier_4", () -> MobEffects.POISON, 10.0F, 60, 2, true),
    POISON_TIER_5("poison_tnt_tier_5", () -> MobEffects.POISON, 15.0F, 80, 2, true),
    POISON_TIER_6("poison_tnt_tier_6", () -> MobEffects.POISON, 15.0F, 100, 3, true),
    POISON_TIER_7("poison_tnt_tier_7", () -> MobEffects.POISON, 20.0F, 130, 3, true),
    POISON_TIER_8("poison_tnt_tier_8", () -> MobEffects.POISON, 20.0F, 160, 4, true),
    POISON_TIER_9("poison_tnt_tier_9", () -> MobEffects.POISON, 25.0F, 190, 4, true),
    POISON_TIER_10("poison_tnt_tier_10", () -> MobEffects.POISON, 30.0F, 250, 5, true),

    REGENERATION_TIER_1("regeneration_tnt_tier_1", () -> MobEffects.REGENERATION, 5.0F, 20, 0, true),
    REGENERATION_TIER_2("regeneration_tnt_tier_2", () -> MobEffects.REGENERATION, 5.0F, 30, 1, true),
    REGENERATION_TIER_3("regeneration_tnt_tier_3", () -> MobEffects.REGENERATION, 10.0F, 40, 1, true),
    REGENERATION_TIER_4("regeneration_tnt_tier_4", () -> MobEffects.REGENERATION, 10.0F, 60, 2, true),
    REGENERATION_TIER_5("regeneration_tnt_tier_5", () -> MobEffects.REGENERATION, 15.0F, 80, 2, true),
    REGENERATION_TIER_6("regeneration_tnt_tier_6", () -> MobEffects.REGENERATION, 15.0F, 100, 3, true),
    REGENERATION_TIER_7("regeneration_tnt_tier_7", () -> MobEffects.REGENERATION, 20.0F, 130, 3, true),
    REGENERATION_TIER_8("regeneration_tnt_tier_8", () -> MobEffects.REGENERATION, 20.0F, 160, 4, true),
    REGENERATION_TIER_9("regeneration_tnt_tier_9", () -> MobEffects.REGENERATION, 25.0F, 190, 4, true),
    REGENERATION_TIER_10("regeneration_tnt_tier_10", () -> MobEffects.REGENERATION, 30.0F, 250, 5, true),

    STRENGTH_TIER_1("strength_tnt_tier_1", () -> MobEffects.STRENGTH, 5.0F, 20, 0, true),
    STRENGTH_TIER_2("strength_tnt_tier_2", () -> MobEffects.STRENGTH, 5.0F, 30, 1, true),
    STRENGTH_TIER_3("strength_tnt_tier_3", () -> MobEffects.STRENGTH, 10.0F, 40, 1, true),
    STRENGTH_TIER_4("strength_tnt_tier_4", () -> MobEffects.STRENGTH, 10.0F, 60, 2, true),
    STRENGTH_TIER_5("strength_tnt_tier_5", () -> MobEffects.STRENGTH, 15.0F, 80, 2, true),
    STRENGTH_TIER_6("strength_tnt_tier_6", () -> MobEffects.STRENGTH, 15.0F, 100, 3, true),
    STRENGTH_TIER_7("strength_tnt_tier_7", () -> MobEffects.STRENGTH, 20.0F, 130, 3, true),
    STRENGTH_TIER_8("strength_tnt_tier_8", () -> MobEffects.STRENGTH, 20.0F, 160, 4, true),
    STRENGTH_TIER_9("strength_tnt_tier_9", () -> MobEffects.STRENGTH, 25.0F, 190, 4, true),
    STRENGTH_TIER_10("strength_tnt_tier_10", () -> MobEffects.STRENGTH, 30.0F, 250, 5, true),

    WEAKNESS_TIER_1("weakness_tnt_tier_1", () -> MobEffects.WEAKNESS, 5.0F, 20, 0, true),
    WEAKNESS_TIER_2("weakness_tnt_tier_2", () -> MobEffects.WEAKNESS, 5.0F, 30, 1, true),
    WEAKNESS_TIER_3("weakness_tnt_tier_3", () -> MobEffects.WEAKNESS, 10.0F, 40, 1, true),
    WEAKNESS_TIER_4("weakness_tnt_tier_4", () -> MobEffects.WEAKNESS, 10.0F, 60, 2, true),
    WEAKNESS_TIER_5("weakness_tnt_tier_5", () -> MobEffects.WEAKNESS, 15.0F, 80, 2, true),
    WEAKNESS_TIER_6("weakness_tnt_tier_6", () -> MobEffects.WEAKNESS, 15.0F, 100, 3, true),
    WEAKNESS_TIER_7("weakness_tnt_tier_7", () -> MobEffects.WEAKNESS, 20.0F, 130, 3, true),
    WEAKNESS_TIER_8("weakness_tnt_tier_8", () -> MobEffects.WEAKNESS, 20.0F, 160, 4, true),
    WEAKNESS_TIER_9("weakness_tnt_tier_9", () -> MobEffects.WEAKNESS, 25.0F, 190, 4, true),
    WEAKNESS_TIER_10("weakness_tnt_tier_10", () -> MobEffects.WEAKNESS, 30.0F, 250, 5, true),

    INVISIBILITY_TIER_1("invisibility_tnt_tier_1", () -> MobEffects.INVISIBILITY, 5.0F, 20, 0, false),
    INVISIBILITY_TIER_2("invisibility_tnt_tier_2", () -> MobEffects.INVISIBILITY, 5.0F, 30, 1, false),
    INVISIBILITY_TIER_3("invisibility_tnt_tier_3", () -> MobEffects.INVISIBILITY, 10.0F, 40, 1, false),
    INVISIBILITY_TIER_4("invisibility_tnt_tier_4", () -> MobEffects.INVISIBILITY, 10.0F, 60, 2, false),
    INVISIBILITY_TIER_5("invisibility_tnt_tier_5", () -> MobEffects.INVISIBILITY, 15.0F, 80, 2, false),
    INVISIBILITY_TIER_6("invisibility_tnt_tier_6", () -> MobEffects.INVISIBILITY, 15.0F, 100, 3, false),
    INVISIBILITY_TIER_7("invisibility_tnt_tier_7", () -> MobEffects.INVISIBILITY, 20.0F, 130, 3, false),
    INVISIBILITY_TIER_8("invisibility_tnt_tier_8", () -> MobEffects.INVISIBILITY, 20.0F, 160, 4, false),
    INVISIBILITY_TIER_9("invisibility_tnt_tier_9", () -> MobEffects.INVISIBILITY, 25.0F, 190, 4, false),
    INVISIBILITY_TIER_10("invisibility_tnt_tier_10", () -> MobEffects.INVISIBILITY, 30.0F, 250, 5, false),

    TURTLE_MASTER_TIER_1("turtle_master_tnt_tier_1", () -> MobEffects.RESISTANCE, 5.0F, 20, 0, false),
    TURTLE_MASTER_TIER_2("turtle_master_tnt_tier_2", () -> MobEffects.RESISTANCE, 5.0F, 30, 1, false),
    TURTLE_MASTER_TIER_3("turtle_master_tnt_tier_3", () -> MobEffects.RESISTANCE, 10.0F, 40, 1, false),
    TURTLE_MASTER_TIER_4("turtle_master_tnt_tier_4", () -> MobEffects.RESISTANCE, 10.0F, 60, 2, false),
    TURTLE_MASTER_TIER_5("turtle_master_tnt_tier_5", () -> MobEffects.RESISTANCE, 15.0F, 80, 2, false),
    TURTLE_MASTER_TIER_6("turtle_master_tnt_tier_6", () -> MobEffects.RESISTANCE, 15.0F, 100, 3, false),
    TURTLE_MASTER_TIER_7("turtle_master_tnt_tier_7", () -> MobEffects.RESISTANCE, 20.0F, 130, 3, false),
    TURTLE_MASTER_TIER_8("turtle_master_tnt_tier_8", () -> MobEffects.RESISTANCE, 20.0F, 160, 4, false),
    TURTLE_MASTER_TIER_9("turtle_master_tnt_tier_9", () -> MobEffects.RESISTANCE, 25.0F, 190, 4, false),
    TURTLE_MASTER_TIER_10("turtle_master_tnt_tier_10", () -> MobEffects.RESISTANCE, 30.0F, 250, 5, false),

    HEALING_TIER_1("healing_tnt_tier_1", () -> MobEffects.INSTANT_HEALTH, 5.0F, 20, 0, false),
    HEALING_TIER_2("healing_tnt_tier_2", () -> MobEffects.INSTANT_HEALTH, 5.0F, 30, 1, false),
    HEALING_TIER_3("healing_tnt_tier_3", () -> MobEffects.INSTANT_HEALTH, 10.0F, 40, 1, false),
    HEALING_TIER_4("healing_tnt_tier_4", () -> MobEffects.INSTANT_HEALTH, 10.0F, 60, 2, false),
    HEALING_TIER_5("healing_tnt_tier_5", () -> MobEffects.INSTANT_HEALTH, 15.0F, 80, 2, false),
    HEALING_TIER_6("healing_tnt_tier_6", () -> MobEffects.INSTANT_HEALTH, 15.0F, 100, 3, false),
    HEALING_TIER_7("healing_tnt_tier_7", () -> MobEffects.INSTANT_HEALTH, 20.0F, 130, 3, false),
    HEALING_TIER_8("healing_tnt_tier_8", () -> MobEffects.INSTANT_HEALTH, 20.0F, 160, 4, false),
    HEALING_TIER_9("healing_tnt_tier_9", () -> MobEffects.INSTANT_HEALTH, 25.0F, 190, 4, false),
    HEALING_TIER_10("healing_tnt_tier_10", () -> MobEffects.INSTANT_HEALTH, 30.0F, 250, 5, false),

    HARM_TIER_1("harm_tnt_tier_1", () -> MobEffects.INSTANT_DAMAGE, 5.0F, 20, 0, false),
    HARM_TIER_2("harm_tnt_tier_2", () -> MobEffects.INSTANT_DAMAGE, 5.0F, 30, 1, false),
    HARM_TIER_3("harm_tnt_tier_3", () -> MobEffects.INSTANT_DAMAGE, 10.0F, 40, 1, false),
    HARM_TIER_4("harm_tnt_tier_4", () -> MobEffects.INSTANT_DAMAGE, 10.0F, 60, 2, false),
    HARM_TIER_5("harm_tnt_tier_5", () -> MobEffects.INSTANT_DAMAGE, 15.0F, 80, 2, false),
    HARM_TIER_6("harm_tnt_tier_6", () -> MobEffects.INSTANT_DAMAGE, 15.0F, 100, 3, false),
    HARM_TIER_7("harm_tnt_tier_7", () -> MobEffects.INSTANT_DAMAGE, 20.0F, 130, 3, false),
    HARM_TIER_8("harm_tnt_tier_8", () -> MobEffects.INSTANT_DAMAGE, 20.0F, 160, 4, false),
    HARM_TIER_9("harm_tnt_tier_9", () -> MobEffects.INSTANT_DAMAGE, 25.0F, 190, 4, false),
    HARM_TIER_10("harm_tnt_tier_10", () -> MobEffects.INSTANT_DAMAGE, 30.0F, 250, 5, false),

    SLOW_FALLING_TIER_1("slow_falling_tnt_tier_1", () -> MobEffects.SLOW_FALLING, 5.0F, 20, 0, false),
    SLOW_FALLING_TIER_2("slow_falling_tnt_tier_2", () -> MobEffects.SLOW_FALLING, 5.0F, 30, 1, false),
    SLOW_FALLING_TIER_3("slow_falling_tnt_tier_3", () -> MobEffects.SLOW_FALLING, 10.0F, 40, 1, false),
    SLOW_FALLING_TIER_4("slow_falling_tnt_tier_4", () -> MobEffects.SLOW_FALLING, 10.0F, 60, 2, false),
    SLOW_FALLING_TIER_5("slow_falling_tnt_tier_5", () -> MobEffects.SLOW_FALLING, 15.0F, 80, 2, false),
    SLOW_FALLING_TIER_6("slow_falling_tnt_tier_6", () -> MobEffects.SLOW_FALLING, 15.0F, 100, 3, false),
    SLOW_FALLING_TIER_7("slow_falling_tnt_tier_7", () -> MobEffects.SLOW_FALLING, 20.0F, 130, 3, false),
    SLOW_FALLING_TIER_8("slow_falling_tnt_tier_8", () -> MobEffects.SLOW_FALLING, 20.0F, 160, 4, false),
    SLOW_FALLING_TIER_9("slow_falling_tnt_tier_9", () -> MobEffects.SLOW_FALLING, 25.0F, 190, 4, false),
    SLOW_FALLING_TIER_10("slow_falling_tnt_tier_10", () -> MobEffects.SLOW_FALLING, 30.0F, 250, 5, false),

    NIGHT_VISION_TIER_1("night_vision_tnt_tier_1", () -> MobEffects.NIGHT_VISION, 5.0F, 20, 0, false),
    NIGHT_VISION_TIER_2("night_vision_tnt_tier_2", () -> MobEffects.NIGHT_VISION, 5.0F, 30, 1, false),
    NIGHT_VISION_TIER_3("night_vision_tnt_tier_3", () -> MobEffects.NIGHT_VISION, 10.0F, 40, 1, false),
    NIGHT_VISION_TIER_4("night_vision_tnt_tier_4", () -> MobEffects.NIGHT_VISION, 10.0F, 60, 2, false),
    NIGHT_VISION_TIER_5("night_vision_tnt_tier_5", () -> MobEffects.NIGHT_VISION, 15.0F, 80, 2, false),
    NIGHT_VISION_TIER_6("night_vision_tnt_tier_6", () -> MobEffects.NIGHT_VISION, 15.0F, 100, 3, false),
    NIGHT_VISION_TIER_7("night_vision_tnt_tier_7", () -> MobEffects.NIGHT_VISION, 20.0F, 130, 3, false),
    NIGHT_VISION_TIER_8("night_vision_tnt_tier_8", () -> MobEffects.NIGHT_VISION, 20.0F, 160, 4, false),
    NIGHT_VISION_TIER_9("night_vision_tnt_tier_9", () -> MobEffects.NIGHT_VISION, 25.0F, 190, 4, false),
    NIGHT_VISION_TIER_10("night_vision_tnt_tier_10", () -> MobEffects.NIGHT_VISION, 30.0F, 250, 5, false),

    BLINDNESS_TIER_1("blindness_tnt_tier_1", () -> MobEffects.BLINDNESS, 5.0F, 20, 0, false),
    BLINDNESS_TIER_2("blindness_tnt_tier_2", () -> MobEffects.BLINDNESS, 5.0F, 30, 1, false),
    BLINDNESS_TIER_3("blindness_tnt_tier_3", () -> MobEffects.BLINDNESS, 10.0F, 40, 1, false),
    BLINDNESS_TIER_4("blindness_tnt_tier_4", () -> MobEffects.BLINDNESS, 10.0F, 60, 2, false),
    BLINDNESS_TIER_5("blindness_tnt_tier_5", () -> MobEffects.BLINDNESS, 15.0F, 80, 2, false),
    BLINDNESS_TIER_6("blindness_tnt_tier_6", () -> MobEffects.BLINDNESS, 15.0F, 100, 3, false),
    BLINDNESS_TIER_7("blindness_tnt_tier_7", () -> MobEffects.BLINDNESS, 20.0F, 130, 3, false),
    BLINDNESS_TIER_8("blindness_tnt_tier_8", () -> MobEffects.BLINDNESS, 20.0F, 160, 4, false),
    BLINDNESS_TIER_9("blindness_tnt_tier_9", () -> MobEffects.BLINDNESS, 25.0F, 190, 4, false),
    BLINDNESS_TIER_10("blindness_tnt_tier_10", () -> MobEffects.BLINDNESS, 30.0F, 250, 5, false),

    LEVITATION_TIER_1("levitation_tnt_tier_1", () -> MobEffects.LEVITATION, 5.0F, 20, 0, false),
    LEVITATION_TIER_2("levitation_tnt_tier_2", () -> MobEffects.LEVITATION, 5.0F, 30, 1, false),
    LEVITATION_TIER_3("levitation_tnt_tier_3", () -> MobEffects.LEVITATION, 10.0F, 40, 1, false),
    LEVITATION_TIER_4("levitation_tnt_tier_4", () -> MobEffects.LEVITATION, 10.0F, 60, 2, false),
    LEVITATION_TIER_5("levitation_tnt_tier_5", () -> MobEffects.LEVITATION, 15.0F, 80, 2, false),
    LEVITATION_TIER_6("levitation_tnt_tier_6", () -> MobEffects.LEVITATION, 15.0F, 100, 3, false),
    LEVITATION_TIER_7("levitation_tnt_tier_7", () -> MobEffects.LEVITATION, 20.0F, 130, 3, false),
    LEVITATION_TIER_8("levitation_tnt_tier_8", () -> MobEffects.LEVITATION, 20.0F, 160, 4, false),
    LEVITATION_TIER_9("levitation_tnt_tier_9", () -> MobEffects.LEVITATION, 25.0F, 190, 4, false),
    LEVITATION_TIER_10("levitation_tnt_tier_10", () -> MobEffects.LEVITATION, 30.0F, 250, 5, false),

    WITHER_TIER_1("wither_tnt_tier_1", () -> MobEffects.WITHER, 5.0F, 20, 0, false),
    WITHER_TIER_2("wither_tnt_tier_2", () -> MobEffects.WITHER, 5.0F, 30, 1, false),
    WITHER_TIER_3("wither_tnt_tier_3", () -> MobEffects.WITHER, 10.0F, 40, 1, false),
    WITHER_TIER_4("wither_tnt_tier_4", () -> MobEffects.WITHER, 10.0F, 60, 2, false),
    WITHER_TIER_5("wither_tnt_tier_5", () -> MobEffects.WITHER, 15.0F, 80, 2, false),
    WITHER_TIER_6("wither_tnt_tier_6", () -> MobEffects.WITHER, 15.0F, 100, 3, false),
    WITHER_TIER_7("wither_tnt_tier_7", () -> MobEffects.WITHER, 20.0F, 130, 3, false),
    WITHER_TIER_8("wither_tnt_tier_8", () -> MobEffects.WITHER, 20.0F, 160, 4, false),
    WITHER_TIER_9("wither_tnt_tier_9", () -> MobEffects.WITHER, 25.0F, 190, 4, false),
    WITHER_TIER_10("wither_tnt_tier_10", () -> MobEffects.WITHER, 30.0F, 250, 5, false);

    private final String name;
    private final Supplier<Holder<MobEffect>> effectSupplier;
    private final float radius;
    private final int duration; // Время жизни зоны (в тиках) / Длительность накладываемого эффекта
    private final int amplifier; // уровень эффекта (0 = I)
    private final boolean linger; // true = накладывает постоянный эффект, false = работает только внутри зоны

    EffectTntType(String name, Supplier<Holder<MobEffect>> effectSupplier, float radius, int duration, int amplifier, boolean linger) {
        this.name = name;
        this.effectSupplier = effectSupplier;
        this.radius = radius;
        this.duration = duration;
        this.amplifier = amplifier;
        this.linger = linger;
    }

    public Holder<MobEffect> getEffect() { return effectSupplier.get(); }
    public float getRadius() { return radius; }
    public int getDuration() { return duration; }
    public int getAmplifier() { return amplifier; }
    public boolean isLinger() { return linger; }

    @Override
    @NonNull
    public String getSerializedName() { return this.name; }
}
