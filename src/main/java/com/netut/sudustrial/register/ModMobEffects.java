package com.netut.sudustrial.register;

import com.netut.sudustrial.Sudustrial;
import com.netut.sudustrial.effect.tnt.nuclear.NuclearDecayEffect;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class ModMobEffects {
    public static final ResourceKey<MobEffect> NUCLEAR_DECAY_KEY = ResourceKey.create(
            Registries.MOB_EFFECT,
            Identifier.fromNamespaceAndPath(Sudustrial.MOD_ID, "nuclear_decay")
    );

    // Цвет частиц — ядовито-зелёный.
    public static Holder<MobEffect> NUCLEAR_DECAY;

    public static void registerModMobEffects() {
        MobEffect effect = new NuclearDecayEffect(MobEffectCategory.HARMFUL, 0x7FFF00);
        Registry.register(BuiltInRegistries.MOB_EFFECT, NUCLEAR_DECAY_KEY, effect);
        NUCLEAR_DECAY = BuiltInRegistries.MOB_EFFECT.getOrThrow(NUCLEAR_DECAY_KEY);
    }
}
