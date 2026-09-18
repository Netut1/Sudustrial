package com.netut.sudustrial.register.creative_tabs.tab;

import com.netut.sudustrial.Sudustrial;
import com.netut.sudustrial.tnt.type.block.*;
import com.netut.sudustrial.tnt.type.item.CoreItemType;
import com.netut.sudustrial.register.block.nuclear_block.RegisterNuclearCore;
import com.netut.sudustrial.register.block.tnt.*;
import com.netut.sudustrial.register.item.core.RegisterCore;
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class RegisterTntTab {
    public static final ResourceKey<CreativeModeTab> SUDUSTRIAL_TNT_TAB_KEY = ResourceKey.create(
            Registries.CREATIVE_MODE_TAB,
            Identifier.fromNamespaceAndPath(Sudustrial.MOD_ID, "sudustrial_tnt_tab")
    );

    public static final CreativeModeTab SUDUSTRIAL_TNT_TAB = FabricCreativeModeTab.builder()
            .icon(() -> new ItemStack(RegisterTnt.TNT_ITEMS.get(TntType.STANDARD_TIER_2)))
            .title(Component.translatable("tnt_tab.sudustrial"))
            .displayItems((displayParameters, output) -> {
                for (TntType type : TntType.values()) {
                    output.accept(RegisterTnt.TNT_ITEMS.get(type));
                }
                for (ElementalTntType type : ElementalTntType.values()) {
                    output.accept(RegisterElementalTnt.ELEMENTAL_TNT_ITEMS.get(type));
                }
                for (SpawnerTntType type : SpawnerTntType.values()) {
                    output.accept(RegisterSpawnerTnt.SPAWNER_TNT_ITEMS.get(type));
                }
                for (EffectTntType type : EffectTntType.values()) {
                    output.accept(RegisterEffectTnt.EFFECT_TNT_ITEMS.get(type));
                }
                for (MiningTntType type : MiningTntType.values()) {
                    output.accept(RegisterMiningTnt.MINING_TNT_ITEMS.get(type));
                }
                for (NuclearTntType type : NuclearTntType.values()) {
                    output.accept(RegisterNuclearTnt.NUCLEAR_TNT_ITEMS.get(type));
                }
                for (CoreItemType type : CoreItemType.values()) {
                    output.accept(RegisterCore.CORE_ITEMS.get(type));
                }
                output.accept(RegisterNuclearCore.NUCLEAR_CORE_ITEM);
            })
            .build();

    public static void register(){
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, SUDUSTRIAL_TNT_TAB_KEY, SUDUSTRIAL_TNT_TAB);
    }
}
