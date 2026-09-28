package com.netut.sudustrial;

import com.netut.sudustrial.action.DelayedTaskScheduler;
import com.netut.sudustrial.dragon.persistence.CustomEnderDragonPersistence;
import com.netut.sudustrial.tnt.action.explosion.optimization.NuclearExplosionManager;
import com.netut.sudustrial.tnt.action.NuclearDecayCarryOver;
import com.netut.sudustrial.register.attachment.ModAttachments;
import com.netut.sudustrial.register.block.ModBlocks;
import com.netut.sudustrial.register.block.concrete.ModConcreteBlocks;
import com.netut.sudustrial.register.creative_tabs.ModCreativeTabs;
import com.netut.sudustrial.register.effect.ModEffects;
import com.netut.sudustrial.register.entity.ModEntities;
import com.netut.sudustrial.register.item.ModItems;
import com.netut.sudustrial.register.sound.ModSounds;
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
		// !!!!! Blocks → Items → Effects → Entities → CreativeTabs !!!!!
		ModBlocks.register();
		ModConcreteBlocks.register();
		ModItems.register();
		ModEffects.register();
		ModAttachments.register();
		ModEntities.register();
		ModCreativeTabs.register();
		NuclearExplosionManager.init();
		NuclearDecayCarryOver.init();
		DelayedTaskScheduler.init();
		CustomEnderDragonPersistence.init();
		ModSounds.register();
		LOGGER.info("Hello Fabric world!");
	}


}
