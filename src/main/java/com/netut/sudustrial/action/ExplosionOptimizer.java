package com.netut.sudustrial.action;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.chunk.status.ChunkStatus;
import net.minecraft.world.phys.Vec3;

public class ExplosionOptimizer {
    private ExplosionOptimizer() {}

    // Предзагружает все чанки в радиусе взрыва.
    // Предотвращает динамическое расширение карт и пулов сервера во время взрыва.
    public static void prepareArea(ServerLevel level, Vec3 center, float radius) {
        BlockPos centerPos = BlockPos.containing(center);
        int radInt = (int) Math.ceil(radius);

        int minChunkX = (centerPos.getX() - radInt) >> 4;
        int maxChunkX = (centerPos.getX() + radInt) >> 4;
        int minChunkZ = (centerPos.getZ() - radInt) >> 4;
        int maxChunkZ = (centerPos.getZ() + radInt) >> 4;

        for (int chunkX = minChunkX; chunkX <= maxChunkX; chunkX++) {
            for (int chunkZ = minChunkZ; chunkZ <= maxChunkZ; chunkZ++) {
                level.getChunkSource().getChunk(chunkX, chunkZ, ChunkStatus.FULL, true);
            }
        }
    }

    //Отправляет пакеты обновления чанков клиентам, находящимся поблизости.
    public static void markChunksForRenderUpdate(ServerLevel level, Vec3 center, float radius) {
        BlockPos centerPos = BlockPos.containing(center);
        int radInt = (int) Math.ceil(radius);

        int minChunkX = (centerPos.getX() - radInt) >> 4;
        int maxChunkX = (centerPos.getX() + radInt) >> 4;
        int minChunkZ = (centerPos.getZ() - radInt) >> 4;
        int maxChunkZ = (centerPos.getZ() + radInt) >> 4;

        // Используем getMinY() и getMaxY() для определения границ высоты мира
        int minY = level.getMinY();
        int maxY = level.getMaxY();

        for (int chunkX = minChunkX; chunkX <= maxChunkX; chunkX++) {
            for (int chunkZ = minChunkZ; chunkZ <= maxChunkZ; chunkZ++) {
                BlockPos minPos = new BlockPos(chunkX << 4, minY, chunkZ << 4);
                BlockPos maxPos = new BlockPos((chunkX << 4) + 15, maxY, (chunkZ << 4) + 15);

                for (ServerPlayer player : level.players()) {
                    if (player.distanceToSqr(center) <= (radius + 128) * (radius + 128)) {
                        level.getChunkSource().blockChanged(minPos);
                        level.getChunkSource().blockChanged(maxPos);
                    }
                }
            }
        }
    }
}
