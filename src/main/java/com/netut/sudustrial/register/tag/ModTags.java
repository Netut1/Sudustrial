package com.netut.sudustrial.register.tag;

import com.netut.sudustrial.Sudustrial;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static final TagKey<Block> HEAT_SOURCES = TagKey.create(
            Registries.BLOCK,
            Identifier.fromNamespaceAndPath(Sudustrial.MOD_ID, "heat_sources")
    );
}
