package com.netut.sudustrial.register;

import com.netut.sudustrial.Sudustrial;
import com.netut.sudustrial.entity.cauldron.PotionCauldronBlockEntity;
import com.netut.sudustrial.entity.tnt.nuclear.NuclearCoreBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class ModBlockEntities {
    public static final ResourceKey<BlockEntityType<?>> NUCLEAR_CORE_KEY = ResourceKey.create(
            Registries.BLOCK_ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(Sudustrial.MOD_ID, "nuclear_core")
    );

    public static BlockEntityType<NuclearCoreBlockEntity> NUCLEAR_CORE;
    public static BlockEntityType<PotionCauldronBlockEntity> POTION_CAULDRON;

    // Вызывать ПОСЛЕ ModBlocks.registerModBlocks() — нужен уже зарегистрированный NUCLEAR_CORE_BLOCK
    public static void registerModBlockEntities() {
        NUCLEAR_CORE = Registry.register(
                BuiltInRegistries.BLOCK_ENTITY_TYPE,
                NUCLEAR_CORE_KEY,
                FabricBlockEntityTypeBuilder.create(NuclearCoreBlockEntity::new, ModBlocks.NUCLEAR_CORE_BLOCK).build()
        );
        POTION_CAULDRON = Registry.register(
                BuiltInRegistries.BLOCK_ENTITY_TYPE,
                ResourceKey.create(Registries.BLOCK_ENTITY_TYPE, Identifier.fromNamespaceAndPath(Sudustrial.MOD_ID, "potion_cauldron")),
                FabricBlockEntityTypeBuilder.create(PotionCauldronBlockEntity::new, ModBlocks.POTION_CAULDRON_BLOCK).build()
        );
    }
}
