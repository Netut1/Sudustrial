package com.netut.sudustrial.client.datagen.feature;

import com.netut.sudustrial.client.datagen.feature.concrete.ConcreteFeature;
import com.netut.sudustrial.client.datagen.feature.nuclear.NuclearAntidoteFeature;
import com.netut.sudustrial.client.datagen.feature.nuclear.NuclearFeature;
import com.netut.sudustrial.client.datagen.feature.po_2.Po2Feature;
import com.netut.sudustrial.client.datagen.feature.potion_cauldron.PotionCauldronFeature;
import com.netut.sudustrial.client.datagen.feature.tnt.TntFeature;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class Features {
    private Features() {}

    public static final List<DatagenFeature> ALL = listOf(
            new PotionCauldronFeature(),
            new NuclearAntidoteFeature(),
            new Po2Feature(),
            new NuclearFeature(),
            new TntFeature(),
            new ConcreteFeature()
    );

    private static List<DatagenFeature> listOf(DatagenFeature... features) {
        Set<Class<?>> seen = new HashSet<>();
        for (DatagenFeature feature : features) {
            if (!seen.add(feature.getClass())) {
                throw new IllegalStateException("Feature is listed twice: " + feature.getClass().getName());
            }
        }
        return List.of(features);
    }
}
