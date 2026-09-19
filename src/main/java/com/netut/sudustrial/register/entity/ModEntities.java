package com.netut.sudustrial.register.entity;

import com.netut.sudustrial.register.entity.block.dragon_nest.RegisterDragonNestEntity;
import com.netut.sudustrial.register.entity.block.nuclear_core.RegisterNuclearCoreEntity;
import com.netut.sudustrial.register.entity.block.potion_cauldron.RegisterPotionCauldronEntity;
import com.netut.sudustrial.register.entity.block.tnt.RegisterTntEntity;
import com.netut.sudustrial.register.entity.living.dragon.RegisterCustomEnderDragonEntity;

public class ModEntities {
    public static void register() {
        RegisterTntEntity.register();
        RegisterNuclearCoreEntity.register();
        RegisterPotionCauldronEntity.register();
        RegisterDragonNestEntity.register();
        RegisterCustomEnderDragonEntity.register();
    }
}
