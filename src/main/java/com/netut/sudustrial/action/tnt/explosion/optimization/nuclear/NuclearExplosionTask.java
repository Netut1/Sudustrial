package com.netut.sudustrial.action.tnt.explosion.optimization.nuclear;

import com.netut.sudustrial.register.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.status.ChunkStatus;

import java.util.ArrayList;
import java.util.List;

public class NuclearExplosionTask {
    private static final int NEW_COLUMNS_PER_TICK = 100;
    private static final int SECTIONS_PER_TICK = 100;

    // Ширина приграничной полосы в блоках, где имеет смысл проверять соседей на воду снаружи радиуса.
    private static final int SURFACE_MARGIN = 2;

    // Чем затыкаем настоящую протечку с водой.
    private static final BlockState DAM_STATE = Blocks.CLAY.defaultBlockState();

    private final ServerLevel level;
    private final BlockPos center;
    private final long rSq;
    private final long surfaceThresholdSq;
    private final BlockState airState;

    private final int[] colXs;
    private final int[] colZs;
    private int preloadCursor = 0;
    private boolean preloadDone = false;

    private final int[] secXs;
    private final int[] secYs;
    private final int[] secZs;
    private int replaceCursor = 0;
    private boolean finished = false;

    private record ColumnEntry(int cx, int cz, long distSq) {}
    private record SectionEntry(int sx, int sy, int sz, long distSq) {}

    public NuclearExplosionTask(ServerLevel level, BlockPos center, int radius) {
        this.level = level;
        this.center = center;
        this.rSq = (long) radius * (long) radius;
        this.airState = Blocks.AIR.defaultBlockState();

        int margin = Math.max(0, radius - SURFACE_MARGIN);
        this.surfaceThresholdSq = (long) margin * margin;

        int minX = center.getX() - radius;
        int maxX = center.getX() + radius;
        int minY = Math.max(level.getMinY(), center.getY() - radius);
        int maxY = Math.min(level.getMaxY(), center.getY() + radius);
        int minZ = center.getZ() - radius;
        int maxZ = center.getZ() + radius;

        int secMinX = minX >> 4, secMaxX = maxX >> 4;
        int secMinY = minY >> 4, secMaxY = maxY >> 4;
        int secMinZ = minZ >> 4, secMaxZ = maxZ >> 4;

        List<SectionEntry> sectionEntries = new ArrayList<>();
        for (int sx = secMinX; sx <= secMaxX; sx++) {
            for (int sy = secMinY; sy <= secMaxY; sy++) {
                for (int sz = secMinZ; sz <= secMaxZ; sz++) {
                    if (sectionIntersectsSphere(sx, sy, sz)) {
                        sectionEntries.add(new SectionEntry(sx, sy, sz, sortKey3D(sx, sy, sz)));
                    }
                }
            }
        }
        sectionEntries.sort((a, b) -> Long.compare(a.distSq(), b.distSq()));

        int secCount = sectionEntries.size();
        this.secXs = new int[secCount];
        this.secYs = new int[secCount];
        this.secZs = new int[secCount];
        for (int i = 0; i < secCount; i++) {
            SectionEntry e = sectionEntries.get(i);
            secXs[i] = e.sx();
            secYs[i] = e.sy();
            secZs[i] = e.sz();
        }

        List<ColumnEntry> columnEntries = new ArrayList<>();
        for (int cx = secMinX; cx <= secMaxX; cx++) {
            for (int cz = secMinZ; cz <= secMaxZ; cz++) {
                if (columnIntersectsSphere(cx, cz)) {
                    columnEntries.add(new ColumnEntry(cx, cz, sortKey2D(cx, cz)));
                }
            }
        }
        columnEntries.sort((a, b) -> Long.compare(a.distSq(), b.distSq()));

        int colCount = columnEntries.size();
        this.colXs = new int[colCount];
        this.colZs = new int[colCount];
        for (int i = 0; i < colCount; i++) {
            ColumnEntry e = columnEntries.get(i);
            colXs[i] = e.cx();
            colZs[i] = e.cz();
        }
    }

    public boolean isFinished() {
        return finished;
    }

    public boolean tick() {
        if (finished) return true;

        if (!preloadDone) {
            preloadDone = tickPreload();
            return false;
        }

        return tickReplace();
    }

    private boolean tickPreload() {
        int budget = NEW_COLUMNS_PER_TICK;

        while (preloadCursor < colXs.length) {
            int cx = colXs[preloadCursor];
            int cz = colZs[preloadCursor];

            ChunkAccess existing = level.getChunkSource().getChunk(cx, cz, ChunkStatus.FULL, false);
            if (existing == null) {
                level.getChunkSource().getChunk(cx, cz, ChunkStatus.FULL, true);
                budget--;
            }

            preloadCursor++;
            if (budget <= 0) {
                return false;
            }
        }
        return true;
    }

    private boolean tickReplace() {
        int budget = SECTIONS_PER_TICK;
        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();
        BlockPos.MutableBlockPos neighborPos = new BlockPos.MutableBlockPos();

        while (budget > 0 && replaceCursor < secXs.length) {
            processSection(secXs[replaceCursor], secYs[replaceCursor], secZs[replaceCursor], pos, neighborPos);
            replaceCursor++;
            budget--;
        }

        if (replaceCursor >= secXs.length) {
            finished = true;
            return true;
        }
        return false;
    }

    private void processSection(int sx, int sy, int sz, BlockPos.MutableBlockPos pos, BlockPos.MutableBlockPos neighborPos) {
        int baseX = sx << 4;
        int baseY = sy << 4;
        int baseZ = sz << 4;

        for (int lx = 0; lx < 16; lx++) {
            int x = baseX + lx;
            long dx = (long) (x - center.getX()) * (x - center.getX());

            for (int ly = 0; ly < 16; ly++) {
                int y = baseY + ly;
                if (y < level.getMinY() || y > level.getMaxY()) continue;
                long dy = (long) (y - center.getY()) * (y - center.getY());

                for (int lz = 0; lz < 16; lz++) {
                    int z = baseZ + lz;
                    long dz = (long) (z - center.getZ()) * (z - center.getZ());
                    long distSq = dx + dy + dz;

                    if (distSq <= rSq) {
                        pos.set(x, y, z);
                        if (pos.equals(center)) continue;

                        BlockState current = level.getBlockState(pos);
                        if (current.is(ModBlocks.NUCLEAR_AIR) || current.getBlock().getExplosionResistance() > 1199.0 || current.isAir()) continue;

                        // Только у самого края сферы проверяем не граничит ли эта клетка с водой снаружи радиуса.
                        if (distSq >= surfaceThresholdSq && isAdjacentToExternalFluid(x, y, z, neighborPos)) {
                            level.setBlock(pos, DAM_STATE, 2);
                        } else {
                            level.setBlock(pos, airState, 2);
                        }
                    }
                }
            }
        }
    }

    private boolean isAdjacentToExternalFluid(int x, int y, int z, BlockPos.MutableBlockPos neighborPos) {
        int[][] offsets = {{1, 0, 0}, {-1, 0, 0}, {0, 1, 0}, {0, -1, 0}, {0, 0, 1}, {0, 0, -1}};
        for (int[] o : offsets) {
            int nx = x + o[0];
            int ny = y + o[1];
            int nz = z + o[2];

            long ndx = (long) (nx - center.getX()) * (nx - center.getX());
            long ndy = (long) (ny - center.getY()) * (ny - center.getY());
            long ndz = (long) (nz - center.getZ()) * (nz - center.getZ());

            if (ndx + ndy + ndz > rSq) { // сосед действительно ВНЕ сферы
                neighborPos.set(nx, ny, nz);
                if (!level.getFluidState(neighborPos).isEmpty()) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean sectionIntersectsSphere(int sx, int sy, int sz) {
        int baseX = sx << 4, baseY = sy << 4, baseZ = sz << 4;
        int nearestX = clamp(center.getX(), baseX, baseX + 15);
        int nearestY = clamp(center.getY(), baseY, baseY + 15);
        int nearestZ = clamp(center.getZ(), baseZ, baseZ + 15);

        long dx = nearestX - center.getX();
        long dy = nearestY - center.getY();
        long dz = nearestZ - center.getZ();
        return dx * dx + dy * dy + dz * dz <= rSq;
    }

    private boolean columnIntersectsSphere(int cx, int cz) {
        int baseX = cx << 4, baseZ = cz << 4;
        int nearestX = clamp(center.getX(), baseX, baseX + 15);
        int nearestZ = clamp(center.getZ(), baseZ, baseZ + 15);

        long dx = nearestX - center.getX();
        long dz = nearestZ - center.getZ();
        return dx * dx + dz * dz <= rSq;
    }

    private long sortKey3D(int sx, int sy, int sz) {
        long dx = ((long) (sx << 4) + 8) - center.getX();
        long dy = ((long) (sy << 4) + 8) - center.getY();
        long dz = ((long) (sz << 4) + 8) - center.getZ();
        return dx * dx + dy * dy + dz * dz;
    }

    private long sortKey2D(int cx, int cz) {
        long dx = ((long) (cx << 4) + 8) - center.getX();
        long dz = ((long) (cz << 4) + 8) - center.getZ();
        return dx * dx + dz * dz;
    }

    private static int clamp(int v, int min, int max) {
        return Math.max(min, Math.min(max, v));
    }
}
