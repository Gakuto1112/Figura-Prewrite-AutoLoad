package com.github.gakuto1112.figura_prewrite_autoload;

import com.github.gakuto1112.figura_prewrite_autoload.auto_load.AutoLoad;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.fabricmc.api.ClientModInitializer;

public class FiguraPrewriteAutoload implements ClientModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("figura_prewrite_autoload");
	public final AutoLoad autoLoad = new AutoLoad();

	@Override
	public void onInitializeClient() {
		LOGGER.info("Hello Fabric world!");
	}
}