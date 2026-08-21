package com.netut.sudustrial.block.tnt.type;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.EntityType;
import org.jspecify.annotations.NonNull;

public enum SpawnerTntType implements StringRepresentable {
    ZOMBIE_TIER_1("zombie_tnt_tier_1", 3.0f, BuiltInRegistries.ENTITY_TYPE.get(Identifier.withDefaultNamespace("zombie")).orElseThrow().value(), 3.0D),
    ZOMBIE_TIER_2("zombie_tnt_tier_2", 6.0f, BuiltInRegistries.ENTITY_TYPE.get(Identifier.withDefaultNamespace("zombie")).orElseThrow().value(), 3.0D),
    ZOMBIE_TIER_3("zombie_tnt_tier_3", 10.0f, BuiltInRegistries.ENTITY_TYPE.get(Identifier.withDefaultNamespace("zombie")).orElseThrow().value(), 3.0D),
    ZOMBIE_TIER_4("zombie_tnt_tier_4", 20.0f, BuiltInRegistries.ENTITY_TYPE.get(Identifier.withDefaultNamespace("zombie")).orElseThrow().value(), 5.0D),
    ZOMBIE_TIER_5("zombie_tnt_tier_5", 25.0f, BuiltInRegistries.ENTITY_TYPE.get(Identifier.withDefaultNamespace("zombie")).orElseThrow().value(), 5.0D),
    ZOMBIE_TIER_6("zombie_tnt_tier_6", 30.0f, BuiltInRegistries.ENTITY_TYPE.get(Identifier.withDefaultNamespace("zombie")).orElseThrow().value(), 5.0D),
    ZOMBIE_TIER_7("zombie_tnt_tier_7", 50.0f, BuiltInRegistries.ENTITY_TYPE.get(Identifier.withDefaultNamespace("zombie")).orElseThrow().value(), 10.0D),
    ZOMBIE_TIER_8("zombie_tnt_tier_8", 70.0f, BuiltInRegistries.ENTITY_TYPE.get(Identifier.withDefaultNamespace("zombie")).orElseThrow().value(), 10.0D),
    ZOMBIE_TIER_9("zombie_tnt_tier_9", 90.0f, BuiltInRegistries.ENTITY_TYPE.get(Identifier.withDefaultNamespace("zombie")).orElseThrow().value(), 10.0D),
    ZOMBIE_TIER_10("zombie_tnt_tier_10", 150.0f, BuiltInRegistries.ENTITY_TYPE.get(Identifier.withDefaultNamespace("zombie")).orElseThrow().value(), 20.0D),

    CREEPER_TIER_1("creeper_tnt_tier_1", 3.0f, BuiltInRegistries.ENTITY_TYPE.get(Identifier.withDefaultNamespace("creeper")).orElseThrow().value(), 3.0D),
    CREEPER_TIER_2("creeper_tnt_tier_2", 6.0f, BuiltInRegistries.ENTITY_TYPE.get(Identifier.withDefaultNamespace("creeper")).orElseThrow().value(), 3.0D),
    CREEPER_TIER_3("creeper_tnt_tier_3", 10.0f, BuiltInRegistries.ENTITY_TYPE.get(Identifier.withDefaultNamespace("creeper")).orElseThrow().value(), 3.0D),
    CREEPER_TIER_4("creeper_tnt_tier_4", 20.0f, BuiltInRegistries.ENTITY_TYPE.get(Identifier.withDefaultNamespace("creeper")).orElseThrow().value(), 5.0D),
    CREEPER_TIER_5("creeper_tnt_tier_5", 25.0f, BuiltInRegistries.ENTITY_TYPE.get(Identifier.withDefaultNamespace("creeper")).orElseThrow().value(), 5.0D),
    CREEPER_TIER_6("creeper_tnt_tier_6", 30.0f, BuiltInRegistries.ENTITY_TYPE.get(Identifier.withDefaultNamespace("creeper")).orElseThrow().value(), 5.0D),
    CREEPER_TIER_7("creeper_tnt_tier_7", 50.0f, BuiltInRegistries.ENTITY_TYPE.get(Identifier.withDefaultNamespace("creeper")).orElseThrow().value(), 10.0D),
    CREEPER_TIER_8("creeper_tnt_tier_8", 70.0f, BuiltInRegistries.ENTITY_TYPE.get(Identifier.withDefaultNamespace("creeper")).orElseThrow().value(), 10.0D),
    CREEPER_TIER_9("creeper_tnt_tier_9", 90.0f, BuiltInRegistries.ENTITY_TYPE.get(Identifier.withDefaultNamespace("creeper")).orElseThrow().value(), 10.0D),
    CREEPER_TIER_10("creeper_tnt_tier_10", 150.0f, BuiltInRegistries.ENTITY_TYPE.get(Identifier.withDefaultNamespace("creeper")).orElseThrow().value(), 20.0D),

    SHEEP_TIER_1("sheep_tnt_tier_1", 3.0f, BuiltInRegistries.ENTITY_TYPE.get(Identifier.withDefaultNamespace("sheep")).orElseThrow().value(), 3.0D),
    SHEEP_TIER_2("sheep_tnt_tier_2", 6.0f, BuiltInRegistries.ENTITY_TYPE.get(Identifier.withDefaultNamespace("sheep")).orElseThrow().value(), 3.0D),
    SHEEP_TIER_3("sheep_tnt_tier_3", 10.0f, BuiltInRegistries.ENTITY_TYPE.get(Identifier.withDefaultNamespace("sheep")).orElseThrow().value(), 3.0D),
    SHEEP_TIER_4("sheep_tnt_tier_4", 20.0f, BuiltInRegistries.ENTITY_TYPE.get(Identifier.withDefaultNamespace("sheep")).orElseThrow().value(), 5.0D),
    SHEEP_TIER_5("sheep_tnt_tier_5", 25.0f, BuiltInRegistries.ENTITY_TYPE.get(Identifier.withDefaultNamespace("sheep")).orElseThrow().value(), 5.0D),
    SHEEP_TIER_6("sheep_tnt_tier_6", 30.0f, BuiltInRegistries.ENTITY_TYPE.get(Identifier.withDefaultNamespace("sheep")).orElseThrow().value(), 5.0D),
    SHEEP_TIER_7("sheep_tnt_tier_7", 50.0f, BuiltInRegistries.ENTITY_TYPE.get(Identifier.withDefaultNamespace("sheep")).orElseThrow().value(), 10.0D),
    SHEEP_TIER_8("sheep_tnt_tier_8", 70.0f, BuiltInRegistries.ENTITY_TYPE.get(Identifier.withDefaultNamespace("sheep")).orElseThrow().value(), 10.0D),
    SHEEP_TIER_9("sheep_tnt_tier_9", 90.0f, BuiltInRegistries.ENTITY_TYPE.get(Identifier.withDefaultNamespace("sheep")).orElseThrow().value(), 10.0D),
    SHEEP_TIER_10("sheep_tnt_tier_10", 150.0f, BuiltInRegistries.ENTITY_TYPE.get(Identifier.withDefaultNamespace("sheep")).orElseThrow().value(), 20.0D),

    SHULKER_TIER_1("shulker_tnt_tier_1", 3.0f, BuiltInRegistries.ENTITY_TYPE.get(Identifier.withDefaultNamespace("shulker")).orElseThrow().value(), 3.0D),
    SHULKER_TIER_2("shulker_tnt_tier_2", 6.0f, BuiltInRegistries.ENTITY_TYPE.get(Identifier.withDefaultNamespace("shulker")).orElseThrow().value(), 3.0D),
    SHULKER_TIER_3("shulker_tnt_tier_3", 10.0f, BuiltInRegistries.ENTITY_TYPE.get(Identifier.withDefaultNamespace("shulker")).orElseThrow().value(), 3.0D),
    SHULKER_TIER_4("shulker_tnt_tier_4", 20.0f, BuiltInRegistries.ENTITY_TYPE.get(Identifier.withDefaultNamespace("shulker")).orElseThrow().value(), 5.0D),
    SHULKER_TIER_5("shulker_tnt_tier_5", 25.0f, BuiltInRegistries.ENTITY_TYPE.get(Identifier.withDefaultNamespace("shulker")).orElseThrow().value(), 5.0D),
    SHULKER_TIER_6("shulker_tnt_tier_6", 30.0f, BuiltInRegistries.ENTITY_TYPE.get(Identifier.withDefaultNamespace("shulker")).orElseThrow().value(), 5.0D),
    SHULKER_TIER_7("shulker_tnt_tier_7", 50.0f, BuiltInRegistries.ENTITY_TYPE.get(Identifier.withDefaultNamespace("shulker")).orElseThrow().value(), 10.0D),
    SHULKER_TIER_8("shulker_tnt_tier_8", 70.0f, BuiltInRegistries.ENTITY_TYPE.get(Identifier.withDefaultNamespace("shulker")).orElseThrow().value(), 10.0D),
    SHULKER_TIER_9("shulker_tnt_tier_9", 90.0f, BuiltInRegistries.ENTITY_TYPE.get(Identifier.withDefaultNamespace("shulker")).orElseThrow().value(), 10.0D),
    SHULKER_TIER_10("shulker_tnt_tier_10", 150.0f, BuiltInRegistries.ENTITY_TYPE.get(Identifier.withDefaultNamespace("shulker")).orElseThrow().value(), 20.0D),

    PIGLIN_BRUTE_TIER_1("piglin_brute_tnt_tier_1", 3.0f, BuiltInRegistries.ENTITY_TYPE.get(Identifier.withDefaultNamespace("piglin_brute")).orElseThrow().value(), 3.0D),
    PIGLIN_BRUTE_TIER_2("piglin_brute_tnt_tier_2", 6.0f, BuiltInRegistries.ENTITY_TYPE.get(Identifier.withDefaultNamespace("piglin_brute")).orElseThrow().value(), 3.0D),
    PIGLIN_BRUTE_TIER_3("piglin_brute_tnt_tier_3", 10.0f, BuiltInRegistries.ENTITY_TYPE.get(Identifier.withDefaultNamespace("piglin_brute")).orElseThrow().value(), 3.0D),
    PIGLIN_BRUTE_TIER_4("piglin_brute_tnt_tier_4", 20.0f, BuiltInRegistries.ENTITY_TYPE.get(Identifier.withDefaultNamespace("piglin_brute")).orElseThrow().value(), 5.0D),
    PIGLIN_BRUTE_TIER_5("piglin_brute_tnt_tier_5", 25.0f, BuiltInRegistries.ENTITY_TYPE.get(Identifier.withDefaultNamespace("piglin_brute")).orElseThrow().value(), 5.0D),
    PIGLIN_BRUTE_TIER_6("piglin_brute_tnt_tier_6", 30.0f, BuiltInRegistries.ENTITY_TYPE.get(Identifier.withDefaultNamespace("piglin_brute")).orElseThrow().value(), 5.0D),
    PIGLIN_BRUTE_TIER_7("piglin_brute_tnt_tier_7", 50.0f, BuiltInRegistries.ENTITY_TYPE.get(Identifier.withDefaultNamespace("piglin_brute")).orElseThrow().value(), 10.0D),
    PIGLIN_BRUTE_TIER_8("piglin_brute_tnt_tier_8", 70.0f, BuiltInRegistries.ENTITY_TYPE.get(Identifier.withDefaultNamespace("piglin_brute")).orElseThrow().value(), 10.0D),
    PIGLIN_BRUTE_TIER_9("piglin_brute_tnt_tier_9", 90.0f, BuiltInRegistries.ENTITY_TYPE.get(Identifier.withDefaultNamespace("piglin_brute")).orElseThrow().value(), 10.0D),
    PIGLIN_BRUTE_TIER_10("piglin_brute_tnt_tier_10", 150.0f, BuiltInRegistries.ENTITY_TYPE.get(Identifier.withDefaultNamespace("piglin_brute")).orElseThrow().value(), 20.0D),

    PIG_TIER_1("pig_tnt_tier_1", 3.0f, BuiltInRegistries.ENTITY_TYPE.get(Identifier.withDefaultNamespace("pig")).orElseThrow().value(), 3.0D),
    PIG_TIER_2("pig_tnt_tier_2", 6.0f, BuiltInRegistries.ENTITY_TYPE.get(Identifier.withDefaultNamespace("pig")).orElseThrow().value(), 3.0D),
    PIG_TIER_3("pig_tnt_tier_3", 10.0f, BuiltInRegistries.ENTITY_TYPE.get(Identifier.withDefaultNamespace("pig")).orElseThrow().value(), 3.0D),
    PIG_TIER_4("pig_tnt_tier_4", 20.0f, BuiltInRegistries.ENTITY_TYPE.get(Identifier.withDefaultNamespace("pig")).orElseThrow().value(), 5.0D),
    PIG_TIER_5("pig_tnt_tier_5", 25.0f, BuiltInRegistries.ENTITY_TYPE.get(Identifier.withDefaultNamespace("pig")).orElseThrow().value(), 5.0D),
    PIG_TIER_6("pig_tnt_tier_6", 30.0f, BuiltInRegistries.ENTITY_TYPE.get(Identifier.withDefaultNamespace("pig")).orElseThrow().value(), 5.0D),
    PIG_TIER_7("pig_tnt_tier_7", 50.0f, BuiltInRegistries.ENTITY_TYPE.get(Identifier.withDefaultNamespace("pig")).orElseThrow().value(), 10.0D),
    PIG_TIER_8("pig_tnt_tier_8", 70.0f, BuiltInRegistries.ENTITY_TYPE.get(Identifier.withDefaultNamespace("pig")).orElseThrow().value(), 10.0D),
    PIG_TIER_9("pig_tnt_tier_9", 90.0f, BuiltInRegistries.ENTITY_TYPE.get(Identifier.withDefaultNamespace("pig")).orElseThrow().value(), 10.0D),
    PIG_TIER_10("pig_tnt_tier_10", 150.0f, BuiltInRegistries.ENTITY_TYPE.get(Identifier.withDefaultNamespace("pig")).orElseThrow().value(), 20.0D),

    COW_TIER_1("cow_tnt_tier_1", 3.0f, BuiltInRegistries.ENTITY_TYPE.get(Identifier.withDefaultNamespace("cow")).orElseThrow().value(), 3.0D),
    COW_TIER_2("cow_tnt_tier_2", 6.0f, BuiltInRegistries.ENTITY_TYPE.get(Identifier.withDefaultNamespace("cow")).orElseThrow().value(), 3.0D),
    COW_TIER_3("cow_tnt_tier_3", 10.0f, BuiltInRegistries.ENTITY_TYPE.get(Identifier.withDefaultNamespace("cow")).orElseThrow().value(), 3.0D),
    COW_TIER_4("cow_tnt_tier_4", 20.0f, BuiltInRegistries.ENTITY_TYPE.get(Identifier.withDefaultNamespace("cow")).orElseThrow().value(), 5.0D),
    COW_TIER_5("cow_tnt_tier_5", 25.0f, BuiltInRegistries.ENTITY_TYPE.get(Identifier.withDefaultNamespace("cow")).orElseThrow().value(), 5.0D),
    COW_TIER_6("cow_tnt_tier_6", 30.0f, BuiltInRegistries.ENTITY_TYPE.get(Identifier.withDefaultNamespace("cow")).orElseThrow().value(), 5.0D),
    COW_TIER_7("cow_tnt_tier_7", 50.0f, BuiltInRegistries.ENTITY_TYPE.get(Identifier.withDefaultNamespace("cow")).orElseThrow().value(), 10.0D),
    COW_TIER_8("cow_tnt_tier_8", 70.0f, BuiltInRegistries.ENTITY_TYPE.get(Identifier.withDefaultNamespace("cow")).orElseThrow().value(), 10.0D),
    COW_TIER_9("cow_tnt_tier_9", 90.0f, BuiltInRegistries.ENTITY_TYPE.get(Identifier.withDefaultNamespace("cow")).orElseThrow().value(), 10.0D),
    COW_TIER_10("cow_tnt_tier_10", 150.0f, BuiltInRegistries.ENTITY_TYPE.get(Identifier.withDefaultNamespace("cow")).orElseThrow().value(), 20.0D),

    CHICKEN_TIER_1("chicken_tnt_tier_1", 3.0f, BuiltInRegistries.ENTITY_TYPE.get(Identifier.withDefaultNamespace("chicken")).orElseThrow().value(), 3.0D),
    CHICKEN_TIER_2("chicken_tnt_tier_2", 6.0f, BuiltInRegistries.ENTITY_TYPE.get(Identifier.withDefaultNamespace("chicken")).orElseThrow().value(), 3.0D),
    CHICKEN_TIER_3("chicken_tnt_tier_3", 10.0f, BuiltInRegistries.ENTITY_TYPE.get(Identifier.withDefaultNamespace("chicken")).orElseThrow().value(), 3.0D),
    CHICKEN_TIER_4("chicken_tnt_tier_4", 20.0f, BuiltInRegistries.ENTITY_TYPE.get(Identifier.withDefaultNamespace("chicken")).orElseThrow().value(), 5.0D),
    CHICKEN_TIER_5("chicken_tnt_tier_5", 25.0f, BuiltInRegistries.ENTITY_TYPE.get(Identifier.withDefaultNamespace("chicken")).orElseThrow().value(), 5.0D),
    CHICKEN_TIER_6("chicken_tnt_tier_6", 30.0f, BuiltInRegistries.ENTITY_TYPE.get(Identifier.withDefaultNamespace("chicken")).orElseThrow().value(), 5.0D),
    CHICKEN_TIER_7("chicken_tnt_tier_7", 50.0f, BuiltInRegistries.ENTITY_TYPE.get(Identifier.withDefaultNamespace("chicken")).orElseThrow().value(), 10.0D),
    CHICKEN_TIER_8("chicken_tnt_tier_8", 70.0f, BuiltInRegistries.ENTITY_TYPE.get(Identifier.withDefaultNamespace("chicken")).orElseThrow().value(), 10.0D),
    CHICKEN_TIER_9("chicken_tnt_tier_9", 90.0f, BuiltInRegistries.ENTITY_TYPE.get(Identifier.withDefaultNamespace("chicken")).orElseThrow().value(), 10.0D),
    CHICKEN_TIER_10("chicken_tnt_tier_10", 150.0f, BuiltInRegistries.ENTITY_TYPE.get(Identifier.withDefaultNamespace("chicken")).orElseThrow().value(), 20.0D),

    BEE_TIER_1("bee_tnt_tier_1", 3.0f, BuiltInRegistries.ENTITY_TYPE.get(Identifier.withDefaultNamespace("bee")).orElseThrow().value(), 3.0D),
    BEE_TIER_2("bee_tnt_tier_2", 6.0f, BuiltInRegistries.ENTITY_TYPE.get(Identifier.withDefaultNamespace("bee")).orElseThrow().value(), 3.0D),
    BEE_TIER_3("bee_tnt_tier_3", 10.0f, BuiltInRegistries.ENTITY_TYPE.get(Identifier.withDefaultNamespace("bee")).orElseThrow().value(), 3.0D),
    BEE_TIER_4("bee_tnt_tier_4", 20.0f, BuiltInRegistries.ENTITY_TYPE.get(Identifier.withDefaultNamespace("bee")).orElseThrow().value(), 5.0D),
    BEE_TIER_5("bee_tnt_tier_5", 25.0f, BuiltInRegistries.ENTITY_TYPE.get(Identifier.withDefaultNamespace("bee")).orElseThrow().value(), 5.0D),
    BEE_TIER_6("bee_tnt_tier_6", 30.0f, BuiltInRegistries.ENTITY_TYPE.get(Identifier.withDefaultNamespace("bee")).orElseThrow().value(), 5.0D),
    BEE_TIER_7("bee_tnt_tier_7", 50.0f, BuiltInRegistries.ENTITY_TYPE.get(Identifier.withDefaultNamespace("bee")).orElseThrow().value(), 10.0D),
    BEE_TIER_8("bee_tnt_tier_8", 70.0f, BuiltInRegistries.ENTITY_TYPE.get(Identifier.withDefaultNamespace("bee")).orElseThrow().value(), 10.0D),
    BEE_TIER_9("bee_tnt_tier_9", 90.0f, BuiltInRegistries.ENTITY_TYPE.get(Identifier.withDefaultNamespace("bee")).orElseThrow().value(), 10.0D),
    BEE_TIER_10("bee_tnt_tier_10", 150.0f, BuiltInRegistries.ENTITY_TYPE.get(Identifier.withDefaultNamespace("bee")).orElseThrow().value(), 20.0D);

    private final String name;
    private final float entityCount; // Количество сущностей
    private final EntityType<?> entityType;
    private final double spawnRadius;

    SpawnerTntType(String name, float entityCount, EntityType<?> entityType, double spawnRadius) {
        this.name = name;
        this.entityCount = entityCount;
        this.entityType = entityType;
        this.spawnRadius = spawnRadius;
    }

    public float getEntityCount() {
        return entityCount;
    }

    public EntityType<?> getEntityType() {
        return entityType;
    }

    public double getSpawnRadius() {
        return spawnRadius;
    }

    @Override
    @NonNull
    public String getSerializedName() {
        return this.name;
    }
}
