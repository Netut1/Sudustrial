package com.netut.sudustrial.block.tnt_atomic_blocks;

import com.netut.sudustrial.register.ModMobEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jspecify.annotations.NonNull;

public class NuclearAirBlock extends Block {
    public NuclearAirBlock(Properties properties) {
        super(properties);
    }

    @Override
    @NonNull
    protected VoxelShape getShape(@NonNull BlockState state, @NonNull BlockGetter level, @NonNull BlockPos pos, @NonNull CollisionContext context) {
        return Shapes.empty();
    }

    @Override
    protected void entityInside(@NonNull BlockState state, @NonNull Level level, @NonNull BlockPos pos, @NonNull Entity entity, @NonNull InsideBlockEffectApplier effectApplier, boolean isPrecise) {
        if (!level.isClientSide() && entity instanceof LivingEntity living) {
            living.addEffect(new MobEffectInstance(ModMobEffects.NUCLEAR_DECAY, 60, 0, false, true, true));
        }
        super.entityInside(state, level, pos, entity, effectApplier, isPrecise);
    }
}
