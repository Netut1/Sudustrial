package com.netut.sudustrial.register.creative_tabs.tab;

import com.netut.sudustrial.Sudustrial;
import com.netut.sudustrial.register.block.potion_cauldron.RegisterPotionCauldron;
import com.netut.sudustrial.register.item.nuclear_antidote.RegisterNuclearAntidote;
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class RegisterPotionAndFoodTab {
    public static final ResourceKey<CreativeModeTab> SUDUSTRIAL_POTION_AND_FOOD_TAB_KEY = ResourceKey.create(
            Registries.CREATIVE_MODE_TAB,
            Identifier.fromNamespaceAndPath(Sudustrial.MOD_ID, "sudustrial_potion_and_food_tab")
    );

    public static final CreativeModeTab SUDUSTRIAL_POTION_AND_FOOD_TAB = FabricCreativeModeTab.builder()
            .icon(() -> new ItemStack(RegisterPotionCauldron.POTION_CAULDRON_ITEM))
            .title(Component.translatable("poison_and_food_tab.sudustrial"))
            .displayItems((displayParameters, output) -> {
                output.accept(RegisterNuclearAntidote.NUCLEAR_ANTIDOTE);
                output.accept(RegisterPotionCauldron.POTION_CAULDRON_ITEM);
            })
            .build();

    public static void register() {
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, SUDUSTRIAL_POTION_AND_FOOD_TAB_KEY, SUDUSTRIAL_POTION_AND_FOOD_TAB);
    }
}
