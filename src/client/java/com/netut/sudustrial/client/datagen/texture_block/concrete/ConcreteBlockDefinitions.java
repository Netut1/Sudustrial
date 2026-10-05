package com.netut.sudustrial.client.datagen.texture_block.concrete;

import com.netut.sudustrial.client.datagen.texture_block.type.BlockDefinition;
import com.netut.sudustrial.client.datagen.texture_block.type.BlockModelType;
import com.netut.sudustrial.client.datagen.texture_block.type.BlockTextures;
import net.minecraft.world.item.DyeColor;

import java.util.ArrayList;
import java.util.List;

public final class ConcreteBlockDefinitions {

    private ConcreteBlockDefinitions() {}

    public static List<BlockDefinition> concrete() {
        List<BlockDefinition> definitions = new ArrayList<>();

        for (DyeColor color : DyeColor.values()) {
            String colorName = color.getName();
            String concreteTexture = "minecraft:block/" + colorName + "_concrete";
            String powderTexture = "minecraft:block/" + colorName + "_concrete_powder";

            definitions.add(stairs(colorName + "_concrete", concreteTexture));
            definitions.add(slab(colorName + "_concrete", concreteTexture));
            definitions.add(stairs(colorName + "_concrete_powder", powderTexture));
            definitions.add(slab(colorName + "_concrete_powder", powderTexture));
        }
        return List.copyOf(definitions);
    }

    private static BlockDefinition stairs(String baseName, String texture) {
        return new BlockDefinition(baseName + "_stairs", BlockModelType.STAIRS, sameTexture(texture));
    }

    private static BlockDefinition slab(String baseName, String texture) {
        return new BlockDefinition(baseName + "_slab", BlockModelType.SLAB, sameTexture(texture), "minecraft:block/" + baseName);
    }

    private static BlockTextures sameTexture(String texture) {
        return new BlockTextures(texture, texture, texture, texture, texture, texture, texture);
    }
}