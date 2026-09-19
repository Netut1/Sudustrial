package com.netut.sudustrial.dragon.ai_phase;

import com.netut.sudustrial.dragon.entity.living.CustomEnderDragon;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.boss.enderdragon.phases.AbstractDragonPhaseInstance;
import net.minecraft.world.entity.boss.enderdragon.phases.EnderDragonPhase;
import com.netut.sudustrial.register.ai.ModDragonPhases;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class CustomEnderDragonIdlePhase extends AbstractDragonPhaseInstance {
    private static final double HORIZONTAL_RADIUS = 30.0D;
    private static final double UP_LIMIT = 15.0D;
    private static final double DOWN_LIMIT = 5.0D;
    private static final double REROLL_DISTANCE_SQ = 6.0D * 6.0D;

    private @Nullable Vec3 targetLocation;

    public CustomEnderDragonIdlePhase(EnderDragon dragon) {
        super(dragon);
    }

    @Override
    public void begin() {
        this.targetLocation = null;
    }

    @Override
    public void doServerTick(@NonNull ServerLevel level) {
        if (!(this.dragon instanceof CustomEnderDragon jean)) return;

        boolean needsNewTarget = this.targetLocation == null
                || this.dragon.distanceToSqr(this.targetLocation.x, this.targetLocation.y, this.targetLocation.z) < REROLL_DISTANCE_SQ;

        if (needsNewTarget) {
            this.targetLocation = rollNewTarget(jean, level.getRandom());
        }
    }

    private Vec3 rollNewTarget(CustomEnderDragon jean, RandomSource random) {
        BlockPos home = jean.getHomePosition();

        double angle = random.nextDouble() * Math.PI * 2.0D;
        double horizontalDist = random.nextDouble() * HORIZONTAL_RADIUS;
        double dx = Math.cos(angle) * horizontalDist;
        double dz = Math.sin(angle) * horizontalDist;
        double dy = -DOWN_LIMIT + random.nextDouble() * (DOWN_LIMIT + UP_LIMIT);

        return new Vec3(home.getX() + 0.5D + dx, home.getY() + dy, home.getZ() + 0.5D + dz);
    }

    @Override
    public boolean isSitting() {
        return false;
    }

    @Override
    public float getFlySpeed() {
        return 0.6F;
    }

    @Override
    public @Nullable Vec3 getFlyTargetLocation() {
        return this.targetLocation;
    }

    @Override
    public @NonNull EnderDragonPhase<CustomEnderDragonIdlePhase> getPhase() {
        return ModDragonPhases.JEAN_IDLE;
    }
}
