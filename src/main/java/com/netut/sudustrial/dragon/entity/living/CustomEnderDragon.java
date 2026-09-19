package com.netut.sudustrial.dragon.entity.living;

import com.netut.sudustrial.register.entity.living.dragon.RegisterCustomEnderDragonEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.boss.enderdragon.EnderDragonPart;
import net.minecraft.world.entity.boss.enderdragon.phases.DragonPhaseInstance;
import net.minecraft.world.entity.boss.enderdragon.phases.EnderDragonPhase;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NonNull;

public class CustomEnderDragon extends EnderDragon {
    private static final Component FIXED_NAME = Component.literal("Jean");
    private @Nullable BlockPos homePos;

    public CustomEnderDragon(EntityType<? extends EnderDragon> type, Level level) {
        super(type, level);
    }

    @Override
    @NonNull
    public EntityType<?> getType() {
        return RegisterCustomEnderDragonEntity.PLAYER_ENDER_DRAGON;
    }

    @Override
    @NonNull
    public Component getCustomName() {
        return FIXED_NAME;
    }

    @Override
    public boolean hasCustomName() {
        return true;
    }

    @Override
    public void setCustomName(@Nullable Component name) {
    }

    @Override
    public boolean hurt(@NonNull ServerLevel level, @NonNull EnderDragonPart part, @NonNull DamageSource source, float damage) {
        if (this.getPhaseManager().getCurrentPhase().getPhase() == EnderDragonPhase.DYING) {
            return false;
        }

        DragonPhaseInstance currentPhase = this.getPhaseManager().getCurrentPhase();
        damage = currentPhase.onHurt(source, damage);
        if (part != this.head) {
            damage = damage / 4.0F + Math.min(damage, 1.0F);
        }

        if (damage < 0.01F) {
            return false;
        }

        if (source.getEntity() instanceof Player || source.is(DamageTypeTags.ALWAYS_HURTS_ENDER_DRAGONS)) {
            this.reallyHurt(level, source, damage);
        }

        return true;
    }

    @NonNull
    public BlockPos getHomePosition() {
        if (homePos == null) homePos = this.blockPosition();
        return homePos;
    }

    public void setHomePosition(BlockPos pos) {
        this.homePos = pos;
    }

    @Override
    protected void addAdditionalSaveData(@NonNull ValueOutput output) {
        super.addAdditionalSaveData(output);
        output.store("home_pos", BlockPos.CODEC, getHomePosition());
    }

    @Override
    protected void readAdditionalSaveData(@NonNull ValueInput input) {
        super.readAdditionalSaveData(input);
        this.homePos = input.read("home_pos", BlockPos.CODEC).orElse(null);
    }
}
