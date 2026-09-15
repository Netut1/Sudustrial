package com.netut.sudustrial.block.nest;

import com.netut.sudustrial.entity.blockentity.nest.DragonNestBlockEntity;
import com.netut.sudustrial.register.ModBlockEntities;
import com.netut.sudustrial.register.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.redstone.Orientation;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NonNull;

import java.util.Map;

public class DragonNestBlock extends Block implements EntityBlock {
    private static final int TICK_INTERVAL = 20;

    // ЗАГЛУШКИ — подбирай под баланс. Сейчас: жёсткий минимум 20 мин, обычный диапазон 50-100 мин.
    public static final int HARD_MINIMUM_TICKS = 20 * 60 * 20;
    public static final int RANGE_MIN_TICKS = 20 * 60 * 30;
    public static final int RANGE_MAX_TICKS = 20 * 60 * 100; // верхняя граница не меняется никогда

    private static final Map<Block, Integer> HEAT_SOURCE_EFFECTIVE_MIN = Map.of(
            Blocks.CAMPFIRE, RANGE_MIN_TICKS,
            Blocks.SOUL_CAMPFIRE, 20 * 60 * 70,
            Blocks.MAGMA_BLOCK, 20 * 60 * 40,
            Blocks.LAVA, HARD_MINIMUM_TICKS,
            Blocks.TORCH, 30*20*80,
            Blocks.SOUL_TORCH, 20*60*90,
            Blocks.CRYING_OBSIDIAN, HARD_MINIMUM_TICKS
    );

    public static final VoxelShape SHAPE = Shapes.or(
            Block.box(0, 0, 0, 16, 3, 16),    // дно
            Block.box(0, 3, 0, 2, 10, 16),    // западная стенка
            Block.box(14, 3, 0, 16, 10, 16),  // восточная стенка
            Block.box(2, 3, 0, 14, 10, 2),    // северная стенка
            Block.box(2, 3, 14, 14, 10, 16)   // южная стенка
    );

    public DragonNestBlock(Properties properties) {
        super(properties);
    }

    @Override
    @NonNull
    protected VoxelShape getShape(@NonNull BlockState state, @NonNull BlockGetter level, @NonNull BlockPos pos, @NonNull CollisionContext context) {
        return SHAPE;
    }

    @Override
    protected void neighborChanged(@NonNull BlockState state, @NonNull Level level, @NonNull BlockPos pos, @NonNull Block neighborBlock, @Nullable Orientation orientation, boolean movedByPiston) {
        super.neighborChanged(state, level, pos, neighborBlock, orientation, movedByPiston);
        if (level.isClientSide()) return;

        if (!(level.getBlockEntity(pos) instanceof DragonNestBlockEntity blockEntity)) return;
        if (blockEntity.getTargetHatchTicks() == -1) return;

        if (!hasEggAbove(level, pos)) {
            blockEntity.resetIncubation();
        }
    }

    @Override
    protected void affectNeighborsAfterRemoval(@NonNull BlockState state, @NonNull ServerLevel level, @NonNull BlockPos pos, boolean movedByPiston) {
        if (hasEggAbove(level, pos)) {
            BlockPos eggPos = pos.above();
            level.removeBlock(eggPos, false);
            ItemEntity dropped = new ItemEntity(level, eggPos.getX() + 0.5D, eggPos.getY() + 0.2D, eggPos.getZ() + 0.5D, new ItemStack(Items.DRAGON_EGG));
            level.addFreshEntity(dropped);
        }
        super.affectNeighborsAfterRemoval(state, level, pos, movedByPiston);
    }

    private static boolean hasEggAbove(Level level, BlockPos nestPos) {
        return level.getBlockState(nestPos.above()).is(Blocks.DRAGON_EGG);
    }

    @Nullable
    private static Block getHeatSourceBelow(Level level, BlockPos nestPos) {
        BlockState belowState = level.getBlockState(nestPos.below());
        if (!belowState.is(ModTags.HEAT_SOURCES)) return null;
        return belowState.getBlock();
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, DragonNestBlockEntity blockEntity) {
        if (level.getGameTime() % TICK_INTERVAL != 0) return;
        if (!(level instanceof ServerLevel serverLevel)) return;

        if (!hasEggAbove(serverLevel, pos)) return;

        Block heatSource = getHeatSourceBelow(serverLevel, pos);
        if (heatSource == null) return;

        if (blockEntity.getTargetHatchTicks() == -1) {
            int effectiveMin = HEAT_SOURCE_EFFECTIVE_MIN.getOrDefault(heatSource, RANGE_MIN_TICKS);
            effectiveMin = Math.max(HARD_MINIMUM_TICKS, effectiveMin);
            int rolled = effectiveMin + serverLevel.getRandom().nextInt(RANGE_MAX_TICKS - effectiveMin + 1);
            blockEntity.setTargetHatchTicks(rolled);
        }

        int newProgress = blockEntity.getHeatProgress() + TICK_INTERVAL;
        blockEntity.setHeatProgress(newProgress);

        if (newProgress >= blockEntity.getTargetHatchTicks()) {
            hatch(serverLevel, pos, blockEntity);
        }
    }

    private static void hatch(ServerLevel level, BlockPos nestPos, DragonNestBlockEntity blockEntity) {
        BlockPos eggPos = nestPos.above();
        level.removeBlock(eggPos, false);
        blockEntity.resetIncubation();

        // TODO: заспавнить Джина на eggPos — как только будет готова его сущность.
    }

    @Override
    @Nullable
    public BlockEntity newBlockEntity(@NonNull BlockPos pos, @NonNull BlockState state) {
        return new DragonNestBlockEntity(pos, state);
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
        return createTickerHelper(blockEntityType, ModBlockEntities.DRAGON_NEST, DragonNestBlock::serverTick);
    }
}
