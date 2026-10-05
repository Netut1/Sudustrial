package com.netut.sudustrial.client.datagen.texture_block.concrete.generator;

import com.netut.sudustrial.register.block.texture.concrete.RegisterConcreteBlocks;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;

import static com.netut.sudustrial.client.datagen.model.ModelUtils.id;

public final class ConcreteItemGenerator {

    private ConcreteItemGenerator() {
    }

    public static void generate(BlockModelGenerators generator) {
        for (DyeColor color : DyeColor.values()) {
            generateItem(
                    generator,
                    RegisterConcreteBlocks.CONCRETE_STAIR_ITEM.get(color),
                    id("block/" + color.getName() + "_concrete_stairs")
            );

            generateItem(
                    generator,
                    RegisterConcreteBlocks.CONCRETE_SLAB_ITEM.get(color),
                    id("block/" + color.getName() + "_concrete_slab")
            );

            generateItem(
                    generator,
                    RegisterConcreteBlocks.CONCRETE_POWDER_STAIR_ITEM.get(color),
                    id("block/" + color.getName() + "_concrete_powder_stairs")
            );

            generateItem(
                    generator,
                    RegisterConcreteBlocks.CONCRETE_POWDER_SLAB_ITEM.get(color),
                    id("block/" + color.getName() + "_concrete_powder_slab")
            );
        }
    }

    private static void generateItem(
            BlockModelGenerators generator,
            Item item,
            Identifier model
    ) {
        generator.registerSimpleItemModel(item, model);
    }
}