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
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NonNull;

public class Po2PanelBlock extends Block{
    public static final EnumProperty<Direction> FACING = BlockStateProperties.HORIZONTAL_FACING;

    public static final IntegerProperty COLUMN = IntegerProperty.create("column", 0, Po2Fence.WIDTH - 1);

    public static final IntegerProperty ROW = IntegerProperty.create("row", 0, Po2Fence.HEIGHT - 1);

    private static final VoxelShape SHAPE_ALONG_X = box(0.0D, 0.0D, Po2Fence.MIN, 16.0D, 16.0D, Po2Fence.MAX);
    private static final VoxelShape SHAPE_ALONG_Z = box(Po2Fence.MIN, 0.0D, 0.0D, Po2Fence.MAX, 16.0D, 16.0D);

    public Po2PanelBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.NORTH)
                .setValue(COLUMN, 0)
                .setValue(ROW, Po2Fence.HEIGHT - 1));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.@NonNull Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(FACING, COLUMN, ROW);
    }

    @Override
    @NonNull
    protected VoxelShape getShape(@NonNull BlockState state, @NonNull BlockGetter level, @NonNull BlockPos pos, @NonNull CollisionContext context) {
        return state.getValue(FACING).getAxis() == Direction.Axis.Z ? SHAPE_ALONG_X : SHAPE_ALONG_Z;
    }

    public static BlockPos cellPos(BlockPos fromPos, BlockState fromState, int col, int row) {
        Direction right = Po2Fence.rightOf(fromState.getValue(FACING));
        return fromPos
                .relative(right, col - fromState.getValue(COLUMN))
                .above(fromState.getValue(ROW) - row);
    }

    @Override
    public @Nullable BlockState getStateForPlacement(@NonNull BlockPlaceContext context) {
        // лицом к игроку; секция растёт вправо от кликнутого блока, с Shift - влево
        Direction facing = context.getHorizontalDirection().getOpposite();
        int anchorColumn = context.isSecondaryUseActive() ? Po2Fence.WIDTH - 1 : 0;

        BlockState anchor = this.defaultBlockState()
                .setValue(FACING, facing)
                .setValue(COLUMN, anchorColumn)
                .setValue(ROW, Po2Fence.HEIGHT - 1);

        Level level = context.getLevel();
        BlockPos anchorPos = context.getClickedPos();

        for (int col = 0; col < Po2Fence.WIDTH; col++) {
            for (int row = 0; row < Po2Fence.HEIGHT; row++) {
                BlockPos cellPos = cellPos(anchorPos, anchor, col, row);
                BlockState cell = anchor.setValue(COLUMN, col).setValue(ROW, row);

                if (level.isOutsideBuildHeight(cellPos)) return null;
                if (!cellPos.equals(anchorPos) && !level.getBlockState(cellPos).canBeReplaced(context)) return null;
                if (!level.isUnobstructed(cell, cellPos, CollisionContext.empty())) return null;
            }
        }
        return anchor;
    }

    @Override
    public void setPlacedBy(@NonNull Level level, @NonNull BlockPos pos, @NonNull BlockState state, @Nullable LivingEntity placer, @NonNull ItemStack stack) {
        super.setPlacedBy(level, pos, state, placer, stack);
        if (level.isClientSide()) return;

        int anchorCol = state.getValue(COLUMN);
        int anchorRow = state.getValue(ROW);

        for (int col = 0; col < Po2Fence.WIDTH; col++) {
            for (int row = 0; row < Po2Fence.HEIGHT; row++) {
                if (col == anchorCol && row == anchorRow) continue;
                level.setBlock(cellPos(pos, state, col, row), state.setValue(COLUMN, col).setValue(ROW, row), Block.UPDATE_ALL);
            }
        }
    }

    @Override
    @NonNull
    protected BlockState updateShape(@NonNull BlockState state, @NonNull LevelReader level, @NonNull ScheduledTickAccess scheduledTickAccess, @NonNull BlockPos pos, @NonNull Direction direction, @NonNull BlockPos neighborPos, @NonNull BlockState neighborState, @NonNull RandomSource random) {
        if (!isIntact(state, direction, neighborState)) {
            return Blocks.AIR.defaultBlockState();
        }
        return super.updateShape(state, level, scheduledTickAccess, pos, direction, neighborPos, neighborState, random);
    }

    private static boolean isIntact(BlockState self, Direction direction, BlockState neighbor) {
        int col = self.getValue(COLUMN);
        int row = self.getValue(ROW);
        Direction right = Po2Fence.rightOf(self.getValue(FACING));

        int expectedCol = col;
        int expectedRow = row;
        if (direction == right) expectedCol = col + 1;
        else if (direction == right.getOpposite()) expectedCol = col - 1;
        else if (direction == Direction.UP) expectedRow = row - 1;
        else if (direction == Direction.DOWN) expectedRow = row + 1;
        else return true;

        if (expectedCol < 0 || expectedCol >= Po2Fence.WIDTH || expectedRow < 0 || expectedRow >= Po2Fence.HEIGHT) {
            return true;
        }
        return neighbor.is(self.getBlock())
                && neighbor.getValue(FACING) == self.getValue(FACING)
                && neighbor.getValue(COLUMN) == expectedCol
                && neighbor.getValue(ROW) == expectedRow;
    }
}
