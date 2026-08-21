package com.netut.sudustrial.register;

import com.netut.sudustrial.Sudustrial;
import com.netut.sudustrial.block.tnt.type.*;
import com.netut.sudustrial.item.tnt.nuclear.CoreItemType;
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeTabs {
    public static final ResourceKey<CreativeModeTab> SUDUSTRIAL_TNT_TAB_KEY = ResourceKey.create(
            Registries.CREATIVE_MODE_TAB,
            Identifier.fromNamespaceAndPath(Sudustrial.MOD_ID, "sudustrial_tnt_tab")
    );

    public static final CreativeModeTab SUDUSTRIAL_TNT_TAB = FabricCreativeModeTab.builder()
            .icon(() -> new ItemStack(ModBlocks.TNT_ITEMS.get(TntType.STANDARD_TIER_2)))
            .title(Component.translatable("tnt_tab.sudustrial"))
            .displayItems((displayParameters, output) -> {
                for (TntType type : TntType.values()) {
                    output.accept(ModBlocks.TNT_ITEMS.get(type));
                }
                for (ElementalTntType type : ElementalTntType.values()) {
                    output.accept(ModBlocks.ELEMENTAL_TNT_ITEMS.get(type));
                }
                for (SpawnerTntType type : SpawnerTntType.values()) {
                    output.accept(ModBlocks.SPAWNER_TNT_ITEMS.get(type));
                }
                for (EffectTntType type : EffectTntType.values()) {
                    output.accept(ModBlocks.EFFECT_TNT_ITEMS.get(type));
                }
                for (MiningTntType type : MiningTntType.values()) {
                    output.accept(ModBlocks.MINING_TNT_ITEMS.get(type));
                }
                for (CoreItemType type : CoreItemType.values()) {
                    output.accept(ModItems.CORE_ITEMS.get(type));
                }
                for (NuclearTntType type : NuclearTntType.values()) {
                    output.accept(ModBlocks.NUCLEAR_TNT_ITEMS.get(type));
                }
                output.accept(ModBlocks.NUCLEAR_CORE_ITEM);
            })
            .build();

    public static void registerCreativeTabs() {
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, SUDUSTRIAL_TNT_TAB_KEY, SUDUSTRIAL_TNT_TAB);
    }
}
