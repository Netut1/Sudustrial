package com.netut.sudustrial.tnt.block;

import com.netut.sudustrial.tnt.entity.CustomTntEntity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.StringRepresentable;

public interface TntVariant extends StringRepresentable {
    void explode(ServerLevel level, CustomTntEntity tnt);
}
