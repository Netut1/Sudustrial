package com.netut.sudustrial.block.concrete;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import org.jspecify.annotations.NonNull;

public class ConcretePowderSlabBlock extends SlabBlock {
    private final Block concreteSlab;

    public ConcretePowderSlabBlock(Block concreteSlab, Properties properties) {
        super(properties);
        this.concreteSlab = concreteSlab;
    }

    private static boolean touchesLiquid(BlockGetter level, BlockPos pos, BlockState state) {
        if (state.hasProperty(BlockStateProperties.WATERLOGGED) && state.getValue(BlockStateProperties.WATERLOGGED)) {
            return true;
        }

        for (Direction direction : Direction.values()) {
            if (direction != Direction.DOWN) {
                BlockPos adjacentPos = pos.relative(direction);
                FluidState fluidState = level.getFluidState(adjacentPos);
                if (fluidState.is(Fluids.WATER) || fluidState.is(Fluids.FLOWING_WATER)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockGetter level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        BlockState state = super.getStateForPlacement(context);

        if (state != null && touchesLiquid(level, pos, state)) {
            return copySlabState(state, this.concreteSlab.defaultBlockState());
        }

        return state;
    }

    @Override
    public BlockState updateShape(@NonNull BlockState state, @NonNull LevelReader level, @NonNull ScheduledTickAccess ticks, @NonNull BlockPos pos, @NonNull Direction directionToNeighbour, @NonNull BlockPos neighbourPos, @NonNull BlockState neighbourState, @NonNull RandomSource random) {
        if (touchesLiquid(level, pos, state)) {
            return copySlabState(state, this.concreteSlab.defaultBlockState());
        }
        return super.updateShape(state, level, ticks, pos, directionToNeighbour, neighbourPos, neighbourState, random);
    }

    // Сохраняем положение полублока (верхний/нижний/двойной) и водонаполненность
    private BlockState copySlabState(BlockState from, BlockState to) {
        return to.setValue(TYPE, from.getValue(TYPE))
                .setValue(WATERLOGGED, from.getValue(WATERLOGGED));
    }
}
