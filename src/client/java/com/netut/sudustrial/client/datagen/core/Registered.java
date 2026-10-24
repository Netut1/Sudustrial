package com.netut.sudustrial.client.datagen.core;

import java.util.Map;

public final class Registered {
    private Registered() {}

    public static <K, V> V require(Map<K, V> registry, K key) {
        V value = registry.get(key);
        if (value == null) {
            throw new IllegalStateException("Not registered (datagen): " + key);
        }
        return value;
    }
}
