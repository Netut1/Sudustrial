package com.netut.sudustrial.register.entity.block.tnt;

import com.netut.sudustrial.Sudustrial;
import com.netut.sudustrial.tnt.entity.CustomTntEntity;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public class RegisterTntEntity {
    public static final ResourceKey<EntityType<?>> CUSTOM_TNT_KEY = ResourceKey.create(
            Registries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(Sudustrial.MOD_ID, "custom_tnt")
    );

    public static final EntityType<CustomTntEntity> CUSTOM_TNT = EntityType.Builder.<CustomTntEntity>of(CustomTntEntity::new, MobCategory.MISC)
            .fireImmune()
            .sized(0.98F, 0.98F)
            .clientTrackingRange(10)
            .updateInterval(10)
            .build(CUSTOM_TNT_KEY);

    public static void register() {
        Registry.register(BuiltInRegistries.ENTITY_TYPE, CUSTOM_TNT_KEY, CUSTOM_TNT);
    }
}
