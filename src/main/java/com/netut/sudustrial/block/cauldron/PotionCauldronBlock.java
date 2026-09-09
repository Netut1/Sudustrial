package com.netut.sudustrial.block.cauldron;

import com.netut.sudustrial.entity.cauldron.PotionCauldronBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NonNull;

import java.util.List;
import java.util.Optional;

public class PotionCauldronBlock extends Block implements EntityBlock {
    public static final IntegerProperty EFFECT_COUNT = IntegerProperty.create("effect_count", 0, PotionCauldronBlockEntity.MAX_EFFECTS);

    public PotionCauldronBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(EFFECT_COUNT, 0));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.@NonNull Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(EFFECT_COUNT);
    }

    @Override
    @NonNull
    protected VoxelShape getShape(@NonNull BlockState state, @NonNull BlockGetter level, @NonNull BlockPos pos, @NonNull CollisionContext context) {
        return super.getShape(state, level, pos, context); // обычный кубический котёл — форму не трогаем
    }

    @Override
    @NonNull
    protected InteractionResult useItemOn(@NonNull ItemStack stack, @NonNull BlockState state, @NonNull Level level, @NonNull BlockPos pos, @NonNull Player player, @NonNull InteractionHand hand, @NonNull BlockHitResult hitResult) {
        if (!(level.getBlockEntity(pos) instanceof PotionCauldronBlockEntity blockEntity)) {
            return InteractionResult.PASS;
        }

        if (stack.is(Items.GLASS_BOTTLE)) {
            return tryExtract(stack, level, pos, player, blockEntity);
        }

        if (stack.is(Items.POTION)) {
            return tryPourIn(stack, level, pos, player, blockEntity);
        }

        return InteractionResult.PASS;
    }

    private InteractionResult tryPourIn(ItemStack stack, Level level, BlockPos pos, Player player, PotionCauldronBlockEntity blockEntity) {
        PotionContents contents = stack.get(DataComponents.POTION_CONTENTS);
        if (contents == null) return InteractionResult.PASS;

        List<MobEffectInstance> incoming = (List<MobEffectInstance>) contents.getAllEffects();
        if (incoming.isEmpty()) return InteractionResult.PASS; // вода/неуклюжее и подобное — не годится

        List<MobEffectInstance> mixed = blockEntity.getMixedEffects();
        boolean addedAnything = false;

        for (MobEffectInstance effect : incoming) {
            if (mixed.size() >= PotionCauldronBlockEntity.MAX_EFFECTS) break;
            if (blockEntity.hasEffectType(effect)) continue; // дубль по типу — пропускаем
            mixed.add(effect);
            addedAnything = true;
        }

        if (!addedAnything) {
            return InteractionResult.PASS; // либо всё дубли, либо уже некуда добавлять
        }

        if (!level.isClientSide()) {
            if (!player.isCreative()) {
                stack.shrink(1);
                ItemStack emptyBottle = new ItemStack(Items.GLASS_BOTTLE);
                if (!player.getInventory().add(emptyBottle)) {
                    player.drop(emptyBottle, false);
                }
            }
            level.setBlock(pos, level.getBlockState(pos).setValue(EFFECT_COUNT, mixed.size()), 3);
            blockEntity.setChanged();
            level.playSound(null, pos, SoundEvents.BREWING_STAND_BREW, SoundSource.BLOCKS, 1.0F, 1.0F);
        }
        return InteractionResult.SUCCESS;
    }

    private InteractionResult tryExtract(ItemStack stack, Level level, BlockPos pos, Player player, PotionCauldronBlockEntity blockEntity) {
        List<MobEffectInstance> mixed = blockEntity.getMixedEffects();
        if (mixed.isEmpty()) return InteractionResult.PASS;

        if (!level.isClientSide()) {
            ItemStack result = new ItemStack(Items.POTION);
            PotionContents resultContents = new PotionContents(
                    Optional.empty(),
                    Optional.of(0x8A2BE2), // цвет жидкости зелья - фиолетовый
                    List.copyOf(mixed),
                    Optional.of("sudustrial.mysterious_potion")
            );
            result.set(DataComponents.POTION_CONTENTS, resultContents);

            if (!player.isCreative()) {
                stack.shrink(1);
            }
            if (!player.getInventory().add(result)) {
                player.drop(result, false);
            }

            blockEntity.clear();
            level.setBlock(pos, level.getBlockState(pos).setValue(EFFECT_COUNT, 0), 3);
            level.playSound(null, pos, SoundEvents.BOTTLE_FILL, SoundSource.BLOCKS, 1.0F, 1.0F);
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    @Nullable
    public BlockEntity newBlockEntity(@NonNull BlockPos pos, @NonNull BlockState state) {
        return new PotionCauldronBlockEntity(pos, state);
    }
}
