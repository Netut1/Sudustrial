package com.netut.sudustrial.client.datagen;

import com.netut.sudustrial.Sudustrial;
import com.netut.sudustrial.register.block.nuclear_block.*;
import com.netut.sudustrial.register.block.tnt.*;
import com.netut.sudustrial.register.item.core.RegisterCore;
import com.netut.sudustrial.register.item.nuclear_antidote.RegisterNuclearAntidote;
import com.netut.sudustrial.tnt.type.block.*;
import com.netut.sudustrial.potion_cauldron.block.PotionCauldronBlock;
import com.netut.sudustrial.register.block.potion_cauldron.RegisterPotionCauldron;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import org.jspecify.annotations.NonNull;

import java.util.Optional;

public class SudustrialModelProvider extends FabricModelProvider {

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

    public SudustrialModelProvider(FabricPackOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(@NonNull BlockModelGenerators generator) {
        generateTnt(generator);

        generateElementalTnt(generator);
        generateSpawnerTnt(generator);
        generateEffectTnt(generator);
        generateMiningTnt(generator);
        generateNuclearTnt(generator);

        generateNuclearAir(generator);
        generateNuclearCore(generator);

        generatePotionCauldron(generator);
    }

    @Override
    public void generateItemModels(@NonNull ItemModelGenerators generator) {
        generateTntItems(generator);
        generateCoreItems(generator);
        /* Обычные flat item. */
        generator.generateFlatItem(
                RegisterNuclearAntidote.NUCLEAR_ANTIDOTE,
                ModelTemplates.FLAT_ITEM
        );
        generator.generateFlatItem(
                RegisterPotionCauldron.POTION_CAULDRON_ITEM,
                ModelTemplates.FLAT_ITEM
        );
    }

    private void generateTnt(BlockModelGenerators generator) {
        for (TntType type : TntType.values()) {
            Block block = RegisterTnt.TNT_BLOCKS.get(type);

            if (block == null) {
                throw new IllegalStateException(
                        "No registered TNT block for type: " + type
                );
            }

            String name = type.getSerializedName();
            int tier = getTier(name);
            if (type == TntType.PARTY) {
                tier = 10;

                TextureMapping textures = new TextureMapping()
                        .put(
                                TextureSlot.SIDE,
                                blockMaterial("party_tnt_side")
                        )
                        .put(
                                TextureSlot.TOP,
                                blockMaterial("standard_tnt_top")
                        )
                        .put(
                                TextureSlot.BOTTOM,
                                blockMaterial("standard_tnt_bottom")
                        )
                        .put(
                                OVERLAY,
                                blockMaterial("overlay_tier_10")
                        );

                Identifier model = TNT_OVERLAY_TEMPLATE.create(
                        block,
                        textures,
                        generator.modelOutput
                );

                generator.blockStateOutput.accept(
                        MultiVariantGenerator.dispatch(
                                block,
                                BlockModelGenerators.plainVariant(model)
                        )
                );

                generator.registerSimpleItemModel(block, model);
                continue;
            }

            String baseName = getBaseName(name);

            TextureMapping textures = new TextureMapping()
                    .put(
                            TextureSlot.SIDE,
                            blockMaterial(baseName + "_side")
                    )
                    .put(
                            TextureSlot.TOP,
                            blockMaterial(baseName + "_top")
                    )
                    .put(
                            TextureSlot.BOTTOM,
                            blockMaterial(baseName + "_bottom")
                    );

            ModelTemplate template = TNT_TEMPLATE;

            if (tier >= 2) {
                template = TNT_OVERLAY_TEMPLATE;

                textures.put(
                        OVERLAY,
                        blockMaterial("overlay_tier_" + tier)
                );
            }

            Identifier model = template.create(block, textures, generator.modelOutput);
            generator.blockStateOutput.accept(MultiVariantGenerator.dispatch(block, BlockModelGenerators.plainVariant(model)));
            generator.registerSimpleItemModel(block, model);
        }
    }

    private void generateElementalTnt(BlockModelGenerators generator) {
        for (ElementalTntType type : ElementalTntType.values()) {
            generateTntVariant(
                    generator,
                    RegisterElementalTnt.ELEMENTAL_TNT_BLOCKS.get(type),
                    type.getSerializedName()
            );
        }
    }

    private void generateSpawnerTnt(BlockModelGenerators generator) {
        for (SpawnerTntType type : SpawnerTntType.values()) {
            generateTntVariant(
                    generator,
                    RegisterSpawnerTnt.SPAWNER_TNT_BLOCKS.get(type),
                    type.getSerializedName()
            );
        }
    }

    private void generateEffectTnt(BlockModelGenerators generator) {
        for (EffectTntType type : EffectTntType.values()) {
            generateTntVariant(
                    generator,
                    RegisterEffectTnt.EFFECT_TNT_BLOCKS.get(type),
                    type.getSerializedName()
            );
        }
    }

    private void generateMiningTnt(BlockModelGenerators generator) {
        for (MiningTntType type : MiningTntType.values()) {
            generateTntVariant(
                    generator,
                    RegisterMiningTnt.MINING_TNT_BLOCKS.get(type),
                    type.getSerializedName()
            );
        }
    }

    private void generateNuclearTnt(BlockModelGenerators generator) {
        for (NuclearTntType type : NuclearTntType.values()) {
            generateTntVariant(
                    generator,
                    RegisterNuclearTnt.NUCLEAR_TNT_BLOCKS.get(type),
                    type.getSerializedName()
            );
        }
    }

    private void generateTntVariant(BlockModelGenerators generator, Block block, String name) {
        if (block == null) {
            throw new IllegalStateException(
                    "No registered TNT block for: " + name
            );
        }
        int tier = getTier(name);
        String baseName = getBaseName(name);

        TextureMapping textures = new TextureMapping()
                .put(
                        TextureSlot.SIDE,
                        blockMaterial(baseName + "_side")
                )
                .put(
                        TextureSlot.TOP,
                        blockMaterial(baseName + "_top")
                )
                .put(
                        TextureSlot.BOTTOM,
                        blockMaterial(baseName + "_bottom")
                );

        ModelTemplate template = TNT_TEMPLATE;

        if (tier >= 2) {
            template = TNT_OVERLAY_TEMPLATE;

            textures.put(
                    OVERLAY,
                    blockMaterial("overlay_tier_" + tier)
            );
        }

        Identifier model = template.create(block, textures, generator.modelOutput);
        generator.blockStateOutput.accept(MultiVariantGenerator.dispatch(block, BlockModelGenerators.plainVariant(model)));
        generator.registerSimpleItemModel(block, model);
    }

    private void generateTntItems(ItemModelGenerators generator) {
    }

    private void generateCoreItems(ItemModelGenerators generator) {
        for (Item item : RegisterCore.CORE_ITEMS.values()) {
            generator.generateFlatItem(
                    item,
                    ModelTemplates.FLAT_ITEM
            );
        }
    }

    private void generateNuclearAir(BlockModelGenerators generator) {
        Identifier model = id("block/nuclear_air");

        generator.blockStateOutput.accept(
                MultiVariantGenerator.dispatch(
                        RegisterNuclearAir.NUCLEAR_AIR,
                        BlockModelGenerators.plainVariant(model)
                )
        );
    }

    private void generateNuclearCore(BlockModelGenerators generator) {
        Identifier model = id("block/nuclear_core");

        generator.blockStateOutput.accept(
                MultiVariantGenerator.dispatch(
                        RegisterNuclearCore.NUCLEAR_CORE_BLOCK,
                        BlockModelGenerators.plainVariant(model)
                )
        );

        generator.registerSimpleItemModel(
                RegisterNuclearCore.NUCLEAR_CORE_BLOCK,
                model
        );
    }

    private void generatePotionCauldron(BlockModelGenerators generator) {
        generator.blockStateOutput.accept(
                MultiVariantGenerator.dispatch(RegisterPotionCauldron.POTION_CAULDRON_BLOCK)
                        .with(
                                PropertyDispatch.initial(PotionCauldronBlock.EFFECT_COUNT)
                                        .generate(count ->
                                                BlockModelGenerators.plainVariant(
                                                        id("block/potion_cauldron_" + count)
                                                )
                                        )
                        )
        );
    }

    private static int getTier(String name) {
        int marker = name.lastIndexOf("_tier_");

        if (marker == -1) {
            return 0;
        }

        String number = name.substring(marker + 6);

        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException ignored) {
            return 0;
        }
    }

    private static String getBaseName(String name) {
        int marker = name.lastIndexOf("_tier_");

        if (marker == -1) {
            return name;
        }

        return name.substring(0, marker);
    }

    private static Material blockMaterial(String textureName) {
        return new Material(
                id("block/" + textureName)
        );
    }

    private static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(
                Sudustrial.MOD_ID,
                path
        );
    }

    @Override
    public @NonNull String getName() {
        return "SudustrialModelProvider";
    }
}
