package com.netut.sudustrial.client;

import com.netut.sudustrial.client.datagen.*;
import com.netut.sudustrial.client.datagen.TextureBlockProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import org.jspecify.annotations.NonNull;

public class SudustrialDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(@NonNull FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(SudustrialModelProvider::new);
		pack.addProvider(SudustrialLootTableProvider::new);
		pack.addProvider(TextureBlockProvider::new);
		pack.addProvider(SudustrialRecipeProvider::new);
	}
}
