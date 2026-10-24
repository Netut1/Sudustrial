package com.netut.sudustrial.register.block;

import com.netut.sudustrial.register.block.nuclear_block.RegisterNuclearAir;
import com.netut.sudustrial.register.block.nuclear_block.RegisterNuclearCore;
import com.netut.sudustrial.register.block.potion_cauldron.RegisterPotionCauldron;
import com.netut.sudustrial.register.block.decorative.RegisterDecorativeBlocks;
import com.netut.sudustrial.register.block.tnt.*;

public class ModBlocks {
    public static void register() {
        RegisterTnt.register();
        RegisterElementalTnt.register();
        RegisterSpawnerTnt.register();
        RegisterEffectTnt.register();
        RegisterMiningTnt.register();
        RegisterNuclearTnt.register();
        RegisterNuclearAir.register();
        RegisterNuclearCore.register();
        RegisterPotionCauldron.register();
        RegisterDecorativeBlocks.register();
    }
}
