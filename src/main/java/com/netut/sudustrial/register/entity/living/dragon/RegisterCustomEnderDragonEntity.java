package com.netut.sudustrial.register.entity.living.dragon;

import com.netut.sudustrial.Sudustrial;
import com.netut.sudustrial.dragon.entity.living.CustomEnderDragon;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;

public class RegisterCustomEnderDragonEntity {
    public static final ResourceKey<EntityType<?>> PLAYER_ENDER_DRAGON_KEY = ResourceKey.create(
            Registries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(Sudustrial.MOD_ID, "jean_dragon")
    );

    public static final EntityType<CustomEnderDragon> PLAYER_ENDER_DRAGON = EntityType.Builder.of(CustomEnderDragon::new, MobCategory.MISC)
            .fireImmune()
            .sized(16.0F, 8.0F)
            .clientTrackingRange(10)
            .build(PLAYER_ENDER_DRAGON_KEY);

    public static void register() {
        Registry.register(BuiltInRegistries.ENTITY_TYPE, PLAYER_ENDER_DRAGON_KEY, PLAYER_ENDER_DRAGON);
        FabricDefaultAttributeRegistry.register(PLAYER_ENDER_DRAGON, EnderDragon.createAttributes());
    }
}
