package com.netut.sudustrial.register.creative_tabs.tab;

import com.netut.sudustrial.Sudustrial;
import com.netut.sudustrial.register.block.decorative.po_2.RegisterPO_2Fence;
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class RegisterDecorativeTab {
    public static final ResourceKey<CreativeModeTab> DECORATIVE_TAB_KEY = ResourceKey.create(
            Registries.CREATIVE_MODE_TAB,
            Identifier.fromNamespaceAndPath(Sudustrial.MOD_ID, "decorative_tab")
    );

    public static final CreativeModeTab DECORATIVE_TAB = FabricCreativeModeTab.builder()
            .icon(() -> new ItemStack(RegisterPO_2Fence.PO_2_PANEL_ITEM))
            .title(Component.translatable("soviet_architecture_tab.sudustrial"))
            .displayItems((displayParameters, output) -> {
                output.accept(RegisterPO_2Fence.PO_2_PANEL_ITEM);
            })
            .build();

    public static void register() {
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, DECORATIVE_TAB_KEY, DECORATIVE_TAB);
    }
}
