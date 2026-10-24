package com.netut.sudustrial.client.datagen.feature.tnt;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.List;

final class TntFamilies {
    private TntFamilies() {}

    record Family(String id, Item ingredient) {
        String base() {
            return id + "_tnt";
        }
    }

    static final List<Family> ALL = List.of(
            new Family("bee", Items.HONEYCOMB),
            new Family("blindness", Items.INK_SAC),
            new Family("chicken", Items.CHICKEN),
            new Family("cow", Items.BEEF),
            new Family("creeper", Items.GUNPOWDER),
            new Family("earth", Items.DIRT),
            new Family("electric", Items.REDSTONE),
            new Family("ender", Items.ENDER_PEARL),
            new Family("fire_resistance", Items.MAGMA_CREAM),
            new Family("fire", Items.FIRE_CHARGE),
            new Family("harm", Items.GLOWSTONE_DUST),
            new Family("healing", Items.GLISTERING_MELON_SLICE),
            new Family("ice", Items.ICE),
            new Family("invisibility", Items.GOLDEN_CARROT),
            new Family("jump_boost", Items.RABBIT_FOOT),
            new Family("levitation", Items.FEATHER),
            new Family("massive", Items.GOLD_BLOCK),
            new Family("mining_column", Items.GOLD_INGOT),
            new Family("mining_sphere", Items.IRON_INGOT),
            new Family("night_vision", Items.GOLDEN_APPLE),
            new Family("ocean", Items.PRISMARINE_SHARD),
            new Family("pig", Items.PORKCHOP),
            new Family("piglin_brute", Items.RAW_GOLD),
            new Family("poison", Items.SPIDER_EYE),
            new Family("regeneration", Items.GHAST_TEAR),
            new Family("sculk", Items.SCULK),
            new Family("sheep", Items.WOOL.white()),
            new Family("shulker", Items.SHULKER_SHELL),
            new Family("slow_falling", Items.PHANTOM_MEMBRANE),
            new Family("slowness", Items.STRING),
            new Family("speed", Items.SUGAR),
            new Family("strength", Items.BLAZE_POWDER),
            new Family("swamp", Items.SLIME_BALL),
            new Family("turtle_master", Items.TURTLE_HELMET),
            new Family("water_breathing", Items.PUFFERFISH),
            new Family("weakness", Items.BONE),
            new Family("wither", Items.WITHER_SKELETON_SKULL),
            new Family("wood", Items.OAK_LOG),
            new Family("zombie", Items.ROTTEN_FLESH)
    );
}
