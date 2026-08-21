package com.netut.sudustrial.block.tnt.state_block;

import com.netut.sudustrial.block.tnt.type.ElementalTntType;
import com.netut.sudustrial.entity.tnt.CustomTntEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.redstone.Orientation;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NonNull;

public class ElementalTntBlock extends Block {
    private final ElementalTntType type;

    public ElementalTntBlock(ElementalTntType type, Properties properties) {
        super(properties);
        this.type = type;
    }

    public ElementalTntType getType() {
        return type;
    }

    public void primeTnt(Level level, BlockPos pos, @Nullable LivingEntity igniter) {
        if (!level.isClientSide()) {
            CustomTntEntity tntEntity = new CustomTntEntity(
                    level,
                    pos.getX() + 0.5D,
                    pos.getY(),
                    pos.getZ() + 0.5D,
                    igniter,
                    this.type
            );
            level.addFreshEntity(tntEntity);
            level.playSound(null, tntEntity.getX(), tntEntity.getY(), tntEntity.getZ(), SoundEvents.TNT_PRIMED, SoundSource.BLOCKS, 1.0F, 1.0F);
        }
    }

    @Override
    @NonNull
    protected InteractionResult useItemOn(ItemStack stack, @NonNull BlockState state, @NonNull Level level, @NonNull BlockPos pos, @NonNull Player player, @NonNull InteractionHand hand, @NonNull BlockHitResult hitResult) {
        if (!stack.is(Items.FLINT_AND_STEEL) && !stack.is(Items.FIRE_CHARGE)) {
            return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
        } else {
            this.primeTnt(level, pos, player);
            level.setBlock(pos, Blocks.AIR.defaultBlockState(), 11);
            if (!player.isCreative()) {
                if (stack.is(Items.FLINT_AND_STEEL)) {
                    EquipmentSlot slot = hand == InteractionHand.MAIN_HAND ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND;
                    stack.hurtAndBreak(1, player, slot);
                } else {
                    stack.shrink(1);
                }
            }
            return InteractionResult.SUCCESS;
        }
    }

    @Override
    protected void neighborChanged(@NonNull BlockState state, Level level, @NonNull BlockPos pos, @NonNull Block block, @Nullable Orientation orientation, boolean movedByPiston) {
        if (level.hasNeighborSignal(pos)) {
            this.primeTnt(level, pos, null);
            level.setBlock(pos, Blocks.AIR.defaultBlockState(), 11);
        }
    }
}
