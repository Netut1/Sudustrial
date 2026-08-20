package com.netut.sudustrial.action;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NuclearExplosionManager {
    private static final List<NuclearExplosionTask> ACTIVE_TASKS = new ArrayList<>();
    private static boolean registered = false;

    public static void init() {
        if (registered) return;
        registered = true;

        ServerTickEvents.END_SERVER_TICK.register(server -> {
            if (ACTIVE_TASKS.isEmpty()) return;

            Iterator<NuclearExplosionTask> iterator = ACTIVE_TASKS.iterator();
            while (iterator.hasNext()) {
                if (iterator.next().tick()) {
                    iterator.remove();
                }
            }
        });
    }

    public static void start(ServerLevel level, BlockPos center, int radius) {
        ACTIVE_TASKS.add(new NuclearExplosionTask(level, center, radius));
    }
}
