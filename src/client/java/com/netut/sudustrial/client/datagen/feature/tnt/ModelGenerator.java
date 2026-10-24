package com.netut.sudustrial.client.datagen.feature.tnt;

import com.netut.sudustrial.tnt.type.block.TntType;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;

import java.util.Optional;

import static com.netut.sudustrial.client.datagen.util.DatagenUtils.blockMaterial;
import static com.netut.sudustrial.client.datagen.util.DatagenUtils.id;

final class ModelGenerator {
    private static final TextureSlot OVERLAY = TextureSlot.create("overlay");

    private static final ModelTemplate TNT_TEMPLATE = new ModelTemplate(
            Optional.of(id("block/template_tnt")),
            Optional.empty(),
            TextureSlot.BOTTOM,
            TextureSlot.TOP,
            TextureSlot.SIDE
    );

    private static final ModelTemplate TNT_OVERLAY_TEMPLATE = new ModelTemplate(
            Optional.of(id("block/template_tnt_overlay")),
            Optional.empty(),
            TextureSlot.BOTTOM,
            TextureSlot.TOP,
            TextureSlot.SIDE,
            OVERLAY
    );

    private ModelGenerator() {}

    static void generate(BlockModelGenerators generator, TntCatalog catalog) {
        for (TntCatalog.Entry entry : catalog.entries()) {
            // PARTY — особый: свои текстуры и фиксированный tier.
            if (entry.variant() == TntType.PARTY) {
                buildTntModel(
                        generator, entry.block(),
                        "party_tnt_side",
                        "standard_tnt_top",
                        "standard_tnt_bottom",
                        10
                );
                continue;
            }

            buildTntModel(
                    generator,
                    entry.block(),
                    entry.base() + "_side",
                    entry.base() + "_top",
                    entry.base() + "_bottom",
                    entry.tier()
            );
        }
    }

    private static void buildTntModel(BlockModelGenerators generator, Block block, String sideTexture, String topTexture, String bottomTexture, int tier) {
        TextureMapping textures = new TextureMapping()
                .put(TextureSlot.SIDE, blockMaterial(sideTexture))
                .put(TextureSlot.TOP, blockMaterial(topTexture))
                .put(TextureSlot.BOTTOM, blockMaterial(bottomTexture));

        ModelTemplate template = TNT_TEMPLATE;

        if (tier >= 2) {
            template = TNT_OVERLAY_TEMPLATE;
            textures.put(OVERLAY, blockMaterial("overlay_tier_" + tier));
        }

        Identifier model = template.create(block, textures, generator.modelOutput);

        generator.blockStateOutput.accept(
                MultiVariantGenerator.dispatch(
                        block,
                        BlockModelGenerators.plainVariant(model)
                )
        );

        generator.registerSimpleItemModel(block, model);
    }
}
