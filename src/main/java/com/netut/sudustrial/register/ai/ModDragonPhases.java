package com.netut.sudustrial.register.ai;

import com.netut.sudustrial.dragon.ai_phase.CustomEnderDragonIdlePhase;
import com.netut.sudustrial.mixin.EnderDragonPhaseAccessor;
import net.minecraft.world.entity.boss.enderdragon.phases.EnderDragonPhase;

public class ModDragonPhases {
    public static final EnderDragonPhase<CustomEnderDragonIdlePhase> JEAN_IDLE =
            EnderDragonPhaseAccessor.sudustrial$create(CustomEnderDragonIdlePhase.class, "JeanIdle");

    public static void registerModDragonPhases() {
    }
}
