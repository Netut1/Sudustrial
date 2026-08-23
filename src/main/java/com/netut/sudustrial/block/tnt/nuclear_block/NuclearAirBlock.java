package com.netut.sudustrial.block.tnt.nuclear_block;

import com.netut.sudustrial.register.ModBlocks;
import com.netut.sudustrial.register.ModMobEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jspecify.annotations.NonNull;

public class NuclearAirBlock extends Block {
    private static final int EFFECT_DURATION_TICKS = 200;
    private static final int REFRESH_THRESHOLD_TICKS = 40;

    public static final IntegerProperty DISTANCE = IntegerProperty.create("distance", 1, 8);
    private static final int MAX_DISTANCE = 8;

    public NuclearAirBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(DISTANCE, MAX_DISTANCE));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.@NonNull Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(DISTANCE);
    }

    @Override
    @NonNull
    protected VoxelShape getShape(@NonNull BlockState state, @NonNull BlockGetter level, @NonNull BlockPos pos, @NonNull CollisionContext context) {
        return Shapes.empty();
    }

    @Override
    protected void entityInside(@NonNull BlockState state, @NonNull Level level, @NonNull BlockPos pos, @NonNull Entity entity, @NonNull InsideBlockEffectApplier effectApplier, boolean isPrecise) {
        if (!level.isClientSide() && entity instanceof LivingEntity living) {
            MobEffectInstance current = living.getEffect(ModMobEffects.NUCLEAR_DECAY);
            if (current == null || current.getDuration() <= REFRESH_THRESHOLD_TICKS) {
                living.addEffect(new MobEffectInstance(ModMobEffects.NUCLEAR_DECAY, EFFECT_DURATION_TICKS, 0, false, true, true));
            }
        }
        super.entityInside(state, level, pos, entity, effectApplier, isPrecise);
    }

    @Override
    @NonNull
    protected BlockState updateShape(@NonNull BlockState state, @NonNull LevelReader level, @NonNull ScheduledTickAccess scheduledTickAccess, @NonNull BlockPos pos, @NonNull Direction direction, @NonNull BlockPos neighborPos, @NonNull BlockState neighborState, @NonNull RandomSource random) {
        int newDistance = computeDistance(level, pos);
        if (newDistance != state.getValue(DISTANCE)) {
            state = state.setValue(DISTANCE, newDistance);
        }
        if (newDistance >= MAX_DISTANCE) {
            scheduledTickAccess.scheduleTick(pos, this, 30 + random.nextInt(60));
        }
        return state;
    }

    @Override
    protected void tick(@NonNull BlockState state, @NonNull ServerLevel level, @NonNull BlockPos pos, @NonNull RandomSource random) {
        if (state.getValue(DISTANCE) >= MAX_DISTANCE) {
            level.removeBlock(pos, false);
        }
    }

    private int computeDistance(LevelReader level, BlockPos pos) {
        int best = MAX_DISTANCE;
        for (Direction dir : Direction.values()) {
            BlockPos neighborPos = pos.relative(dir);
            BlockState neighborState = level.getBlockState(neighborPos);

            int neighborDistance;
            if (neighborState.is(ModBlocks.NUCLEAR_CORE_BLOCK)) {
                neighborDistance = 0;
            } else if (neighborState.is(this)) {
                neighborDistance = neighborState.getValue(DISTANCE);
            } else {
                continue;
            }
            best = Math.min(best, neighborDistance + 1);
        }
        return best;
    }
}
