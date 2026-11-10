package com.netut.sudustrial.decorative.soviet_architecture.po_2;

import net.minecraft.core.Direction;

public class Po2Fence {
    public static final int WIDTH = 4;
    public static final int HEIGHT = 3;

    public static final double MIN = 6.0D;
    public static final double MAX = 10.0D;

    public static final double FOOT_HEIGHT = 4.0D;
    public static final double FOOT_LENGTH = 8.0D;
    public static final double FOOT_DEPTH_MIN = 2.0D;
    public static final double FOOT_DEPTH_MAX = 14.0D;

    private Po2Fence() {}

    public static Direction rightOf(Direction facing) {
        return facing.getCounterClockWise();
    }

    public static int quarterTurns(Direction facing) {
        return switch (facing) {
            case EAST -> 1;
            case SOUTH -> 2;
            case WEST -> 3;
            default -> 0;
        };
    }
}