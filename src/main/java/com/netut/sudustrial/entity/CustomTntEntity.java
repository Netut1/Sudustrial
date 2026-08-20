package com.netut.sudustrial.entity;

import com.netut.sudustrial.action.Explodes;
import com.netut.sudustrial.block.tnt_type.*;
import com.netut.sudustrial.register.ModBlocks;
import com.netut.sudustrial.register.ModEntities;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NonNull;

public class CustomTntEntity extends PrimedTnt {
    private static final EntityDataAccessor<String> TNT_TYPE_DATA =
            SynchedEntityData.defineId(CustomTntEntity.class, EntityDataSerializers.STRING);
    private static final EntityDataAccessor<Byte> TNT_MODE_DATA = // 0: Standard, 1: Elemental, 2: Spawner 3: Effect 4: Mining 5: Nuclear
            SynchedEntityData.defineId(CustomTntEntity.class, EntityDataSerializers.BYTE);

    public CustomTntEntity(EntityType<? extends CustomTntEntity> entityType, Level level) {
        super(entityType, level);
    }

    public CustomTntEntity(Level level, double x, double y, double z, @Nullable LivingEntity owner, TntType type) {
        super(level, x, y, z, owner);
        this.setTntType(type);
    }
    public CustomTntEntity(Level level, double x, double y, double z, @Nullable LivingEntity owner, ElementalTntType type) {
        super(level, x, y, z, owner);
        this.setElementalType(type);
    }
    public CustomTntEntity(Level level, double x, double y, double z, @Nullable LivingEntity owner, SpawnerTntType type) {
        super(level, x, y, z, owner);
        this.setSpawnerType(type);
    }
    public CustomTntEntity(Level level, double x, double y, double z, @Nullable LivingEntity owner, EffectTntType type) {
        super(level, x, y, z, owner);
        this.setEffectType(type);
    }
    public CustomTntEntity(Level level, double x, double y, double z, @Nullable LivingEntity owner, MiningTntType type) {
        super(level, x, y, z, owner);
        this.setMiningType(type);
    }
    public CustomTntEntity(Level level, double x, double y, double z, @Nullable LivingEntity owner, NuclearTntType type) {
        super(level, x, y, z, owner);
        this.setNuclearType(type);
    }

    @Override
    public void tick() {
        int fuse = this.getFuse() - 1;
        this.setFuse(fuse);

        if (fuse <= 0){
            this.discard();
            if (!this.level().isClientSide()) {
                Explodes.explode(this);
            }
        } else {
            this.move(MoverType.SELF, this.getDeltaMovement());
            this.setDeltaMovement(this.getDeltaMovement().scale(0.98D));
            if (!this.onGround()) {
                this.setDeltaMovement(this.getDeltaMovement().add(0, -0.04D, 0));
            }
        }
    }

    @Override
    @NonNull
    public EntityType<?> getType() {
        return ModEntities.CUSTOM_TNT;
    }

    public void setTntType(TntType type) {
        this.entityData.set(TNT_MODE_DATA, (byte) 0);
        this.entityData.set(TNT_TYPE_DATA, type != null ? type.name() : TntType.STANDARD_TIER_2.name());
    }
    public void setElementalType(ElementalTntType type) {
        this.entityData.set(TNT_TYPE_DATA, type != null ? type.name() : ElementalTntType.FIRE_TIER_1.name());
        this.entityData.set(TNT_MODE_DATA, (byte) 1);
    }
    public void setSpawnerType(SpawnerTntType type) {
        this.entityData.set(TNT_TYPE_DATA, type != null ? type.name() : SpawnerTntType.ZOMBIE_TIER_1.name());
        this.entityData.set(TNT_MODE_DATA, (byte) 2);
    }
    public void setEffectType(EffectTntType type) {
        this.entityData.set(TNT_TYPE_DATA, type != null ? type.name() : EffectTntType.POISON_TIER_1.name());
        this.entityData.set(TNT_MODE_DATA, (byte) 3);
    }
    public void setMiningType(MiningTntType type) {
        this.entityData.set(TNT_TYPE_DATA, type != null ? type.name() : MiningTntType.SPHERE_TIER_1.name());
        this.entityData.set(TNT_MODE_DATA, (byte) 4);
    }
    public void setNuclearType(NuclearTntType type) {
        this.entityData.set(TNT_TYPE_DATA, type != null ? type.name() : NuclearTntType.NUCLEAR.name());
        this.entityData.set(TNT_MODE_DATA, (byte) 5);
    }

    public boolean isElemental() {
        return this.entityData.get(TNT_MODE_DATA) == 1;
    }
    public boolean isSpawner() {
        return this.entityData.get(TNT_MODE_DATA) == 2;
    }
    public boolean isEffect() {
        return this.entityData.get(TNT_MODE_DATA) == 3;
    }
    public boolean isMining() {
        return this.entityData.get(TNT_MODE_DATA) == 4;
    }
    public boolean isNuclear() {
        return this.entityData.get(TNT_MODE_DATA) == 5;
    }

    public TntType getTntType() {
        try {
            return TntType.valueOf(this.entityData.get(TNT_TYPE_DATA));
        } catch (Exception e) {
            return TntType.STANDARD_TIER_2;
        }
    }
    public ElementalTntType getElementalTntType() {
        try {
            return ElementalTntType.valueOf(this.entityData.get(TNT_TYPE_DATA));
        } catch (Exception e) {
            return ElementalTntType.FIRE_TIER_1;
        }
    }
    public SpawnerTntType getSpawnerTntType() {
        try {
            return SpawnerTntType.valueOf(this.entityData.get(TNT_TYPE_DATA));
        } catch (Exception e) {
            return SpawnerTntType.ZOMBIE_TIER_1;
        }
    }
    public EffectTntType getEffectTntType() {
        try {
            return EffectTntType.valueOf(this.entityData.get(TNT_TYPE_DATA));
        } catch (Exception e) {
            return EffectTntType.POISON_TIER_1;
        }
    }
    public MiningTntType getMiningTntType() {
        try {
            return MiningTntType.valueOf(this.entityData.get(TNT_TYPE_DATA));
        } catch (Exception e) {
            return MiningTntType.SPHERE_TIER_1;
        }
    }
    public NuclearTntType getNuclearTntType() {
        try {
            return NuclearTntType.valueOf(this.entityData.get(TNT_TYPE_DATA));
        } catch (Exception e) {
            return NuclearTntType.NUCLEAR;
        }
    }

    @Override
    @NonNull
    public BlockState getBlockState() {
        if (this.isMining()) {
            return ModBlocks.MINING_TNT_BLOCKS.get(this.getMiningTntType()).defaultBlockState();
        } else if (this.isSpawner()) {
            return ModBlocks.SPAWNER_TNT_BLOCKS.get(this.getSpawnerTntType()).defaultBlockState();
        } else if (this.isElemental()) {
            return ModBlocks.ELEMENTAL_TNT_BLOCKS.get(this.getElementalTntType()).defaultBlockState();
        } else if (this.isEffect()) {
            return ModBlocks.EFFECT_TNT_BLOCKS.get(this.getEffectTntType()).defaultBlockState();
        } else if (this.isNuclear()) {
            return ModBlocks.NUCLEAR_TNT_BLOCKS.get(this.getNuclearTntType()).defaultBlockState();
        }
        return ModBlocks.TNT_BLOCKS.get(this.getTntType()).defaultBlockState();
    }

    @Override
    protected void readAdditionalSaveData(@NonNull ValueInput input) {
        super.readAdditionalSaveData(input);
        byte mode = input.getByteOr("tnt_mode", (byte) 0);
        String typeName = input.getStringOr("tnt_type", "");

        if (mode == 5) {
            try { this.setNuclearType(NuclearTntType.valueOf(typeName)); } catch (Exception e) { this.setNuclearType(NuclearTntType.NUCLEAR); }
        } else if (mode == 4) {
            try { this.setMiningType(MiningTntType.valueOf(typeName)); } catch (Exception e) { this.setMiningType(MiningTntType.SPHERE_TIER_1); }
        } else if (mode == 3) {
            try { this.setEffectType(EffectTntType.valueOf(typeName)); } catch (Exception e) { this.setEffectType(EffectTntType.POISON_TIER_1); }
        } else if (mode == 2) {
            try { this.setSpawnerType(SpawnerTntType.valueOf(typeName)); } catch (Exception e) { this.setSpawnerType(SpawnerTntType.ZOMBIE_TIER_1); }
        } else if (mode == 1) {
            try { this.setElementalType(ElementalTntType.valueOf(typeName)); } catch (Exception e) { this.setElementalType(ElementalTntType.FIRE_TIER_1); }
        } else {
            try { this.setTntType(TntType.valueOf(typeName)); } catch (Exception e) { this.setTntType(TntType.STANDARD_TIER_2); }
        }
    }

    @Override
    protected void addAdditionalSaveData(@NonNull ValueOutput output) {
        super.addAdditionalSaveData(output);
        output.putByte("tnt_mode", this.entityData.get(TNT_MODE_DATA));
        output.putString("tnt_type", this.entityData.get(TNT_TYPE_DATA));
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.@NonNull Builder builder) {
        super.defineSynchedData(builder);
        builder.define(TNT_TYPE_DATA, TntType.STANDARD_TIER_2.name());
        builder.define(TNT_MODE_DATA, (byte) 0);
    }
}
