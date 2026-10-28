package com.netut.sudustrial.client.datagen.core;

import com.netut.sudustrial.client.datagen.feature.concrete.ConcreteFeature;
import com.netut.sudustrial.client.datagen.feature.tnt.TntFeature;
import com.netut.sudustrial.client.datagen.feature.tnt.nuclear.NuclearFeature;
import com.netut.sudustrial.client.datagen.feature.tnt.nuclear.antidote.NuclearAntidoteFeature;
import com.netut.sudustrial.client.datagen.feature.po_2.Po2Feature;
import com.netut.sudustrial.client.datagen.feature.potion_cauldron.PotionCauldronFeature;

import java.util.List;

public final class Features {
    private Features() {}

    public static final List<DatagenFeature> ALL = List.of(
            new PotionCauldronFeature(),
            new NuclearAntidoteFeature(),
            new Po2Feature(),
            new NuclearFeature(),
            new TntFeature(),
            new ConcreteFeature()
    );
}
