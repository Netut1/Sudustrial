package com.netut.sudustrial.texture_block.soviet_architecture.po_2;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NonNull;

public class Po2CornerBlock extends Block{
    public static final BooleanProperty NORTH = BlockStateProperties.NORTH;
    public static final BooleanProperty EAST = BlockStateProperties.EAST;
    public static final BooleanProperty SOUTH = BlockStateProperties.SOUTH;
    public static final BooleanProperty WEST = BlockStateProperties.WEST;

    public static final IntegerProperty ROW = IntegerProperty.create("row", 0, Po2Fence.HEIGHT - 1);

    private static final double MIN = Po2Fence.MIN;
    private static final double MAX = Po2Fence.MAX;
    private static final VoxelShape POST = box(MIN, 0.0D, MIN, MAX, 16.0D, MAX);
    private static final VoxelShape ARM_NORTH = box(MIN, 0.0D, 0.0D, MAX, 16.0D, MIN);
    private static final VoxelShape ARM_EAST = box(MAX, 0.0D, MIN, 16.0D, 16.0D, MAX);
    private static final VoxelShape ARM_SOUTH = box(MIN, 0.0D, MAX, MAX, 16.0D, 16.0D);
    private static final VoxelShape ARM_WEST = box(0.0D, 0.0D, MIN, MIN, 16.0D, MAX);

    private static final VoxelShape[] SHAPES = new VoxelShape[16];

    static {
        for (int i = 0; i < SHAPES.length; i++) {
            VoxelShape shape = POST;
            if ((i & 1) != 0) shape = Shapes.or(shape, ARM_NORTH);
            if ((i & 2) != 0) shape = Shapes.or(shape, ARM_EAST);
            if ((i & 4) != 0) shape = Shapes.or(shape, ARM_SOUTH);
            if ((i & 8) != 0) shape = Shapes.or(shape, ARM_WEST);
            SHAPES[i] = shape;
        }
    }

    public Po2CornerBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(NORTH, false)
                .setValue(EAST, false)
                .setValue(SOUTH, false)
                .setValue(WEST, false)
                .setValue(ROW, Po2Fence.HEIGHT - 1));
    }


    @Override
    protected void createBlockStateDefinition(StateDefinition.@NonNull Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(NORTH, EAST, SOUTH, WEST, ROW);
    }


    @Override
    @NonNull
    protected VoxelShape getShape(@NonNull BlockState state, @NonNull BlockGetter level, @NonNull BlockPos pos, @NonNull CollisionContext context) {
        int index = (state.getValue(NORTH) ? 1 : 0)
                | (state.getValue(EAST) ? 2 : 0)
                | (state.getValue(SOUTH) ? 4 : 0)
                | (state.getValue(WEST) ? 8 : 0);
        return SHAPES[index];
    }

    private static BooleanProperty arm(Direction direction) {
        return switch (direction) {
            case NORTH -> NORTH;
            case EAST -> EAST;
            case SOUTH -> SOUTH;
            case WEST -> WEST;
            default -> throw new IllegalArgumentException("Not a horizontal direction: " + direction);
        };
    }

    private static boolean connects(BlockState neighbor, Direction direction, int row) {
        if (neighbor.getBlock() instanceof Po2CornerBlock) {
            return neighbor.getValue(ROW) == row;
        }
        if (neighbor.getBlock() instanceof Po2PanelBlock) {
            Direction facing = neighbor.getValue(Po2PanelBlock.FACING);
            if (facing.getAxis() == direction.getAxis()) return false;
            if (neighbor.getValue(Po2PanelBlock.ROW) != row) return false;

            Direction right = Po2Fence.rightOf(facing);
            int column = neighbor.getValue(Po2PanelBlock.COLUMN);
            return (direction == right && column == 0)
                    || (direction == right.getOpposite() && column == Po2Fence.WIDTH - 1);
        }
        return false;
    }

    private static BlockState withArms(LevelReader level, BlockPos pos, BlockState state) {
        int row = state.getValue(ROW);
        for (Direction direction : Direction.Plane.HORIZONTAL) {
            BlockState neighbor = level.getBlockState(pos.relative(direction));
            state = state.setValue(arm(direction), connects(neighbor, direction, row));
        }
        return state;
    }

    @Override
    public @Nullable BlockState getStateForPlacement(@NonNull BlockPlaceContext context) {
        Level level = context.getLevel();
        BlockPos basePos = context.getClickedPos();
        BlockState base = this.defaultBlockState().setValue(ROW, Po2Fence.HEIGHT - 1);

        for (int row = 0; row < Po2Fence.HEIGHT; row++) {
            BlockPos cellPos = basePos.above(Po2Fence.HEIGHT - 1 - row);
            BlockState cell = base.setValue(ROW, row);

            if (level.isOutsideBuildHeight(cellPos)) return null;
            if (!cellPos.equals(basePos) && !level.getBlockState(cellPos).canBeReplaced(context)) return null;
            if (!level.isUnobstructed(cell, cellPos, CollisionContext.empty())) return null;
        }
        return withArms(level, basePos, base);
    }

    @Override
    public void setPlacedBy(@NonNull Level level, @NonNull BlockPos pos, @NonNull BlockState state, @Nullable LivingEntity placer, @NonNull ItemStack stack) {
        super.setPlacedBy(level, pos, state, placer, stack);
        if (level.isClientSide()) return;

        for (int row = Po2Fence.HEIGHT - 2; row >= 0; row--) {
            BlockPos cellPos = pos.above(Po2Fence.HEIGHT - 1 - row);
            level.setBlock(cellPos, withArms(level, cellPos, state.setValue(ROW, row)), Block.UPDATE_ALL);
        }
    }

    @Override
    @NonNull
    protected BlockState updateShape(@NonNull BlockState state, @NonNull LevelReader level, @NonNull ScheduledTickAccess scheduledTickAccess, @NonNull BlockPos pos, @NonNull Direction direction, @NonNull BlockPos neighborPos, @NonNull BlockState neighborState, @NonNull RandomSource random) {
        if (direction.getAxis().isVertical()) {
            int row = state.getValue(ROW);
            boolean partnerExpected = direction == Direction.UP ? row > 0 : row < Po2Fence.HEIGHT - 1;
            if (partnerExpected) {
                int expectedRow = direction == Direction.UP ? row - 1 : row + 1;
                boolean intact = neighborState.getBlock() instanceof Po2CornerBlock
                        && neighborState.getValue(ROW) == expectedRow;
                if (!intact) return Blocks.AIR.defaultBlockState();
            }
            return super.updateShape(state, level, scheduledTickAccess, pos, direction, neighborPos, neighborState, random);
        }
        return state.setValue(arm(direction), connects(neighborState, direction, state.getValue(ROW)));
    }
}
