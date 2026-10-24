package com.netut.sudustrial.client.datagen.feature.tnt;

import com.netut.sudustrial.client.datagen.core.Registered;
import com.netut.sudustrial.register.block.tnt.RegisterEffectTnt;
import com.netut.sudustrial.register.block.tnt.RegisterElementalTnt;
import com.netut.sudustrial.register.block.tnt.RegisterMiningTnt;
import com.netut.sudustrial.register.block.tnt.RegisterNuclearTnt;
import com.netut.sudustrial.register.block.tnt.RegisterSpawnerTnt;
import com.netut.sudustrial.register.block.tnt.RegisterTnt;
import com.netut.sudustrial.tnt.block.TntVariant;
import com.netut.sudustrial.tnt.type.block.EffectTntType;
import com.netut.sudustrial.tnt.type.block.ElementalTntType;
import com.netut.sudustrial.tnt.type.block.MiningTntType;
import com.netut.sudustrial.tnt.type.block.NuclearTntType;
import com.netut.sudustrial.tnt.type.block.SpawnerTntType;
import com.netut.sudustrial.tnt.type.block.TntType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

final class TntCatalog {
    private static final String TIER_MARKER = "_tier_";

    record Entry(TntVariant variant, Block block, Item item, String base, int tier) {

        static Entry of(TntVariant variant, Block block, Item item) {
            String name = variant.getSerializedName();
            int marker = name.lastIndexOf(TIER_MARKER);
            if (marker == -1) {
                return new Entry(variant, block, item, name, 0);
            }

            String tierPart = name.substring(marker + TIER_MARKER.length());
            try {
                return new Entry(variant, block, item, name.substring(0, marker), Integer.parseInt(tierPart));
            } catch (NumberFormatException e) {
                throw new IllegalStateException("Bad TNT tier in name: " + name, e);
            }
        }
    }

    private final List<Entry> entries;
    private final Map<TntVariant, Item> itemByVariant = new HashMap<>();
    private final Map<String, Map<Integer, Item>> itemByBaseAndTier = new HashMap<>();

    private TntCatalog(List<Entry> entries) {
        this.entries = entries;

        for (Entry entry : entries) {
            itemByVariant.put(entry.variant(), entry.item());

            Item previous = itemByBaseAndTier
                    .computeIfAbsent(entry.base(), base -> new HashMap<>())
                    .put(entry.tier(), entry.item());
            if (previous != null) {
                throw new IllegalStateException("Duplicate TNT: " + entry.base() + " tier " + entry.tier());
            }
        }
    }

    static TntCatalog load() {
        List<Entry> entries = new ArrayList<>();

        collect(entries, TntType.values(),          RegisterTnt.TNT_BLOCKS,                    RegisterTnt.TNT_ITEMS);
        collect(entries, ElementalTntType.values(), RegisterElementalTnt.ELEMENTAL_TNT_BLOCKS, RegisterElementalTnt.ELEMENTAL_TNT_ITEMS);
        collect(entries, SpawnerTntType.values(),   RegisterSpawnerTnt.SPAWNER_TNT_BLOCKS,     RegisterSpawnerTnt.SPAWNER_TNT_ITEMS);
        collect(entries, EffectTntType.values(),    RegisterEffectTnt.EFFECT_TNT_BLOCKS,       RegisterEffectTnt.EFFECT_TNT_ITEMS);
        collect(entries, MiningTntType.values(),    RegisterMiningTnt.MINING_TNT_BLOCKS,       RegisterMiningTnt.MINING_TNT_ITEMS);
        collect(entries, NuclearTntType.values(),   RegisterNuclearTnt.NUCLEAR_TNT_BLOCKS,     RegisterNuclearTnt.NUCLEAR_TNT_ITEMS);

        return new TntCatalog(List.copyOf(entries));
    }

    private static <K extends TntVariant> void collect(List<Entry> out, K[] keys, Map<K, Block> blocks, Map<K, Item> items) {
        for (K key : keys) {
            out.add(Entry.of(key, Registered.require(blocks, key), Registered.require(items, key)));
        }
    }

    List<Entry> entries() {
        return entries;
    }

    Item item(TntVariant variant) {
        return Registered.require(itemByVariant, variant);
    }

    Item item(String base, int tier) {
        Map<Integer, Item> tiers = itemByBaseAndTier.get(base);
        Item item = tiers == null ? null : tiers.get(tier);
        if (item == null) {
            throw new IllegalStateException("No TNT registered: " + base + " tier " + tier);
        }
        return item;
    }

    Set<String> bases() {
        return itemByBaseAndTier.keySet();
    }
}
