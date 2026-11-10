package com.netut.sudustrial.tnt.entity;

import com.netut.sudustrial.register.block.tnt.RegisterTnt;
import com.netut.sudustrial.register.entity.block.tnt.RegisterTntEntity;
import com.netut.sudustrial.tnt.action.explosion.Explodes;
import com.netut.sudustrial.tnt.type.block.TntType;
import com.netut.sudustrial.tnt.block.TntVariant;
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
    private static final String DEFAULT_ID = TntType.STANDARD_TIER_2.getSerializedName();

    private static final EntityDataAccessor<String> TNT_ID =
            SynchedEntityData.defineId(CustomTntEntity.class, EntityDataSerializers.STRING);

    public CustomTntEntity(EntityType<? extends CustomTntEntity> entityType, Level level) {
        super(entityType, level);
    }

    public CustomTntEntity(Level level, double x, double y, double z, @Nullable LivingEntity owner, TntVariant variant) {
        super(level, x, y, z, owner);
        this.setVariant(variant);
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
        return RegisterTntEntity.CUSTOM_TNT;
    }

    public TntVariant getVariant() {
        TntVariant variant = RegisterTnt.byId(this.entityData.get(TNT_ID));
        return variant != null ? variant : TntType.STANDARD_TIER_2;
    }

    public void setVariant(TntVariant variant) {
        this.entityData.set(TNT_ID, variant.getSerializedName());
    }

    @Override
    @NonNull
    public BlockState getBlockState() {
        return RegisterTnt.blockOf(this.getVariant()).defaultBlockState();
    }

    @Override
    protected void readAdditionalSaveData(@NonNull ValueInput input) {
        super.readAdditionalSaveData(input);
        TntVariant variant = RegisterTnt.byId(input.getStringOr("tnt_type", DEFAULT_ID));
        this.setVariant(variant != null ? variant : TntType.STANDARD_TIER_2);
    }

    @Override
    protected void addAdditionalSaveData(@NonNull ValueOutput output) {
        super.addAdditionalSaveData(output);
        output.putString("tnt_type", this.entityData.get(TNT_ID));
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.@NonNull Builder builder) {
        super.defineSynchedData(builder);
        builder.define(TNT_ID, DEFAULT_ID);
    }
}
