package com.netut.sudustrial.action;

import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class GetVanillaColorfulBlocks {
    public static Block getVanillaConcrete(DyeColor color) {
        return switch (color) {
            case WHITE -> Blocks.CONCRETE.white();
            case ORANGE -> Blocks.CONCRETE.orange();
            case MAGENTA -> Blocks.CONCRETE.magenta();
            case LIGHT_BLUE -> Blocks.CONCRETE.lightBlue();
            case YELLOW -> Blocks.CONCRETE.yellow();
            case LIME -> Blocks.CONCRETE.lime();
            case PINK -> Blocks.CONCRETE.pink();
            case GRAY -> Blocks.CONCRETE.gray();
            case LIGHT_GRAY -> Blocks.CONCRETE.lightGray();
            case CYAN -> Blocks.CONCRETE.cyan();
            case PURPLE -> Blocks.CONCRETE.purple();
            case BLUE -> Blocks.CONCRETE.blue();
            case BROWN -> Blocks.CONCRETE.brown();
            case GREEN -> Blocks.CONCRETE.green();
            case RED -> Blocks.CONCRETE.red();
            case BLACK -> Blocks.CONCRETE.black();
        };
    }

    public static Block getVanillaConcretePowder(DyeColor color) {
        return switch (color) {
            case WHITE -> Blocks.CONCRETE_POWDER.white();
            case ORANGE -> Blocks.CONCRETE_POWDER.orange();
            case MAGENTA -> Blocks.CONCRETE_POWDER.magenta();
            case LIGHT_BLUE -> Blocks.CONCRETE_POWDER.lightBlue();
            case YELLOW -> Blocks.CONCRETE_POWDER.yellow();
            case LIME -> Blocks.CONCRETE_POWDER.lime();
            case PINK -> Blocks.CONCRETE_POWDER.pink();
            case GRAY -> Blocks.CONCRETE_POWDER.gray();
            case LIGHT_GRAY -> Blocks.CONCRETE_POWDER.lightGray();
            case CYAN -> Blocks.CONCRETE_POWDER.cyan();
            case PURPLE -> Blocks.CONCRETE_POWDER.purple();
            case BLUE -> Blocks.CONCRETE_POWDER.blue();
            case BROWN -> Blocks.CONCRETE_POWDER.brown();
            case GREEN -> Blocks.CONCRETE_POWDER.green();
            case RED -> Blocks.CONCRETE_POWDER.red();
            case BLACK -> Blocks.CONCRETE_POWDER.black();
        };
    }
}
