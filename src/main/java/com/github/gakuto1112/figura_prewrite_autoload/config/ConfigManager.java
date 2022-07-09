package com.github.gakuto1112.figura_prewrite_autoload.config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import com.google.gson.*;
import net.fabricmc.loader.api.FabricLoader;
import com.github.gakuto1112.figura_prewrite_autoload.FiguraPrewriteAutoload;

public class ConfigManager {
    private ConfigStructure config;

    private final Path configFilePath = Path.of(FabricLoader.getInstance().getConfigDir().resolve("fabric_prewrite_autoload.json").toUri());

    public ConfigManager() {
        this.loadConfig();
    }

    public String getLocalAvatarPath() {
        return this.config.localAvatarPath();
    }

    public void setLocalAvatarPath(String localAvatarPath) {
        this.config = new ConfigStructure(localAvatarPath);
        this.saveConfig();
    }

    private void loadConfig() {
        if(Files.exists(this.configFilePath)) {
            if(Files.isReadable(this.configFilePath)) {
                JsonObject jsonObject;
                try {
                    jsonObject = JsonParser.parseReader(Files.newBufferedReader(this.configFilePath)).getAsJsonObject();
                }
                catch(IOException exception) {
                    FiguraPrewriteAutoload.LOGGER.error("Failed to load the config file. Create a new config.");
                    return;
                }
                final JsonElement localAvatarPathElement = jsonObject.get("localAvatarPath");
                if(Objects.nonNull(localAvatarPathElement))  this.config = new ConfigStructure(new Gson().fromJson(localAvatarPathElement, String.class));
                else this.config = new ConfigStructure("");
            }
            else {
                FiguraPrewriteAutoload.LOGGER.warn("No permission to read the config file. Create a new config.");
                this.config = new ConfigStructure("");
                this.saveConfig();
            }
        }
        else {
            FiguraPrewriteAutoload.LOGGER.warn("The config file not found. Create a new config.");
            this.config = new ConfigStructure("");
            this.saveConfig();
        }
    }

    public void saveConfig() {
        if(Files.exists(this.configFilePath)) {
            if(Files.isWritable(this.configFilePath)) this.saveConfigCore();
            else FiguraPrewriteAutoload.LOGGER.error("Cannot save the config because there is not permission to write.");
        }
        else this.saveConfigCore();
    }

    private void saveConfigCore() {
        final Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            Files.writeString(this.configFilePath, gson.toJson(this.config));
        }
        catch(IOException exception) {
            FiguraPrewriteAutoload.LOGGER.error("Failed to write the config file. Cannot save the config.");
        }
    }
}