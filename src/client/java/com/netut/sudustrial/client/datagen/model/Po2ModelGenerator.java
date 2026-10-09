package com.netut.sudustrial.client.datagen.model;

import com.netut.sudustrial.Sudustrial;
import com.netut.sudustrial.register.block.texture.po_2.RegisterPO_2Fence;
import com.netut.sudustrial.texture_block.soviet_architecture.po_2.Po2Fence;
import com.netut.sudustrial.texture_block.soviet_architecture.po_2.Po2PanelBlock;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.MultiPartGenerator;
import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.core.Direction;
import net.minecraft.resources.Identifier;

import java.util.Optional;

public final class Po2ModelGenerator {
    private static final String SIDE_TEXTURE = "po-2_side_feet";

    private static final TextureSlot FRONT = TextureSlot.create("front");
    private static final TextureSlot SIDE = TextureSlot.create("side");

    private static final ModelTemplate PLATE_TEMPLATE = plateTemplate("block/template_po2_panel");

    private static final ModelTemplate PLATE_TRIM_EAST_TEMPLATE = plateTemplate("block/template_po2_panel_trim_east");
    private static final ModelTemplate PLATE_TRIM_WEST_TEMPLATE = plateTemplate("block/template_po2_panel_trim_west");

    private static final ModelTemplate STUB_TEMPLATE = plateTemplate("block/template_po2_stub");
    private static final ModelTemplate FOOT_TEMPLATE = footTemplate("block/template_po2_foot");
    private static final ModelTemplate FOOT_TRIM_TEMPLATE = footTemplate("block/template_po2_foot_trim");

    private Po2ModelGenerator() {}

    public static void generate(BlockModelGenerators generator) {
        createModels(generator);

        var block = RegisterPO_2Fence.PO_2_PANEL_BLOCK;
        MultiPartGenerator multipart = MultiPartGenerator.multiPart(block);

        addPlates(multipart);
        addStubs(multipart);
        addFeet(multipart);

        generator.blockStateOutput.accept(multipart);
        generator.registerSimpleItemModel(block, panelModel("m", 1, false));
    }


    private static void createModels(BlockModelGenerators generator) {
        for (int row = 0; row < Po2Fence.HEIGHT; row++) {
            TextureMapping left = mapping(frontTexture("0", row));
            TextureMapping middle = mapping(frontTexture("m", row));
            TextureMapping right = mapping(frontTexture("3", row));

            PLATE_TEMPLATE.create(panelModel("0", row, false), left, generator.modelOutput);
            PLATE_TRIM_EAST_TEMPLATE.create(panelModel("0", row, true), left, generator.modelOutput);

            PLATE_TEMPLATE.create(panelModel("m", row, false), middle, generator.modelOutput);
            STUB_TEMPLATE.create(stubModel(row), middle, generator.modelOutput);

            PLATE_TEMPLATE.create(panelModel("3", row, false), right, generator.modelOutput);
            PLATE_TRIM_WEST_TEMPLATE.create(panelModel("3", row, true), right, generator.modelOutput);
        }

        FOOT_TEMPLATE.create(footModel(false), sideMapping(), generator.modelOutput);
        FOOT_TRIM_TEMPLATE.create(footModel(true), sideMapping(), generator.modelOutput);
    }


    private static void addPlates(MultiPartGenerator multipart) {
        for (Direction facing : Direction.Plane.HORIZONTAL) {
            int turn = Po2Fence.quarterTurns(facing) * 90;

            for (int col = 0; col < Po2Fence.WIDTH; col++) {
                String type = columnType(col);
                boolean end = col == 0 || col == Po2Fence.WIDTH - 1;

                for (int row = 0; row < Po2Fence.HEIGHT; row++) {
                    if (!end) {
                        multipart.with(
                                BlockModelGenerators.condition()
                                        .term(Po2PanelBlock.FACING, facing)
                                        .term(Po2PanelBlock.COLUMN, col)
                                        .term(Po2PanelBlock.ROW, row),
                                rotated(BlockModelGenerators.plainVariant(panelModel(type, row, false)), turn)
                        );
                        continue;
                    }

                    for (boolean trim : new boolean[]{false, true}) {
                        multipart.with(
                                BlockModelGenerators.condition()
                                        .term(Po2PanelBlock.FACING, facing)
                                        .term(Po2PanelBlock.COLUMN, col)
                                        .term(Po2PanelBlock.ROW, row)
                                        .term(Po2PanelBlock.TRIM, trim),
                                rotated(BlockModelGenerators.plainVariant(panelModel(type, row, trim)), turn)
                        );
                    }
                }
            }
        }
    }

    private static void addStubs(MultiPartGenerator multipart) {
        for (Direction facing : Direction.Plane.HORIZONTAL) {
            int turn = Po2Fence.quarterTurns(facing) * 90;

            for (int row = 0; row < Po2Fence.HEIGHT; row++) {
                multipart.with(
                        BlockModelGenerators.condition()
                                .term(Po2PanelBlock.FACING, facing)
                                .term(Po2PanelBlock.ROW, row)
                                .term(Po2PanelBlock.CONNECT_FRONT, true),
                        rotated(BlockModelGenerators.plainVariant(stubModel(row)), turn)
                );
                multipart.with(
                        BlockModelGenerators.condition()
                                .term(Po2PanelBlock.FACING, facing)
                                .term(Po2PanelBlock.ROW, row)
                                .term(Po2PanelBlock.CONNECT_BACK, true),
                        rotated(BlockModelGenerators.plainVariant(stubModel(row)), turn + 180)
                );
            }
        }
    }

    private static void addFeet(MultiPartGenerator multipart) {
        for (Direction facing : Direction.Plane.HORIZONTAL) {
            int turn = Po2Fence.quarterTurns(facing) * 90;

            for (int col : new int[]{0, Po2Fence.WIDTH - 1}) {
                int extra = col == 0 ? 0 : 180;

                for (boolean trim : new boolean[]{false, true}) {
                    multipart.with(
                            BlockModelGenerators.condition()
                                    .term(Po2PanelBlock.FACING, facing)
                                    .term(Po2PanelBlock.COLUMN, col)
                                    .term(Po2PanelBlock.ROW, Po2Fence.HEIGHT - 1)
                                    .term(Po2PanelBlock.TRIM, trim),
                            rotated(BlockModelGenerators.plainVariant(footModel(trim)), turn + extra)
                    );
                }
            }
        }
    }

    private static MultiVariant rotated(MultiVariant variant, int degrees) {
        return switch (((degrees % 360) + 360) % 360) {
            case 90 -> variant.with(BlockModelGenerators.Y_ROT_90);
            case 180 -> variant.with(BlockModelGenerators.Y_ROT_180);
            case 270 -> variant.with(BlockModelGenerators.Y_ROT_270);
            default -> variant;
        };
    }

    private static String columnType(int column) {
        return switch (column) {
            case 0 -> "0";
            case 1, 2 -> "m";
            case 3 -> "3";
            default -> throw new IllegalArgumentException("Invalid PO-2 column: " + column);
        };
    }

    private static Identifier panelModel(String type, int row, boolean trim) {
        return id("block/po_2_panel_" + type + "_" + row + (trim ? "_trim" : ""));
    }

    private static Identifier stubModel(int row) {return id("block/po_2_stub_" + row);}

    private static Identifier footModel(boolean trim) {return id("block/po_2_foot" + (trim ? "_trim" : ""));}

    private static String frontTexture(String type, int row) {
        int textureRow = Po2Fence.HEIGHT - 1 - row;
        return switch (type) {
            case "0" -> "po-2_0" + textureRow;
            case "3" -> "po-2_3" + textureRow;
            default -> "po-2_1" + textureRow + "-2" + textureRow;
        };
    }

    private static TextureMapping mapping(String texture) {
        return new TextureMapping()
                .put(FRONT, material(texture))
                .put(SIDE, material(SIDE_TEXTURE))
                .put(TextureSlot.PARTICLE, material(SIDE_TEXTURE));
    }

    private static TextureMapping sideMapping() {
        return new TextureMapping()
                .put(SIDE, material(SIDE_TEXTURE))
                .put(TextureSlot.PARTICLE, material(SIDE_TEXTURE));
    }

    private static ModelTemplate plateTemplate(String path) {
        return new ModelTemplate(Optional.of(id(path)), Optional.empty(), FRONT, SIDE, TextureSlot.PARTICLE);
    }

    private static ModelTemplate footTemplate(String path) {
        return new ModelTemplate(Optional.of(id(path)), Optional.empty(), SIDE, TextureSlot.PARTICLE);
    }

    private static net.minecraft.client.resources.model.sprite.Material material(String path) {
        return new net.minecraft.client.resources.model.sprite.Material(id("block/" + path));
    }

    private static Identifier id(String path) {return Identifier.fromNamespaceAndPath(Sudustrial.MOD_ID, path);}
}
