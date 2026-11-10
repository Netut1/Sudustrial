package com.netut.sudustrial.client.datagen.core;

import java.util.Map;
import java.util.Objects;

public final class Registered {
    private Registered() {}

    public static <K, V> V require(Map<K, V> registry, K key) {
        Objects.requireNonNull(registry, "registry map");
        Objects.requireNonNull(key, "registry key");

        V value = registry.get(key);
        if (value == null) {
            throw new IllegalStateException("Not registered (datagen): " + describe(key)
                    + " - the Register* map has " + registry.size() + " entries; check that register() fills it for this key");
        }
        return value;
    }

    private static String describe(Object key) {
        if (key instanceof Enum<?> e) {
            return e.getDeclaringClass().getSimpleName() + "." + e.name();
        }
        return String.valueOf(key);
    }
}
