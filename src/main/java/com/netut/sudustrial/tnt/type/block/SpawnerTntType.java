package com.netut.sudustrial.tnt.type.block;

import com.netut.sudustrial.tnt.action.explosion.Explodes;
import com.netut.sudustrial.tnt.block.TntVariant;
import com.netut.sudustrial.tnt.entity.CustomTntEntity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EntityType;
import org.jspecify.annotations.NonNull;

public enum SpawnerTntType implements TntVariant {
    ZOMBIE_TIER_1("zombie_tnt_tier_1", 1, "zombie"),
    ZOMBIE_TIER_2("zombie_tnt_tier_2", 2, "zombie"),
    ZOMBIE_TIER_3("zombie_tnt_tier_3", 3, "zombie"),
    ZOMBIE_TIER_4("zombie_tnt_tier_4", 4, "zombie"),
    ZOMBIE_TIER_5("zombie_tnt_tier_5", 5, "zombie"),
    ZOMBIE_TIER_6("zombie_tnt_tier_6", 6, "zombie"),
    ZOMBIE_TIER_7("zombie_tnt_tier_7", 7, "zombie"),
    ZOMBIE_TIER_8("zombie_tnt_tier_8", 8, "zombie"),
    ZOMBIE_TIER_9("zombie_tnt_tier_9", 9, "zombie"),
    ZOMBIE_TIER_10("zombie_tnt_tier_10", 10, "zombie"),

    CREEPER_TIER_1("creeper_tnt_tier_1", 1, "creeper"),
    CREEPER_TIER_2("creeper_tnt_tier_2", 2, "creeper"),
    CREEPER_TIER_3("creeper_tnt_tier_3", 3, "creeper"),
    CREEPER_TIER_4("creeper_tnt_tier_4", 4, "creeper"),
    CREEPER_TIER_5("creeper_tnt_tier_5", 5, "creeper"),
    CREEPER_TIER_6("creeper_tnt_tier_6", 6, "creeper"),
    CREEPER_TIER_7("creeper_tnt_tier_7", 7, "creeper"),
    CREEPER_TIER_8("creeper_tnt_tier_8", 8, "creeper"),
    CREEPER_TIER_9("creeper_tnt_tier_9", 9, "creeper"),
    CREEPER_TIER_10("creeper_tnt_tier_10", 10, "creeper"),

    SHEEP_TIER_1("sheep_tnt_tier_1", 1, "sheep"),
    SHEEP_TIER_2("sheep_tnt_tier_2", 2, "sheep"),
    SHEEP_TIER_3("sheep_tnt_tier_3", 3, "sheep"),
    SHEEP_TIER_4("sheep_tnt_tier_4", 4, "sheep"),
    SHEEP_TIER_5("sheep_tnt_tier_5", 5, "sheep"),
    SHEEP_TIER_6("sheep_tnt_tier_6", 6, "sheep"),
    SHEEP_TIER_7("sheep_tnt_tier_7", 7, "sheep"),
    SHEEP_TIER_8("sheep_tnt_tier_8", 8, "sheep"),
    SHEEP_TIER_9("sheep_tnt_tier_9", 9, "sheep"),
    SHEEP_TIER_10("sheep_tnt_tier_10", 10, "sheep"),

    SHULKER_TIER_1("shulker_tnt_tier_1", 1, "shulker"),
    SHULKER_TIER_2("shulker_tnt_tier_2", 2, "shulker"),
    SHULKER_TIER_3("shulker_tnt_tier_3", 3, "shulker"),
    SHULKER_TIER_4("shulker_tnt_tier_4", 4, "shulker"),
    SHULKER_TIER_5("shulker_tnt_tier_5", 5, "shulker"),
    SHULKER_TIER_6("shulker_tnt_tier_6", 6, "shulker"),
    SHULKER_TIER_7("shulker_tnt_tier_7", 7, "shulker"),
    SHULKER_TIER_8("shulker_tnt_tier_8", 8, "shulker"),
    SHULKER_TIER_9("shulker_tnt_tier_9", 9, "shulker"),
    SHULKER_TIER_10("shulker_tnt_tier_10", 10, "shulker"),

    PIGLIN_BRUTE_TIER_1("piglin_brute_tnt_tier_1", 1, "piglin_brute"),
    PIGLIN_BRUTE_TIER_2("piglin_brute_tnt_tier_2", 2, "piglin_brute"),
    PIGLIN_BRUTE_TIER_3("piglin_brute_tnt_tier_3", 3, "piglin_brute"),
    PIGLIN_BRUTE_TIER_4("piglin_brute_tnt_tier_4", 4, "piglin_brute"),
    PIGLIN_BRUTE_TIER_5("piglin_brute_tnt_tier_5", 5, "piglin_brute"),
    PIGLIN_BRUTE_TIER_6("piglin_brute_tnt_tier_6", 6, "piglin_brute"),
    PIGLIN_BRUTE_TIER_7("piglin_brute_tnt_tier_7", 7, "piglin_brute"),
    PIGLIN_BRUTE_TIER_8("piglin_brute_tnt_tier_8", 8, "piglin_brute"),
    PIGLIN_BRUTE_TIER_9("piglin_brute_tnt_tier_9", 9, "piglin_brute"),
    PIGLIN_BRUTE_TIER_10("piglin_brute_tnt_tier_10", 10, "piglin_brute"),

    PIG_TIER_1("pig_tnt_tier_1", 1, "pig"),
    PIG_TIER_2("pig_tnt_tier_2", 2, "pig"),
    PIG_TIER_3("pig_tnt_tier_3", 3, "pig"),
    PIG_TIER_4("pig_tnt_tier_4", 4, "pig"),
    PIG_TIER_5("pig_tnt_tier_5", 5, "pig"),
    PIG_TIER_6("pig_tnt_tier_6", 6, "pig"),
    PIG_TIER_7("pig_tnt_tier_7", 7, "pig"),
    PIG_TIER_8("pig_tnt_tier_8", 8, "pig"),
    PIG_TIER_9("pig_tnt_tier_9", 9, "pig"),
    PIG_TIER_10("pig_tnt_tier_10", 10, "pig"),

    COW_TIER_1("cow_tnt_tier_1", 1, "cow"),
    COW_TIER_2("cow_tnt_tier_2", 2, "cow"),
    COW_TIER_3("cow_tnt_tier_3", 3, "cow"),
    COW_TIER_4("cow_tnt_tier_4", 4, "cow"),
    COW_TIER_5("cow_tnt_tier_5", 5, "cow"),
    COW_TIER_6("cow_tnt_tier_6", 6, "cow"),
    COW_TIER_7("cow_tnt_tier_7", 7, "cow"),
    COW_TIER_8("cow_tnt_tier_8", 8, "cow"),
    COW_TIER_9("cow_tnt_tier_9", 9, "cow"),
    COW_TIER_10("cow_tnt_tier_10", 10, "cow"),

    CHICKEN_TIER_1("chicken_tnt_tier_1", 1, "chicken"),
    CHICKEN_TIER_2("chicken_tnt_tier_2", 2, "chicken"),
    CHICKEN_TIER_3("chicken_tnt_tier_3", 3, "chicken"),
    CHICKEN_TIER_4("chicken_tnt_tier_4", 4, "chicken"),
    CHICKEN_TIER_5("chicken_tnt_tier_5", 5, "chicken"),
    CHICKEN_TIER_6("chicken_tnt_tier_6", 6, "chicken"),
    CHICKEN_TIER_7("chicken_tnt_tier_7", 7, "chicken"),
    CHICKEN_TIER_8("chicken_tnt_tier_8", 8, "chicken"),
    CHICKEN_TIER_9("chicken_tnt_tier_9", 9, "chicken"),
    CHICKEN_TIER_10("chicken_tnt_tier_10", 10, "chicken"),

    BEE_TIER_1("bee_tnt_tier_1", 1, "bee"),
    BEE_TIER_2("bee_tnt_tier_2", 2, "bee"),
    BEE_TIER_3("bee_tnt_tier_3", 3, "bee"),
    BEE_TIER_4("bee_tnt_tier_4", 4, "bee"),
    BEE_TIER_5("bee_tnt_tier_5", 5, "bee"),
    BEE_TIER_6("bee_tnt_tier_6", 6, "bee"),
    BEE_TIER_7("bee_tnt_tier_7", 7, "bee"),
    BEE_TIER_8("bee_tnt_tier_8", 8, "bee"),
    BEE_TIER_9("bee_tnt_tier_9", 9, "bee"),
    BEE_TIER_10("bee_tnt_tier_10", 10, "bee");

    private final String name;
    private final float entityCount; // Количество сущностей
    private final EntityType<?> entityType;
    private final double spawnRadius;

    SpawnerTntType(String name, int tier, String mobId) {
        this.name = name;
        this.entityCount = TntTiers.spawnCount(tier);
        this.entityType = BuiltInRegistries.ENTITY_TYPE.get(Identifier.withDefaultNamespace(mobId)).orElseThrow().value();
        this.spawnRadius = TntTiers.spawnRadius(tier);
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
    public void explode(ServerLevel level, CustomTntEntity tnt) {
        Explodes.spawnerExplode(level, tnt, this);
    }

    @Override
    @NonNull
    public String getSerializedName() {
        return this.name;
    }
}
