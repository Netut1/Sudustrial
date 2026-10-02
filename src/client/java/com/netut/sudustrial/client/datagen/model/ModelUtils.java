package com.netut.sudustrial.client.datagen.model;

import com.netut.sudustrial.Sudustrial;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.resources.Identifier;

public final class ModelUtils {

    private ModelUtils() {}

    public static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(Sudustrial.MOD_ID, path);
    }

    public static Material blockMaterial(String textureName) {
        return new Material(id("block/" + textureName));
    }

    public static int getTier(String name) {
        int marker = name.lastIndexOf("_tier_");
        if (marker == -1) {
            return 0;
        }

        try {
            return Integer.parseInt(name.substring(marker + 6));
        } catch (NumberFormatException ignored) {
            return 0;
        }
    }

    public static String getBaseName(String name) {
        int marker = name.lastIndexOf("_tier_");
        return marker == -1 ? name : name.substring(0, marker);
    }
}
