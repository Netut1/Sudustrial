package com.netut.sudustrial.register.entity.block.potion_cauldron;

import com.netut.sudustrial.Sudustrial;
import com.netut.sudustrial.potion_cauldron.entity.PotionCauldronBlockEntity;
import com.netut.sudustrial.register.block.potion_cauldron.RegisterPotionCauldron;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class RegisterPotionCauldronEntity {
    public static BlockEntityType<PotionCauldronBlockEntity> POTION_CAULDRON;

    public static void register() {
        POTION_CAULDRON = Registry.register(
                BuiltInRegistries.BLOCK_ENTITY_TYPE,
                ResourceKey.create(Registries.BLOCK_ENTITY_TYPE, Identifier.fromNamespaceAndPath(Sudustrial.MOD_ID, "potion_cauldron")),
                FabricBlockEntityTypeBuilder.create(PotionCauldronBlockEntity::new, RegisterPotionCauldron.POTION_CAULDRON_BLOCK).build()
        );
    }
}
