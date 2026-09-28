package com.netut.sudustrial.client;

import com.netut.sudustrial.register.entity.block.tnt.RegisterTntEntity;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.renderer.entity.TntRenderer;

public class SudustrialClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
		EntityRendererRegistry.register(RegisterTntEntity.CUSTOM_TNT, TntRenderer::new);
	}
}
