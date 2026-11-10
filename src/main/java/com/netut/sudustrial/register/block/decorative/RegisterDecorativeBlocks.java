package com.netut.sudustrial.register.block.decorative;

import com.netut.sudustrial.register.block.decorative.concrete.RegisterConcreteBlocks;
import com.netut.sudustrial.register.block.decorative.po_2.RegisterPO_2Fence;

public class RegisterDecorativeBlocks {
    public static void register() {
        RegisterConcreteBlocks.register();
        RegisterPO_2Fence.register();
    }
}
