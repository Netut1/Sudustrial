package com.netut.sudustrial.action;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.level.ServerLevel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DelayedTaskScheduler {
    private record Entry(ServerLevel level, long fireAtGameTime, Runnable task) {}

    private static final List<Entry> PENDING = new ArrayList<>();
    private static boolean registered = false;

    public static void init() {
        if (registered) return;
        registered = true;

        ServerTickEvents.END_SERVER_TICK.register(server -> {
            if (PENDING.isEmpty()) return;

            // Сначала только собираем, что выполнить, и удаляем это.
            List<Entry> due = new ArrayList<>();
            Iterator<Entry> iterator = PENDING.iterator();
            while (iterator.hasNext()) {
                Entry entry = iterator.next();
                if (entry.level().getGameTime() >= entry.fireAtGameTime()) {
                    due.add(entry);
                    iterator.remove();
                }
            }
            // Запускаем уже отдельно
            for (Entry entry : due) {
                entry.task().run();
            }
        });
    }

    public static void schedule(ServerLevel level, int delayTicks, Runnable task) {
        PENDING.add(new Entry(level, level.getGameTime() + delayTicks, task));
    }
}
