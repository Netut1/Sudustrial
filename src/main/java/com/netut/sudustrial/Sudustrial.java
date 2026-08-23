package com.netut.sudustrial;

import com.netut.sudustrial.action.DelayedTaskScheduler;
import com.netut.sudustrial.action.tnt.explosion.optimization.nuclear.NuclearExplosionManager;
import com.netut.sudustrial.action.tnt.revival.nuclear.NuclearDecayCarryOver;
import com.netut.sudustrial.register.*;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Sudustrial implements ModInitializer {
	public static final String MOD_ID = "sudustrial";
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		ModBlocks.registerModBlocks();
		ModItems.registerModItems();
		ModMobEffects.registerModMobEffects();
		ModAttachments.registerModAttachments();
		ModBlockEntities.registerModBlockEntities();
		ModCreativeTabs.registerCreativeTabs();
		ModEntities.registerModEntities();
		NuclearExplosionManager.init();
		NuclearDecayCarryOver.init();
		DelayedTaskScheduler.init();
		ModSounds.registerModSounds();
		LOGGER.info("Hello Fabric world!");
	}


}
