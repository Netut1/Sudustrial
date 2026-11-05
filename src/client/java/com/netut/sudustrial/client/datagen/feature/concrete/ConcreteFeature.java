package com.netut.sudustrial.client.datagen.feature.concrete;

import com.netut.sudustrial.client.datagen.core.block.BlockSet;
import com.netut.sudustrial.client.datagen.feature.DefinitionFeature;

import java.util.List;

public final class ConcreteFeature extends DefinitionFeature {
    @Override
    protected List<BlockSet> blockSets() {
        return ConcreteSets.all();
    }
}
