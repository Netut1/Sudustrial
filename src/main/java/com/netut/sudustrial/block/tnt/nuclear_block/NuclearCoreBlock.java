package com.netut.sudustrial.block.tnt.nuclear_block;

import com.netut.sudustrial.register.ModBlockEntities;
import com.netut.sudustrial.register.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
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
        BlockState nuclearAirState = ModBlocks.NUCLEAR_AIR.defaultBlockState();
        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();

        for (int x = -DECORATIVE_SPHERE_RADIUS; x <= DECORATIVE_SPHERE_RADIUS; x++) {
            for (int y = -DECORATIVE_SPHERE_RADIUS; y <= DECORATIVE_SPHERE_RADIUS; y++) {
                for (int z = -DECORATIVE_SPHERE_RADIUS; z <= DECORATIVE_SPHERE_RADIUS; z++) {
                    if (x * x + y * y + z * z <= rSq) {
                        pos.set(center.getX() + x, center.getY() + y, center.getZ() + z);
                        if (!pos.equals(center)) {
                            level.setBlock(pos, nuclearAirState, 2);
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
