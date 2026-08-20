package com.netut.sudustrial.block.tnt_atomic_blocks;

import com.netut.sudustrial.register.ModBlockEntities;
import com.netut.sudustrial.register.ModMobEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.AABB;
import org.jspecify.annotations.NonNull;

public class NuclearCoreBlockEntity extends BlockEntity {
    private static final int DEFAULT_RADIUS = 101;
    private static final int TICK_INTERVAL = 10; // раз в полсекунды, не каждый тик

    private int radius = DEFAULT_RADIUS;

    public NuclearCoreBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.NUCLEAR_CORE, pos, state);
    }

    public void setRadius(int radius) {
        this.radius = radius;
        setChanged();
    }

    public int getRadius() {
        return radius;
    }

    @Override
    protected void saveAdditional(@NonNull ValueOutput output) {
        super.saveAdditional(output);
        output.putInt("radius", radius);
    }

    @Override
    protected void loadAdditional(@NonNull ValueInput input) {
        super.loadAdditional(input);
        radius = input.getIntOr("radius", DEFAULT_RADIUS);
    }

    // Как у маяка: пока блок существует и чанк загружен, эффект накладывается НАПРЯМУЮ
    // на всех живых существ в радиусе — никаких сущностей-облаков. Собственные частицы
    // вокруг задетых существ появляются автоматически (встроенное поведение любого MobEffectInstance).
    public static void serverTick(Level level, BlockPos pos, BlockState state, NuclearCoreBlockEntity blockEntity) {
        if (!(level instanceof ServerLevel serverLevel)) return;
        if (serverLevel.getGameTime() % TICK_INTERVAL != 0) return; // не каждый тик — реже дешевле

        int r = blockEntity.radius;
        double centerX = pos.getX() + 0.5D;
        double centerY = pos.getY() + 0.5D;
        double centerZ = pos.getZ() + 0.5D;

        AABB area = new AABB(pos).inflate(r);
        long rSq = (long) r * r;

        for (LivingEntity target : serverLevel.getEntitiesOfClass(LivingEntity.class, area)) {
            double dx = target.getX() - centerX;
            double dy = target.getY() - centerY;
            double dz = target.getZ() - centerZ;
            if (dx * dx + dy * dy + dz * dz <= rSq) {
                target.addEffect(new MobEffectInstance(ModMobEffects.NUCLEAR_DECAY, 60, 0, false, true, true));
            }
        }
    }
}
