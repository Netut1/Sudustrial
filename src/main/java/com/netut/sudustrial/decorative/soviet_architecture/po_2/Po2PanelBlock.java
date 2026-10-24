package com.netut.sudustrial.decorative.soviet_architecture.po_2;

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
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NonNull;

public class Po2PanelBlock extends Block {
    public static final EnumProperty<Direction> FACING = BlockStateProperties.HORIZONTAL_FACING;

    public static final IntegerProperty COLUMN = IntegerProperty.create("column", 0, Po2Fence.WIDTH - 1);

    public static final IntegerProperty ROW = IntegerProperty.create("row", 0, Po2Fence.HEIGHT - 1);

    public static final BooleanProperty CONNECT_FRONT = BooleanProperty.create("connect_front");

    public static final BooleanProperty CONNECT_BACK = BooleanProperty.create("connect_back");

    public static final BooleanProperty TRIM = BooleanProperty.create("trim");

    private static final double MIN = Po2Fence.MIN;
    private static final double MAX = Po2Fence.MAX;

    private static final VoxelShape[] SHAPES = new VoxelShape[4 * Po2Fence.WIDTH * 2 * 2 * 2 * 2];

    static {
        for (int turns = 0; turns < 4; turns++) {
            for (int col = 0; col < Po2Fence.WIDTH; col++) {
                for (int bottom = 0; bottom < 2; bottom++) {
                    for (int front = 0; front < 2; front++) {
                        for (int back = 0; back < 2; back++) {
                            for (int trim = 0; trim < 2; trim++) {
                                SHAPES[shapeIndex(turns, col, bottom == 1, front == 1, back == 1, trim == 1)] =
                                        buildShape(turns, col, bottom == 1, front == 1, back == 1, trim == 1);
                            }
                        }
                    }
                }
            }
        }
    }

    public Po2PanelBlock(Properties properties) {
        super(properties);

        registerDefaultState(stateDefinition.any()
                .setValue(FACING, Direction.NORTH)
                .setValue(COLUMN, 0)
                .setValue(ROW, Po2Fence.HEIGHT - 1)
                .setValue(CONNECT_FRONT, false)
                .setValue(CONNECT_BACK, false)
                .setValue(TRIM, false)
        );
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.@NonNull Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(FACING, COLUMN, ROW, CONNECT_FRONT, CONNECT_BACK, TRIM);
    }

    private static int shapeIndex(int turns, int col, boolean bottomRow, boolean front, boolean back, boolean trim) {
        int index = turns * Po2Fence.WIDTH + col;
        index = index * 2 + (bottomRow ? 1 : 0);
        index = index * 2 + (front ? 1 : 0);
        index = index * 2 + (back ? 1 : 0);
        return index * 2 + (trim ? 1 : 0);
    }

    private static VoxelShape buildShape(int turns, int col, boolean bottomRow, boolean front, boolean back, boolean trim) {
        boolean leftEnd = col == 0;
        boolean rightEnd = col == Po2Fence.WIDTH - 1;

        double x1 = 0.0D;
        double x2 = 16.0D;
        if (trim && leftEnd) {x2 = MAX;}
        if (trim && rightEnd) {x1 = MIN;}

        VoxelShape shape = rotated(turns, x1, 0.0D, MIN, x2, 16.0D, MAX);

        if (front) {shape = Shapes.or(shape, rotated(turns, MIN, 0.0D, 0.0D, MAX, 16.0D, MIN));}
        if (back) {shape = Shapes.or(shape, rotated(turns, MIN, 0.0D, MAX, MAX, 16.0D, 16.0D));}

        if (bottomRow && leftEnd) {
            double from = trim ? MAX - Po2Fence.FOOT_LENGTH : 16.0D - Po2Fence.FOOT_LENGTH;
            double to = trim ? MAX : 16.0D;
            shape = Shapes.or(shape, rotated(turns, from, 0.0D, Po2Fence.FOOT_DEPTH_MIN, to, Po2Fence.FOOT_HEIGHT, Po2Fence.FOOT_DEPTH_MAX));
        }
        if (bottomRow && rightEnd) {
            double from = trim ? MIN : 0.0D;
            double to = trim ? MIN + Po2Fence.FOOT_LENGTH : Po2Fence.FOOT_LENGTH;
            shape = Shapes.or(shape, rotated(turns, from, 0.0D, Po2Fence.FOOT_DEPTH_MIN, to, Po2Fence.FOOT_HEIGHT, Po2Fence.FOOT_DEPTH_MAX));
        }
        return shape;
    }

    private static VoxelShape rotated(int turns, double x1, double y1, double z1, double x2, double y2, double z2) {
        for (int i = 0; i < turns; i++) {
            double nx1 = 16.0D - z2;
            double nx2 = 16.0D - z1;
            double nz1 = x1;
            double nz2 = x2;

            x1 = nx1;
            x2 = nx2;
            z1 = nz1;
            z2 = nz2;
        }
        return box(x1, y1, z1, x2, y2, z2);
    }

    @Override
    @NonNull
    protected VoxelShape getShape(@NonNull BlockState state, @NonNull BlockGetter level, @NonNull BlockPos pos, @NonNull CollisionContext context) {
        return SHAPES[shapeIndex(
                Po2Fence.quarterTurns(state.getValue(FACING)),
                state.getValue(COLUMN),
                state.getValue(ROW) == Po2Fence.HEIGHT - 1,
                state.getValue(CONNECT_FRONT),
                state.getValue(CONNECT_BACK),
                state.getValue(TRIM)
        )];
    }

    public static BlockPos cellPos(BlockPos fromPos, BlockState fromState, int col, int row) {
        Direction right = Po2Fence.rightOf(fromState.getValue(FACING));
        return fromPos.relative(right, col - fromState.getValue(COLUMN)).above(fromState.getValue(ROW) - row);
    }

    private static boolean connects(BlockState neighbor, Direction direction, int row) {
        if (!(neighbor.getBlock() instanceof Po2PanelBlock) || neighbor.getValue(ROW) != row) {return false;}

        Direction facing = neighbor.getValue(FACING);
        if (facing.getAxis() == direction.getAxis()) {return false;}

        Direction right = Po2Fence.rightOf(facing);
        int col = neighbor.getValue(COLUMN);

        return (direction == right && col == 0) || (direction == right.getOpposite() && col == Po2Fence.WIDTH - 1);
    }

    private static boolean isPanel(BlockState state, int row) {
        return state.getBlock() instanceof Po2PanelBlock && state.getValue(ROW) == row;
    }

    private static BlockState withConnections(LevelReader level, BlockPos pos, BlockState state) {
        Direction facing = state.getValue(FACING);
        int row = state.getValue(ROW);
        int col = state.getValue(COLUMN);

        boolean front = connects(level.getBlockState(pos.relative(facing)), facing, row);
        boolean back = connects(level.getBlockState(pos.relative(facing.getOpposite())), facing.getOpposite(), row);

        boolean trim = false;
        if ((front || back) && (col == 0 || col == Po2Fence.WIDTH - 1)) {
            Direction right = Po2Fence.rightOf(facing);
            Direction outer = col == 0 ? right.getOpposite() : right;

            trim = !isPanel(level.getBlockState(pos.relative(outer)), row);
        }
        return state.setValue(CONNECT_FRONT, front).setValue(CONNECT_BACK, back).setValue(TRIM, trim);
    }

    @Override
    public @Nullable BlockState getStateForPlacement(@NonNull BlockPlaceContext context) {
        Direction facing = context.getHorizontalDirection().getOpposite();
        int anchorColumn = context.isSecondaryUseActive() ? Po2Fence.WIDTH - 1 : 0;

        BlockState anchor = defaultBlockState()
                .setValue(FACING, facing)
                .setValue(COLUMN, anchorColumn)
                .setValue(ROW, Po2Fence.HEIGHT - 1);

        Level level = context.getLevel();
        BlockPos anchorPos = context.getClickedPos();

        for (int col = 0; col < Po2Fence.WIDTH; col++) {
            for (int row = 0; row < Po2Fence.HEIGHT; row++) {
                BlockPos p = cellPos(anchorPos, anchor, col, row);
                BlockState cell = anchor.setValue(COLUMN, col).setValue(ROW, row);

                if (level.isOutsideBuildHeight(p)) {return null;}
                if (!p.equals(anchorPos) && !level.getBlockState(p).canBeReplaced(context)) {return null;}
                if (!level.isUnobstructed(cell, p, CollisionContext.empty())) {return null;}
            }
        }
        return withConnections(level, anchorPos, anchor);
    }

    @Override
    public void setPlacedBy(@NonNull Level level, @NonNull BlockPos pos, @NonNull BlockState state, @Nullable LivingEntity placer, @NonNull ItemStack stack) {
        super.setPlacedBy(level, pos, state, placer, stack);
        if (level.isClientSide()) {return;}

        for (int col = 0; col < Po2Fence.WIDTH; col++) {
            for (int row = 0; row < Po2Fence.HEIGHT; row++) {
                if (col == state.getValue(COLUMN) && row == state.getValue(ROW)) {continue;}

                BlockPos p = cellPos(pos, state, col, row);
                BlockState cell = state.setValue(COLUMN, col).setValue(ROW, row);

                level.setBlock(p, withConnections(level, p, cell), Block.UPDATE_ALL);
            }
        }
    }

    @Override
    @NonNull
    protected BlockState updateShape(@NonNull BlockState state, @NonNull LevelReader level, @NonNull ScheduledTickAccess scheduledTickAccess, @NonNull BlockPos pos, @NonNull Direction direction, @NonNull BlockPos neighborPos, @NonNull BlockState neighborState, @NonNull RandomSource random) {
        if (!isIntact(state, direction, neighborState)) {scheduledTickAccess.scheduleTick(pos, this, 1);}
        if (direction.getAxis().isHorizontal()) {return withConnections(level, pos, state);}
        return super.updateShape(state, level, scheduledTickAccess, pos, direction, neighborPos, neighborState, random);
    }

    @Override
    protected void tick(@NonNull BlockState state, @NonNull ServerLevel level, @NonNull BlockPos pos, @NonNull RandomSource random) {
        Direction right = Po2Fence.rightOf(state.getValue(FACING));

        for (Direction direction : new Direction[]{right, right.getOpposite(), Direction.UP, Direction.DOWN}) {
            if (!isIntact(state, direction, level.getBlockState(pos.relative(direction)))) {
                collapse(level, pos, state);
                return;
            }
        }
    }

    private void collapse(ServerLevel level, BlockPos pos, BlockState state) {
        BlockPos lootPos = null;

        for (int col = 0; col < Po2Fence.WIDTH; col++) {
            for (int row = 0; row < Po2Fence.HEIGHT; row++) {
                BlockPos p = cellPos(pos, state, col, row);
                if (!isSameCell(level.getBlockState(p), state, col, row)) {continue;}

                if (col == 0 && row == Po2Fence.HEIGHT - 1) {
                    lootPos = p;
                } else {
                    level.setBlock(p, Blocks.AIR.defaultBlockState(), Block.UPDATE_ALL);
                }
            }
        }

        if (lootPos != null) {
            level.destroyBlock(lootPos, true);
        }
    }

    private boolean isSameCell(BlockState other, BlockState reference, int col, int row) {
        return other.is(this)
                && other.getValue(FACING) == reference.getValue(FACING)
                && other.getValue(COLUMN) == col
                && other.getValue(ROW) == row;
    }

    private static boolean isIntact(BlockState self, Direction direction, BlockState neighbor) {
        int col = self.getValue(COLUMN);
        int row = self.getValue(ROW);

        Direction right = Po2Fence.rightOf(self.getValue(FACING));

        int expectedCol = col;
        int expectedRow = row;

        if (direction == right) {expectedCol++;
        } else if (direction == right.getOpposite()) {expectedCol--;
        } else if (direction == Direction.UP) {expectedRow--;
        } else if (direction == Direction.DOWN) {expectedRow++;
        } else {return true;}

        if (expectedCol < 0 || expectedCol >= Po2Fence.WIDTH || expectedRow < 0 || expectedRow >= Po2Fence.HEIGHT) {return true;}

        return neighbor.getBlock() instanceof Po2PanelBlock
                && neighbor.getValue(FACING) == self.getValue(FACING)
                && neighbor.getValue(COLUMN) == expectedCol
                && neighbor.getValue(ROW) == expectedRow;
    }
}
