package com.github.gakuto1112.figura_prewrite_autoload;

import java.nio.file.Files;
import java.nio.file.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.blancworks.figura.avatar.AvatarDataManager;
import net.blancworks.figura.avatar.LocalAvatarData;
import com.github.gakuto1112.figura_prewrite_autoload.config.ConfigManager;

public class FiguraPrewriteAutoload implements ClientModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("figura_prewrite_autoload");
	public static final ConfigManager configManager = new ConfigManager();

	@Override
	public void onInitializeClient() {
		ClientPlayConnectionEvents.JOIN.register((ClientPlayNetworkHandler handler, PacketSender sender, MinecraftClient client) -> {
			final String localAvatarPath = FiguraPrewriteAutoload.configManager.getLocalAvatarPath();
			if(!localAvatarPath.equals("")) {
				if(Files.isReadable(Path.of(localAvatarPath))) {
					LocalAvatarData localAvatarData = (LocalAvatarData)AvatarDataManager.getDataForPlayer(client.player.getUuid());
					localAvatarData.isLocalAvatar = true;
					localAvatarData.loadModelFile(localAvatarPath);
				}
				else FiguraPrewriteAutoload.configManager.setLocalAvatarPath("");
			}
		});
	}
}