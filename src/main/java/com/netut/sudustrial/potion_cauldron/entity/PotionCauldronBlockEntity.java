package com.netut.sudustrial.potion_cauldron.entity;

import com.netut.sudustrial.register.entity.block.potion_cauldron.RegisterPotionCauldronEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jspecify.annotations.NonNull;

import java.util.ArrayList;
import java.util.List;

public class PotionCauldronBlockEntity extends BlockEntity {
    public static final int MAX_EFFECTS = 4;

    private final List<MobEffectInstance> mixedEffects = new ArrayList<>();

    public PotionCauldronBlockEntity(BlockPos pos, BlockState state) {
        super(RegisterPotionCauldronEntity.POTION_CAULDRON, pos, state);
    }

    public List<MobEffectInstance> getMixedEffects() {
        return mixedEffects;
    }

    public boolean hasEffectType(MobEffectInstance candidate) {
        for (MobEffectInstance existing : mixedEffects) {
            if (existing.getEffect().equals(candidate.getEffect())) {
                return true;
            }
        }
        return false;
    }

    public void clear() {
        mixedEffects.clear();
        setChanged();
    }

    @Override
    protected void saveAdditional(@NonNull ValueOutput output) {
        super.saveAdditional(output);
        output.store("mixed_effects", MobEffectInstance.CODEC.listOf(), mixedEffects);
    }

    @Override
    protected void loadAdditional(@NonNull ValueInput input) {
        super.loadAdditional(input);
        mixedEffects.clear();
        mixedEffects.addAll(input.read("mixed_effects", MobEffectInstance.CODEC.listOf()).orElse(List.of()));
    }
}
