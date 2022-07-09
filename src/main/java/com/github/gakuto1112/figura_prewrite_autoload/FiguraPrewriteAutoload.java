package com.github.gakuto1112.figura_prewrite_autoload;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.fabricmc.api.ClientModInitializer;
import com.github.gakuto1112.figura_prewrite_autoload.auto_load.AutoLoad;
import com.github.gakuto1112.figura_prewrite_autoload.config.ConfigManager;

public class FiguraPrewriteAutoload implements ClientModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("figura_prewrite_autoload");
	public final AutoLoad autoLoad = new AutoLoad();
	public static final ConfigManager configManager = new ConfigManager();

	@Override
	public void onInitializeClient() {
		LOGGER.info("Hello Fabric world!");
	}
}