package com.netut.sudustrial.register.entity.block.nuclear_core;

import com.netut.sudustrial.Sudustrial;
import com.netut.sudustrial.tnt.entity.NuclearCoreBlockEntity;
import com.netut.sudustrial.register.block.nuclear_block.RegisterNuclearCore;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class RegisterNuclearCoreEntity {
    public static final ResourceKey<BlockEntityType<?>> NUCLEAR_CORE_KEY = ResourceKey.create(
            Registries.BLOCK_ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(Sudustrial.MOD_ID, "nuclear_core")
    );

    public static BlockEntityType<NuclearCoreBlockEntity> NUCLEAR_CORE;

    public static void register() {
        NUCLEAR_CORE = Registry.register(
                BuiltInRegistries.BLOCK_ENTITY_TYPE,
                NUCLEAR_CORE_KEY,
                FabricBlockEntityTypeBuilder.create(NuclearCoreBlockEntity::new, RegisterNuclearCore.NUCLEAR_CORE_BLOCK).build()
        );
    }
}
