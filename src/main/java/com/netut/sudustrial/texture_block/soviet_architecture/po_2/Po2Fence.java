package com.netut.sudustrial.texture_block.soviet_architecture.po_2;

import net.minecraft.core.Direction;

public class Po2Fence {
    public static final int WIDTH = 4;
    public static final int HEIGHT = 3;

    public static final double MIN = 6.0D;
    public static final double MAX = 10.0D;

    private Po2Fence() {}

    public static Direction rightOf(Direction facing) {
        return facing.getCounterClockWise();
    }
}
