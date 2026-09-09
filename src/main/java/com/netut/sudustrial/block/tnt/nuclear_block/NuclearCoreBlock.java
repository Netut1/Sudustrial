package com.netut.sudustrial.block.tnt.nuclear_block;

import com.netut.sudustrial.action.DelayedTaskScheduler;
import com.netut.sudustrial.entity.tnt.nuclear.NuclearCoreBlockEntity;
import com.netut.sudustrial.register.ModBlockEntities;
import com.netut.sudustrial.register.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NonNull;

public class NuclearCoreBlock extends Block implements EntityBlock {
    private static final int DECORATIVE_SPHERE_RADIUS = 4;
    public static final VoxelShape SHAPE = Shapes.or(
            Block.box(2, 2, 2, 14, 14, 14),   // Внешняя оболочка
            Block.box(0, 0, 0, 4, 4, 4),      // угол (0,0,0)
            Block.box(12, 0, 0, 16, 4, 4),    // угол (16,0,0)
            Block.box(0, 12, 0, 4, 16, 4),    // угол (0,16,0)
            Block.box(0, 0, 12, 4, 4, 16),    // угол (0,0,16)
            Block.box(12, 12, 0, 16, 16, 4),  // угол (16,16,0)
            Block.box(12, 0, 12, 16, 4, 16),  // угол (16,0,12)
            Block.box(0, 12, 12, 4, 16, 16),  // угол (0,16,16)
            Block.box(12, 12, 12, 16, 16, 16) // угол (16,16,16)
    );

    public NuclearCoreBlock(Properties properties) {
        super(properties);
    }

    @Override
    @NonNull
    protected VoxelShape getShape(@NonNull BlockState state, @NonNull BlockGetter level, @NonNull BlockPos pos, @NonNull CollisionContext context) {
        return SHAPE;
    }

    @Override
    protected void onPlace(@NonNull BlockState state, @NonNull Level level, @NonNull BlockPos pos, @NonNull BlockState oldState, boolean movedByPiston) {
        super.onPlace(state, level, pos, oldState, movedByPiston);

        if (level.isClientSide() || oldState.is(state.getBlock())) return;
        if (!(level instanceof ServerLevel serverLevel)) return;

        placeDecorativeSphere(serverLevel, pos);
    }

    private void placeDecorativeSphere(ServerLevel level, BlockPos center) {
        int rSq = DECORATIVE_SPHERE_RADIUS * DECORATIVE_SPHERE_RADIUS;
        BlockState baseNuclearAirState = ModBlocks.NUCLEAR_AIR.defaultBlockState();
        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();

        for (int x = -DECORATIVE_SPHERE_RADIUS; x <= DECORATIVE_SPHERE_RADIUS; x++) {
            for (int y = -DECORATIVE_SPHERE_RADIUS; y <= DECORATIVE_SPHERE_RADIUS; y++) {
                for (int z = -DECORATIVE_SPHERE_RADIUS; z <= DECORATIVE_SPHERE_RADIUS; z++) {
                    int distSq = x * x + y * y + z * z;
                    if (distSq <= rSq) {
                        pos.set(center.getX() + x, center.getY() + y, center.getZ() + z);
                        if (!pos.equals(center)) {
                            int geometricDistance = Math.max(1, (int) Math.ceil(Math.sqrt(distSq)));
                            BlockState stateToPlace = baseNuclearAirState.setValue(NuclearAirBlock.DISTANCE, geometricDistance);
                            level.setBlock(pos, stateToPlace, 2);
                        }
                    }
                }
            }
        }
    }

    @Override
    protected void affectNeighborsAfterRemoval(@NonNull BlockState state, @NonNull ServerLevel level, @NonNull BlockPos pos, boolean movedByPiston) {
        BlockPos corePos = pos.immutable();
        DelayedTaskScheduler.schedule(level, 100, () -> sweepDecorativeSphere(level, corePos));
        super.affectNeighborsAfterRemoval(state, level, pos, movedByPiston);
    }

    private void sweepDecorativeSphere(ServerLevel level, BlockPos center) {
        int sweepRadius = DECORATIVE_SPHERE_RADIUS + 1;
        int rSq = sweepRadius * sweepRadius;
        RandomSource random = level.getRandom();

        for (int x = -sweepRadius; x <= sweepRadius; x++) {
            for (int y = -sweepRadius; y <= sweepRadius; y++) {
                for (int z = -sweepRadius; z <= sweepRadius; z++) {
                    if (x * x + y * y + z * z <= rSq) {
                        BlockPos strayPos = new BlockPos(center.getX() + x, center.getY() + y, center.getZ() + z);
                        if (level.getBlockState(strayPos).is(ModBlocks.NUCLEAR_AIR)) {
                            int extraDelay = random.nextInt(30);
                            DelayedTaskScheduler.schedule(level, extraDelay, () -> {
                                // Перепроверяем на случай, если блок уже кто-то убрал за это время
                                if (level.getBlockState(strayPos).is(ModBlocks.NUCLEAR_AIR)) {
                                    level.removeBlock(strayPos, false);
                                }
                            });
                        }
                    }
                }
            }
        }
    }

    @Override
    @Nullable
    public BlockEntity newBlockEntity(@NonNull BlockPos pos, @NonNull BlockState state) {
        return new NuclearCoreBlockEntity(pos, state);
    }

    @Nullable
    @SuppressWarnings("unchecked")
    private static <E extends BlockEntity, A extends BlockEntity> BlockEntityTicker<A> createTickerHelper(
            BlockEntityType<A> serverType, BlockEntityType<E> handledType, BlockEntityTicker<? super E> ticker) {
        return handledType == serverType ? (BlockEntityTicker<A>) ticker : null;
    }

    @Override
    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(@NonNull Level level, @NonNull BlockState state, @NonNull BlockEntityType<T> blockEntityType) {
        if (level.isClientSide()) return null;
        return createTickerHelper(blockEntityType, ModBlockEntities.NUCLEAR_CORE, NuclearCoreBlockEntity::serverTick);
    }
}
