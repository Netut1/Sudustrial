package com.netut.sudustrial.register.concrete_register;

import com.netut.sudustrial.Sudustrial;
import com.netut.sudustrial.action.GetVanillaColorfulBlocks;
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

import java.util.HashMap;
import java.util.Map;

// Всё, что касается бетонных ступеней/полублоков.
// В 26.3 это станет ванильной фичей — чтобы вырезать, достаточно удалить:
//   - этот файл
//   - block/concrete/ConcretePowderSlabBlock.java
//   - block/concrete/ConcretePowderStairBlock.java
//   - RegistersSlab.java
//   - RegistersStair.java
//   - action/GetVanillaColorfulBlocks.java
//   - одну строку вызова в Sudustrial.onInitialize()
//   - ресурсы: blockstates/models/lang/recipe/loot_table для *_concrete_*
public class ModConcreteBlocks {
    public static final Map<DyeColor, Block> CONCRETE_STAIR = new HashMap<>();
    public static final Map<DyeColor, Item> CONCRETE_STAIR_ITEM = new HashMap<>();
    public static final Map<DyeColor, Block> CONCRETE_SLAB = new HashMap<>();
    public static final Map<DyeColor, Item> CONCRETE_SLAB_ITEM = new HashMap<>();

    public static final Map<DyeColor, Block> CONCRETE_POWDER_STAIR = new HashMap<>();
    public static final Map<DyeColor, Item> CONCRETE_POWDER_STAIR_ITEM = new HashMap<>();
    public static final Map<DyeColor, Block> CONCRETE_POWDER_SLAB = new HashMap<>();
    public static final Map<DyeColor, Item> CONCRETE_POWDER_SLAB_ITEM = new HashMap<>();

    public static final ResourceKey<CreativeModeTab> SUDUSTRIAL_CONCRETE_TAB_KEY = ResourceKey.create(
            Registries.CREATIVE_MODE_TAB,
            Identifier.fromNamespaceAndPath(Sudustrial.MOD_ID, "sudustrial_concrete_tab")
    );

    public static final CreativeModeTab SUDUSTRIAL_CONCRETE_TAB = FabricCreativeModeTab.builder()
            .icon(() -> new ItemStack(CONCRETE_STAIR.get(DyeColor.WHITE)))
            .title(Component.translatable("concrete_tab.sudustrial"))
            .displayItems((displayParameters, output) -> {
                for (DyeColor color : DyeColor.values()) {
                    if (CONCRETE_STAIR.containsKey(color)) {
                        output.accept(CONCRETE_STAIR.get(color));
                    }
                }
                for (DyeColor color : DyeColor.values()) {
                    if (CONCRETE_POWDER_STAIR.containsKey(color)) {
                        output.accept(CONCRETE_POWDER_STAIR.get(color));
                    }
                }

                // Выводим все полублоки второй стопкой
                for (DyeColor color : DyeColor.values()) {
                    if (CONCRETE_SLAB.containsKey(color)) {
                        output.accept(CONCRETE_SLAB.get(color));
                    }
                }
                for (DyeColor color : DyeColor.values()) {
                    if (CONCRETE_POWDER_SLAB.containsKey(color)) {
                        output.accept(CONCRETE_POWDER_SLAB.get(color));
                    }
                }
            })
            .build();

    public static void registerModConcreteBlocks() {
        for (DyeColor color : DyeColor.values()) {
            String colorName = color.getName();
            Block baseConcreteBlock = GetVanillaColorfulBlocks.getVanillaConcrete(color);
            RegistersStair.registerStair(
                    colorName + "_concrete_stairs",
                    baseConcreteBlock,
                    color,
                    CONCRETE_STAIR,
                    CONCRETE_STAIR_ITEM
            );
            RegistersSlab.registerSlab(
                    colorName + "_concrete_slab",
                    baseConcreteBlock,
                    color,
                    CONCRETE_SLAB,
                    CONCRETE_SLAB_ITEM
            );
        }
        for (DyeColor color : DyeColor.values()) {
            String colorName = color.getName();
            Block basePowderBlock = GetVanillaColorfulBlocks.getVanillaConcretePowder(color);

            Block concreteStair = CONCRETE_STAIR.get(color);
            Block concreteSlab = CONCRETE_SLAB.get(color);

            RegistersStair.registerPowderStair(
                    colorName + "_concrete_powder_stairs",
                    basePowderBlock,
                    concreteStair,
                    color,
                    CONCRETE_POWDER_STAIR,
                    CONCRETE_POWDER_STAIR_ITEM
            );
            RegistersSlab.registerPowderSlab(
                    colorName + "_concrete_powder_slab",
                    basePowderBlock,
                    concreteSlab,
                    color,
                    CONCRETE_POWDER_SLAB,
                    CONCRETE_POWDER_SLAB_ITEM
            );
        }

        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, SUDUSTRIAL_CONCRETE_TAB_KEY, SUDUSTRIAL_CONCRETE_TAB);
    }
}
