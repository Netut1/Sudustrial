package com.netut.sudustrial.register.block.nuclear_block;

import com.netut.sudustrial.Sudustrial;
import com.netut.sudustrial.tnt.block.NuclearAirBlock;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class RegisterNuclearAir {
    public static Block NUCLEAR_AIR;

    public static void register(){
        Identifier nuclearAirLoc = Identifier.fromNamespaceAndPath(Sudustrial.MOD_ID, "nuclear_air");
        ResourceKey<Block> nuclearAirKey = ResourceKey.create(Registries.BLOCK, nuclearAirLoc);
        BlockBehaviour.Properties nuclearAirProperties = BlockBehaviour.Properties.of()
                .noCollision()
                .replaceable()
                .noOcclusion()
                .lightLevel(state -> 3)
                .noLootTable()
                .setId(nuclearAirKey);
        NUCLEAR_AIR = new NuclearAirBlock(nuclearAirProperties);
        Registry.register(BuiltInRegistries.BLOCK, nuclearAirKey, NUCLEAR_AIR);
    }
}
