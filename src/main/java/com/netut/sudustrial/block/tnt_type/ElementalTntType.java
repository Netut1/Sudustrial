package com.netut.sudustrial.block.tnt_type;

import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.jspecify.annotations.NonNull;

public enum ElementalTntType implements StringRepresentable {
    // Конструктор: (Имя ID, Уровень/Сила, Блок для твердых, Блок для жидкостей, Блок для воздуха)
    FIRE_TIER_1("fire_tnt_tier_1", 2.0f, Blocks.NETHERRACK, Blocks.LAVA, Blocks.FIRE),
    FIRE_TIER_2("fire_tnt_tier_2", 4.0f, Blocks.NETHERRACK, Blocks.LAVA, Blocks.FIRE),
    FIRE_TIER_3("fire_tnt_tier_3", 6.0f, Blocks.NETHERRACK, Blocks.LAVA, Blocks.FIRE),
    FIRE_TIER_4("fire_tnt_tier_4", 8.0f, Blocks.NETHERRACK, Blocks.LAVA, Blocks.FIRE),
    FIRE_TIER_5("fire_tnt_tier_5", 10.0f, Blocks.NETHERRACK, Blocks.LAVA, Blocks.FIRE),
    FIRE_TIER_6("fire_tnt_tier_6", 12.0f, Blocks.NETHERRACK, Blocks.LAVA, Blocks.FIRE),
    FIRE_TIER_7("fire_tnt_tier_7", 14.0f, Blocks.NETHERRACK, Blocks.LAVA, Blocks.FIRE),
    FIRE_TIER_8("fire_tnt_tier_8", 16.0f, Blocks.NETHERRACK, Blocks.LAVA, Blocks.FIRE),
    FIRE_TIER_9("fire_tnt_tier_9", 18.0f, Blocks.NETHERRACK, Blocks.LAVA, Blocks.FIRE),
    FIRE_TIER_10("fire_tnt_tier_10", 20.0f, Blocks.NETHERRACK, Blocks.LAVA, Blocks.FIRE),

    WOOD_TIER_1("wood_tnt_tier_1", 2.0f, Blocks.OAK_WOOD, Blocks.WATER, Blocks.OAK_LEAVES),
    WOOD_TIER_2("wood_tnt_tier_2", 4.0f, Blocks.OAK_WOOD, Blocks.WATER, Blocks.OAK_LEAVES),
    WOOD_TIER_3("wood_tnt_tier_3", 6.0f, Blocks.OAK_WOOD, Blocks.WATER, Blocks.OAK_LEAVES),
    WOOD_TIER_4("wood_tnt_tier_4", 8.0f, Blocks.OAK_WOOD, Blocks.WATER, Blocks.OAK_LEAVES),
    WOOD_TIER_5("wood_tnt_tier_5", 10.0f, Blocks.OAK_WOOD, Blocks.WATER, Blocks.OAK_LEAVES),
    WOOD_TIER_6("wood_tnt_tier_6", 12.0f, Blocks.OAK_WOOD, Blocks.WATER, Blocks.OAK_LEAVES),
    WOOD_TIER_7("wood_tnt_tier_7", 14.0f, Blocks.OAK_WOOD, Blocks.WATER, Blocks.OAK_LEAVES),
    WOOD_TIER_8("wood_tnt_tier_8", 16.0f, Blocks.OAK_WOOD, Blocks.WATER, Blocks.OAK_LEAVES),
    WOOD_TIER_9("wood_tnt_tier_9", 18.0f, Blocks.OAK_WOOD, Blocks.WATER, Blocks.OAK_LEAVES),
    WOOD_TIER_10("wood_tnt_tier_10", 20.0f, Blocks.OAK_WOOD, Blocks.WATER, Blocks.OAK_LEAVES),

    SWAMP_TIER_1("swamp_tnt_tier_1", 2.0f, Blocks.SLIME_BLOCK, Blocks.SUSPICIOUS_GRAVEL, Blocks.FROGSPAWN),
    SWAMP_TIER_2("swamp_tnt_tier_2", 4.0f, Blocks.SLIME_BLOCK, Blocks.SUSPICIOUS_GRAVEL, Blocks.FROGSPAWN),
    SWAMP_TIER_3("swamp_tnt_tier_3", 6.0f, Blocks.SLIME_BLOCK, Blocks.SUSPICIOUS_GRAVEL, Blocks.FROGSPAWN),
    SWAMP_TIER_4("swamp_tnt_tier_4", 8.0f, Blocks.SLIME_BLOCK, Blocks.SUSPICIOUS_GRAVEL, Blocks.FROGSPAWN),
    SWAMP_TIER_5("swamp_tnt_tier_5", 10.0f, Blocks.SLIME_BLOCK, Blocks.SUSPICIOUS_GRAVEL, Blocks.FROGSPAWN),
    SWAMP_TIER_6("swamp_tnt_tier_6", 12.0f, Blocks.SLIME_BLOCK, Blocks.SUSPICIOUS_GRAVEL, Blocks.FROGSPAWN),
    SWAMP_TIER_7("swamp_tnt_tier_7", 14.0f, Blocks.SLIME_BLOCK, Blocks.SUSPICIOUS_GRAVEL, Blocks.FROGSPAWN),
    SWAMP_TIER_8("swamp_tnt_tier_8", 16.0f, Blocks.SLIME_BLOCK, Blocks.SUSPICIOUS_GRAVEL, Blocks.FROGSPAWN),
    SWAMP_TIER_9("swamp_tnt_tier_9", 18.0f, Blocks.SLIME_BLOCK, Blocks.SUSPICIOUS_GRAVEL, Blocks.FROGSPAWN),
    SWAMP_TIER_10("swamp_tnt_tier_10", 20.0f, Blocks.SLIME_BLOCK, Blocks.SUSPICIOUS_GRAVEL, Blocks.FROGSPAWN),

    SCULK_TIER_1("sculk_tnt_tier_1", 2.0f, Blocks.SCULK, Blocks.COAL_BLOCK, Blocks.SCULK_VEIN),
    SCULK_TIER_2("sculk_tnt_tier_2", 4.0f, Blocks.SCULK, Blocks.COAL_BLOCK, Blocks.SCULK_VEIN),
    SCULK_TIER_3("sculk_tnt_tier_3", 6.0f, Blocks.SCULK, Blocks.COAL_BLOCK, Blocks.SCULK_VEIN),
    SCULK_TIER_4("sculk_tnt_tier_4", 8.0f, Blocks.SCULK, Blocks.COAL_BLOCK, Blocks.SCULK_VEIN),
    SCULK_TIER_5("sculk_tnt_tier_5", 10.0f, Blocks.SCULK, Blocks.COAL_BLOCK, Blocks.SCULK_VEIN),
    SCULK_TIER_6("sculk_tnt_tier_6", 12.0f, Blocks.SCULK, Blocks.COAL_BLOCK, Blocks.SCULK_VEIN),
    SCULK_TIER_7("sculk_tnt_tier_7", 14.0f, Blocks.SCULK, Blocks.COAL_BLOCK, Blocks.SCULK_VEIN),
    SCULK_TIER_8("sculk_tnt_tier_8", 16.0f, Blocks.SCULK, Blocks.COAL_BLOCK, Blocks.SCULK_VEIN),
    SCULK_TIER_9("sculk_tnt_tier_9", 18.0f, Blocks.SCULK, Blocks.COAL_BLOCK, Blocks.SCULK_VEIN),
    SCULK_TIER_10("sculk_tnt_tier_10", 20.0f, Blocks.SCULK, Blocks.COAL_BLOCK, Blocks.SCULK_VEIN),

    EARTH_TIER_1("earth_tnt_tier_1", 2.0f, Blocks.MOSS_BLOCK, Blocks.WATER, Blocks.SHORT_GRASS),
    EARTH_TIER_2("earth_tnt_tier_2", 4.0f, Blocks.MOSS_BLOCK, Blocks.WATER, Blocks.SHORT_GRASS),
    EARTH_TIER_3("earth_tnt_tier_3", 6.0f, Blocks.MOSS_BLOCK, Blocks.WATER, Blocks.SHORT_GRASS),
    EARTH_TIER_4("earth_tnt_tier_4", 8.0f, Blocks.MOSS_BLOCK, Blocks.WATER, Blocks.SHORT_GRASS),
    EARTH_TIER_5("earth_tnt_tier_5", 10.0f, Blocks.MOSS_BLOCK, Blocks.WATER, Blocks.SHORT_GRASS),
    EARTH_TIER_6("earth_tnt_tier_6", 12.0f, Blocks.MOSS_BLOCK, Blocks.WATER, Blocks.SHORT_GRASS),
    EARTH_TIER_7("earth_tnt_tier_7", 14.0f, Blocks.MOSS_BLOCK, Blocks.WATER, Blocks.SHORT_GRASS),
    EARTH_TIER_8("earth_tnt_tier_8", 16.0f, Blocks.MOSS_BLOCK, Blocks.WATER, Blocks.SHORT_GRASS),
    EARTH_TIER_9("earth_tnt_tier_9", 18.0f, Blocks.MOSS_BLOCK, Blocks.WATER, Blocks.SHORT_GRASS),
    EARTH_TIER_10("earth_tnt_tier_10", 20.0f, Blocks.MOSS_BLOCK, Blocks.WATER, Blocks.SHORT_GRASS),

    ELECTRIC_TIER_1("electric_tnt_tier_1", 2.0f, Blocks.REDSTONE_BLOCK, Blocks.WATER, Blocks.IRON_BARS),
    ELECTRIC_TIER_2("electric_tnt_tier_2", 4.0f, Blocks.REDSTONE_BLOCK, Blocks.WATER, Blocks.IRON_BARS),
    ELECTRIC_TIER_3("electric_tnt_tier_3", 6.0f, Blocks.REDSTONE_BLOCK, Blocks.WATER, Blocks.IRON_BARS),
    ELECTRIC_TIER_4("electric_tnt_tier_4", 8.0f, Blocks.REDSTONE_BLOCK, Blocks.WATER, Blocks.IRON_BARS),
    ELECTRIC_TIER_5("electric_tnt_tier_5", 10.0f, Blocks.REDSTONE_BLOCK, Blocks.WATER, Blocks.IRON_BARS),
    ELECTRIC_TIER_6("electric_tnt_tier_6", 12.0f, Blocks.REDSTONE_BLOCK, Blocks.WATER, Blocks.IRON_BARS),
    ELECTRIC_TIER_7("electric_tnt_tier_7", 14.0f, Blocks.REDSTONE_BLOCK, Blocks.WATER, Blocks.IRON_BARS),
    ELECTRIC_TIER_8("electric_tnt_tier_8", 16.0f, Blocks.REDSTONE_BLOCK, Blocks.WATER, Blocks.IRON_BARS),
    ELECTRIC_TIER_9("electric_tnt_tier_9", 18.0f, Blocks.REDSTONE_BLOCK, Blocks.WATER, Blocks.IRON_BARS),
    ELECTRIC_TIER_10("electric_tnt_tier_10", 20.0f, Blocks.REDSTONE_BLOCK, Blocks.WATER, Blocks.IRON_BARS),

    ENDER_TIER_1("ender_tnt_tier_1", 2.0f, Blocks.END_STONE, Blocks.PURPUR_BLOCK, Blocks.CHORUS_FLOWER),
    ENDER_TIER_2("ender_tnt_tier_2", 4.0f, Blocks.END_STONE, Blocks.PURPUR_BLOCK, Blocks.CHORUS_FLOWER),
    ENDER_TIER_3("ender_tnt_tier_3", 6.0f, Blocks.END_STONE, Blocks.PURPUR_BLOCK, Blocks.CHORUS_FLOWER),
    ENDER_TIER_4("ender_tnt_tier_4", 8.0f, Blocks.END_STONE, Blocks.PURPUR_BLOCK, Blocks.CHORUS_FLOWER),
    ENDER_TIER_5("ender_tnt_tier_5", 10.0f, Blocks.END_STONE, Blocks.PURPUR_BLOCK, Blocks.CHORUS_FLOWER),
    ENDER_TIER_6("ender_tnt_tier_6", 12.0f, Blocks.END_STONE, Blocks.PURPUR_BLOCK, Blocks.CHORUS_FLOWER),
    ENDER_TIER_7("ender_tnt_tier_7", 14.0f, Blocks.END_STONE, Blocks.PURPUR_BLOCK, Blocks.CHORUS_FLOWER),
    ENDER_TIER_8("ender_tnt_tier_8", 16.0f, Blocks.END_STONE, Blocks.PURPUR_BLOCK, Blocks.CHORUS_FLOWER),
    ENDER_TIER_9("ender_tnt_tier_9", 18.0f, Blocks.END_STONE, Blocks.PURPUR_BLOCK, Blocks.CHORUS_FLOWER),
    ENDER_TIER_10("ender_tnt_tier_10", 20.0f, Blocks.END_STONE, Blocks.PURPUR_BLOCK, Blocks.CHORUS_FLOWER),

    OCEAN_TIER_1("ocean_tnt_tier_1", 2.0f, Blocks.PRISMARINE, Blocks.WATER, Blocks.SEA_PICKLE),
    OCEAN_TIER_2("ocean_tnt_tier_2", 4.0f, Blocks.PRISMARINE, Blocks.WATER, Blocks.SEA_PICKLE),
    OCEAN_TIER_3("ocean_tnt_tier_3", 6.0f, Blocks.PRISMARINE, Blocks.WATER, Blocks.SEA_PICKLE),
    OCEAN_TIER_4("ocean_tnt_tier_4", 8.0f, Blocks.PRISMARINE, Blocks.WATER, Blocks.SEA_PICKLE),
    OCEAN_TIER_5("ocean_tnt_tier_5", 10.0f, Blocks.PRISMARINE, Blocks.WATER, Blocks.SEA_PICKLE),
    OCEAN_TIER_6("ocean_tnt_tier_6", 12.0f, Blocks.PRISMARINE, Blocks.WATER, Blocks.SEA_PICKLE),
    OCEAN_TIER_7("ocean_tnt_tier_7", 14.0f, Blocks.PRISMARINE, Blocks.WATER, Blocks.SEA_PICKLE),
    OCEAN_TIER_8("ocean_tnt_tier_8", 16.0f, Blocks.PRISMARINE, Blocks.WATER, Blocks.SEA_PICKLE),
    OCEAN_TIER_9("ocean_tnt_tier_9", 18.0f, Blocks.PRISMARINE, Blocks.WATER, Blocks.SEA_PICKLE),
    OCEAN_TIER_10("ocean_tnt_tier_10", 20.0f, Blocks.PRISMARINE, Blocks.WATER, Blocks.SEA_PICKLE),

    ICE_TIER_1("ice_tnt_tier_1", 2.0f, Blocks.PACKED_ICE, Blocks.BLUE_ICE, Blocks.POWDER_SNOW),
    ICE_TIER_2("ice_tnt_tier_2", 4.0f, Blocks.PACKED_ICE, Blocks.BLUE_ICE, Blocks.POWDER_SNOW),
    ICE_TIER_3("ice_tnt_tier_3", 6.0f, Blocks.PACKED_ICE, Blocks.BLUE_ICE, Blocks.POWDER_SNOW),
    ICE_TIER_4("ice_tnt_tier_4", 8.0f, Blocks.PACKED_ICE, Blocks.BLUE_ICE, Blocks.POWDER_SNOW),
    ICE_TIER_5("ice_tnt_tier_5", 10.0f, Blocks.PACKED_ICE, Blocks.BLUE_ICE, Blocks.POWDER_SNOW),
    ICE_TIER_6("ice_tnt_tier_6", 12.0f, Blocks.PACKED_ICE, Blocks.BLUE_ICE, Blocks.POWDER_SNOW),
    ICE_TIER_7("ice_tnt_tier_7", 14.0f, Blocks.PACKED_ICE, Blocks.BLUE_ICE, Blocks.POWDER_SNOW),
    ICE_TIER_8("ice_tnt_tier_8", 16.0f, Blocks.PACKED_ICE, Blocks.BLUE_ICE, Blocks.POWDER_SNOW),
    ICE_TIER_9("ice_tnt_tier_9", 18.0f, Blocks.PACKED_ICE, Blocks.BLUE_ICE, Blocks.POWDER_SNOW),
    ICE_TIER_10("ice_tnt_tier_10", 20.0f, Blocks.PACKED_ICE, Blocks.BLUE_ICE, Blocks.POWDER_SNOW);

    private final String name;
    private final float explosionPower;
    private final Block solidReplacement;
    private final Block liquidReplacement;
    private final Block airReplacement;

    ElementalTntType(String name, float explosionPower, Block solidReplacement, Block liquidReplacement, Block airReplacement) {
        this.name = name;
        this.explosionPower = explosionPower;
        this.solidReplacement = solidReplacement;
        this.liquidReplacement = liquidReplacement;
        this.airReplacement = airReplacement;
    }

    public float getExplosionPower() {
        return explosionPower;
    }

    public Block getSolidReplacement() {
        return solidReplacement;
    }

    public Block getLiquidReplacement() {
        return liquidReplacement;
    }

    public Block getAirReplacement() {
        return airReplacement;
    }

    @Override
    @NonNull
    public String getSerializedName() {
        return this.name;
    }
}
