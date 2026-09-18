package com.netut.sudustrial.dragon.entity.block;

import com.netut.sudustrial.register.entity.block.dragon_nest.RegisterDragonNestEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jspecify.annotations.NonNull;

public class DragonNestBlockEntity extends BlockEntity {
    private int heatProgress = 0;
    private int targetHatchTicks = -1;

    public DragonNestBlockEntity(BlockPos pos, BlockState state) {
        super(RegisterDragonNestEntity.DRAGON_NEST, pos, state);
    }

    public int getHeatProgress() { return heatProgress; }
    public void setHeatProgress(int heatProgress) { this.heatProgress = heatProgress; setChanged(); }

    public int getTargetHatchTicks() { return targetHatchTicks; }
    public void setTargetHatchTicks(int targetHatchTicks) { this.targetHatchTicks = targetHatchTicks; setChanged(); }

    public void resetIncubation() {
        this.heatProgress = 0;
        this.targetHatchTicks = -1;
        setChanged();
    }

    @Override
    protected void saveAdditional(@NonNull ValueOutput output) {
        super.saveAdditional(output);
        output.putInt("heat_progress", heatProgress);
        output.putInt("target_hatch_ticks", targetHatchTicks);
    }

    @Override
    protected void loadAdditional(@NonNull ValueInput input) {
        super.loadAdditional(input);
        heatProgress = input.getIntOr("heat_progress", 0);
        targetHatchTicks = input.getIntOr("target_hatch_ticks", -1);
    }
}
