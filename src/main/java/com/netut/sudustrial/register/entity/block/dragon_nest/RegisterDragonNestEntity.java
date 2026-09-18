package com.netut.sudustrial.register.entity.block.dragon_nest;

import com.netut.sudustrial.Sudustrial;
import com.netut.sudustrial.dragon.entity.block.DragonNestBlockEntity;
import com.netut.sudustrial.register.block.dragon_nest.RegisterDragonNest;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class RegisterDragonNestEntity {
    public static BlockEntityType<DragonNestBlockEntity> DRAGON_NEST;

    public static void register() {
        DRAGON_NEST = Registry.register(
                BuiltInRegistries.BLOCK_ENTITY_TYPE,
                ResourceKey.create(Registries.BLOCK_ENTITY_TYPE, Identifier.fromNamespaceAndPath(Sudustrial.MOD_ID, "dragon_nest")),
                FabricBlockEntityTypeBuilder.create(DragonNestBlockEntity::new, RegisterDragonNest.DRAGON_NEST_BLOCK).build()
        );
    }
}
