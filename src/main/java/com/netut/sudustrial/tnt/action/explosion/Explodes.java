package com.netut.sudustrial.tnt.action.explosion;

import com.netut.sudustrial.tnt.action.explosion.optimization.NuclearExplosionManager;
import com.netut.sudustrial.tnt.action.explosion.optimization.ExplosionOptimizer;
import com.netut.sudustrial.tnt.entity.NuclearCoreBlockEntity;
import com.netut.sudustrial.tnt.entity.CustomTntEntity;
import com.netut.sudustrial.register.block.nuclear_block.RegisterNuclearCore;
import com.netut.sudustrial.register.sound.party_explode.RegisterPartyExplodeSound;
import com.netut.sudustrial.tnt.type.block.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.AABB;

public class Explodes {
    private Explodes() {
    }

    private static final TagKey<Block> ORES_TAG = TagKey.create(
            Registries.BLOCK,
            Identifier.fromNamespaceAndPath("c", "ores")
    );

    public static void explode(CustomTntEntity entity) {
        if (entity.level() instanceof ServerLevel serverLevel) {
            entity.getVariant().explode(serverLevel, entity);
        }
    }

    public static void standardExplode(ServerLevel level, CustomTntEntity entity, TntType type) {
        float power = type.getExplosionPower();

        if (power == 0.0f) {
            partyExplode(level, entity);
        } else if (power > 32.0F) {
            optimizedExplode(level, entity, power);
        } else {
            entity.level().explode(entity, entity.getX(), entity.getY(0.0625),
                    entity.getZ(), power, Level.ExplosionInteraction.TNT
            );
        }
    }

    public static void partyExplode(ServerLevel level, CustomTntEntity entity) {
        level.playSound(null, entity.getX(), entity.getY(), entity.getZ(),
                RegisterPartyExplodeSound.PARTY_EXPLODE, SoundSource.BLOCKS,
                4.0F, (1.0F + (level.getRandom().nextFloat() - level.getRandom().nextFloat()) * 0.2F) * 0.7F);
    }

    public static void optimizedExplode(ServerLevel level, CustomTntEntity entity, float power) {
        int radius = Math.min((int) (power * 0.2F), 100);
        BlockPos center = entity.blockPosition();
        int rSq = radius * radius;
        ExplosionOptimizer.prepareArea(level, entity.position(), radius);

        level.playSound(null, entity.getX(), entity.getY(), entity.getZ(),
                SoundEvents.GENERIC_EXPLODE.value(), SoundSource.BLOCKS,
                4.0F, (1.0F + (level.getRandom().nextFloat() - level.getRandom().nextFloat()) * 0.2F) * 0.7F);

        AABB damageBox = new AABB(center).inflate(radius);
        for (Entity target : level.getEntities(entity, damageBox)) {
            if (target.distanceToSqr(entity) <= rSq) {
                target.hurtServer(level, level.damageSources().explosion(entity, entity.getOwner()), power * 3.0F);
            }
        }

        BlockPos minPos = center.offset(-radius, -radius, -radius);
        BlockPos maxPos = center.offset(radius, radius, radius);

        for (BlockPos pos : BlockPos.betweenClosed(minPos, maxPos)) {
            if (center.distSqr(pos) <= rSq) {
                BlockState state = level.getBlockState(pos);
                if (!state.isAir() && state.getBlock().getExplosionResistance() < 1200.0F) {
                    level.setBlock(pos, Blocks.AIR.defaultBlockState(), 2);
                }
            }
        }
        ExplosionOptimizer.markChunksForRenderUpdate(level, entity.position(), radius);
    }

    public static void nuclearExplode(ServerLevel level, CustomTntEntity entity, NuclearTntType type) {
        float power = type.getExplosionPower();
        int radius = Math.min((int) (power * 0.2F), 200);
        BlockPos center = entity.blockPosition();
        int rSq = radius * radius;
        int effect_radius = radius + 2;

        level.playSound(null, entity.getX(), entity.getY(), entity.getZ(),
                SoundEvents.GENERIC_EXPLODE.value(), SoundSource.BLOCKS,
                4.0F, (1.0F + (level.getRandom().nextFloat() - level.getRandom().nextFloat()) * 0.2F) * 0.7F);

        AABB damageBox = new AABB(center).inflate(radius);
        for (Entity target : level.getEntities(entity, damageBox)) {
            if (target.distanceToSqr(entity) <= rSq) {
                target.hurtServer(level, level.damageSources().explosion(entity, entity.getOwner()), power * 3.0F);
            }
        }

        level.setBlock(center, RegisterNuclearCore.NUCLEAR_CORE_BLOCK.defaultBlockState(), 3);
        if (level.getBlockEntity(center) instanceof NuclearCoreBlockEntity coreEntity) {
            coreEntity.setRadius(effect_radius);
        }

        NuclearExplosionManager.start(level, center, radius);
        ExplosionOptimizer.waterRemove(level, center, radius);
    }

    public static void elementalExplode(ServerLevel level, CustomTntEntity entity, ElementalTntType type) {
        int radius = (int) type.getExplosionPower();
        BlockPos center = entity.blockPosition();
        int rSq = radius * radius;
        ExplosionOptimizer.prepareArea(level, entity.position(), radius);

        level.playSound(null, entity.getX(), entity.getY(), entity.getZ(),
                SoundEvents.GENERIC_EXPLODE.value(), SoundSource.BLOCKS,
                4.0F, (1.0F + (level.getRandom().nextFloat() - level.getRandom().nextFloat()) * 0.2F) * 0.7F);

        BlockPos minPos = center.offset(-radius, -radius, -radius);
        BlockPos maxPos = center.offset(radius, radius, radius);

        for (BlockPos pos : BlockPos.betweenClosed(minPos, maxPos)) {
            if (center.distSqr(pos) <= rSq) {
                BlockState state = level.getBlockState(pos);
                FluidState fluidState = level.getFluidState(pos);

                if (state.isAir()) {
                    level.setBlock(pos, type.getAirReplacement().defaultBlockState(), 3);
                } else if (!fluidState.isEmpty()) {
                    level.setBlock(pos, type.getLiquidReplacement().defaultBlockState(), 3);
                } else if (state.getBlock().getExplosionResistance() < 1200.0F) {
                    level.setBlock(pos, type.getSolidReplacement().defaultBlockState(), 3);
                }
            }
        }
        ExplosionOptimizer.markChunksForRenderUpdate(level, entity.position(), radius);
    }

    public static void spawnerExplode(ServerLevel level, CustomTntEntity entity, SpawnerTntType type) {
        int count = (int) type.getEntityCount();
        double radius = type.getSpawnRadius();

        level.playSound(null, entity.getX(), entity.getY(), entity.getZ(),
                SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS,
                2.0F, 0.5F);

        for (int i = 0; i < count; i++) {
            double offsetX = (level.getRandom().nextDouble() - 0.5D) * 2.0D * radius;
            double offsetY = level.getRandom().nextDouble() * 1.5D;
            double offsetZ = (level.getRandom().nextDouble() - 0.5D) * 2.0D * radius;

            double spawnX = entity.getX() + offsetX;
            double spawnY = entity.getY() + offsetY;
            double spawnZ = entity.getZ() + offsetZ;

            // Если версия ниже 1.21.2, заменить EntitySpawnReason.TRIGGERED на MobSpawnType.TRIGGERED
            Entity spawned = type.getEntityType().create(level, EntitySpawnReason.TRIGGERED);
            if (spawned != null) {
                spawned.setPos(spawnX, spawnY, spawnZ);
                spawned.setYRot(level.getRandom().nextFloat() * 360.0F);

                if (spawned instanceof Mob mob) {
                    mob.finalizeSpawn(level, level.getCurrentDifficultyAt(BlockPos.containing(spawnX, spawnY, spawnZ)),
                            EntitySpawnReason.TRIGGERED, null);
                }
                level.addFreshEntity(spawned);
            }
        }
    }

    public static void effectExplode(ServerLevel level, CustomTntEntity entity, EffectTntType type) {
        level.playSound(null, entity.getX(), entity.getY(), entity.getZ(),
                SoundEvents.GLASS_BREAK, SoundSource.BLOCKS,
                1.0F, 1.0F);

        float maxRadius = type.getRadius();
        int maxRadiusInt = (int) Math.floor(maxRadius);
        int maxDuration = type.getDuration();
        float shrinkSpeed = maxRadius / (float) maxDuration;
        for (int yOffset = -maxRadiusInt; yOffset <= maxRadiusInt; yOffset++) {
            float initialLayerRadius = (float) Math.sqrt(maxRadius * maxRadius - yOffset * yOffset);
            if (initialLayerRadius < 0.2F) continue;
            int layerDuration = Math.max(1, (int) (initialLayerRadius / shrinkSpeed));
            double spawnX = entity.getX();
            double spawnY = entity.getY() + yOffset;
            double spawnZ = entity.getZ();

            AreaEffectCloud cloud = new AreaEffectCloud(level, spawnX, spawnY, spawnZ);
            if (entity.getOwner() instanceof net.minecraft.world.entity.LivingEntity owner) {
                cloud.setOwner(owner);
            }

            cloud.setRadius(initialLayerRadius);
            cloud.setWaitTime(0);
            cloud.setDuration(layerDuration);
            cloud.setRadiusPerTick(-shrinkSpeed);

            int effectDuration;
            if (type.isLinger()) {
                effectDuration = maxDuration;
            } else {
                effectDuration = 20;
                cloud.setDurationOnUse(0);
            }

            cloud.addEffect(new MobEffectInstance(
                    type.getEffect(),
                    effectDuration * 2,
                    type.getAmplifier(),
                    false,
                    true
            ));

            level.addFreshEntity(cloud);
        }
    }

    public static void miningExplode(ServerLevel level, CustomTntEntity entity, MiningTntType type) {
        BlockPos center = entity.blockPosition();
        int radius = type.getRadius();
        ExplosionOptimizer.prepareArea(level, entity.position(), radius);

        level.playSound(null, entity.getX(), entity.getY(), entity.getZ(),
                SoundEvents.GENERIC_EXPLODE.value(), SoundSource.BLOCKS,
                4.0F, 1.0F);

        if (type.isColumn()) {
            int minX = center.getX() - radius;
            int maxX = center.getX() + radius;
            int minZ = center.getZ() - radius;
            int maxZ = center.getZ() + radius;
            int minY = level.getMinY();
            int maxY = center.getY();

            BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();
            for (int x = minX; x <= maxX; x++) {
                for (int z = minZ; z <= maxZ; z++) {
                    for (int y = maxY; y >= minY; y--) {
                        pos.set(x, y, z);
                        removeNonOreBlock(level, pos);
                    }
                }
            }
        } else {
            // Сфера
            int rSq = radius * radius;
            BlockPos minPos = center.offset(-radius, -radius, -radius);
            BlockPos maxPos = center.offset(radius, radius, radius);

            for (BlockPos pos : BlockPos.betweenClosed(minPos, maxPos)) {
                if (center.distSqr(pos) <= rSq) {
                    removeNonOreBlock(level, pos);
                }
            }
        }
        ExplosionOptimizer.markChunksForRenderUpdate(level, entity.position(), radius);
    }

    private static void removeNonOreBlock(ServerLevel level, BlockPos pos) {
        BlockState state = level.getBlockState(pos);
        if (state.isAir() || state.is(Blocks.BEDROCK)) return;

        // Если блок НЕ является рудой — удаляем
        if (!state.is(ORES_TAG)) {
            level.setBlock(pos, Blocks.AIR.defaultBlockState(), 2);
        }
    }
}
