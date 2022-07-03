package dev.vince.example.api.config;

import com.google.gson.*;
import dev.vince.example.Client;
import dev.vince.example.api.module.Module;
import dev.vince.example.api.settings.Setting;
import dev.vince.example.api.utils.LoggingUtil;
import dev.vince.example.impl.settings.BooleanSetting;
import tv.twitch.chat.ChatUserInfo;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public final class ConfigManager {
    private final File directory;
    private final ArrayList<Config> configs;

    public ConfigManager(File directory) {
        this.directory = directory;
        this.configs = new ArrayList<>();
    }

    public ArrayList<Config> getConfigs() {
        return configs;
    }

    public File getDirectory() {
        return directory;
    }

    public void addConfig(Config config) {
        configs.add(config);
    }

    public void saveConfig(Config config) {
        LoggingUtil.log("Saving config: " + config.getName());
        File savedConfig = new File(directory, config.getName() + ".json");

        try {
            if (!directory.exists()) {
                directory.mkdirs();
            }

            if (!savedConfig.exists()) {
                savedConfig.createNewFile();
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(savedConfig));

            JsonObject json = new JsonObject();
            JsonObject info = new JsonObject();

            // Information about the config build
            info.addProperty("name", config.getName());
            info.addProperty("description", config.getDescription());
            info.addProperty("lastUpdated", config.getLastUpdated());
            info.addProperty("version", config.getVersion());
            info.addProperty("build-type", config.getBuildType());
            json.add("info", info);

            // I wonder what this is for!
            JsonArray modules = new JsonArray();

            for (Module m : Client.INSTANCE.getModuleManager().getModules()) {
                JsonObject module = new JsonObject();
                module.addProperty("name", m.getName());
                module.addProperty("enabled", m.isEnabled());
                module.addProperty("keybind", m.getKeybind());
                module.addProperty("hidden", m.isHidden());
                for (Setting s : m.getSettings()) {
                    if (s instanceof BooleanSetting) {
                        module.addProperty(s.getName(), ((BooleanSetting) s).getValue());
                    }
                }
                modules.add(module);
            }
            json.add("modules", modules);

            String finalJson = new GsonBuilder().setPrettyPrinting().create().toJson(json);
            writer.write(finalJson);
            writer.close();
            LoggingUtil.addChatSuccess("Saved Config '" + config.getName() + "'");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadConfig(String configName) {
        try {
            Reader reader = new FileReader(new File(directory, configName + ".json"));
            JsonElement node = new JsonParser().parse(reader);
            if (!node.isJsonObject()) {
                return;
            }
            JsonArray arr = node.getAsJsonObject().get("info").getAsJsonArray();
            arr.forEach(element -> {
                JsonObject obj = element.getAsJsonObject();
                String modName = obj.get("name").getAsString();
                Module m = Client.INSTANCE.getModuleManager().getModuleByName(modName);
                if (m != null) {
                    obj.entrySet().forEach(data -> {
                        switch (data.getKey()) {
                            case "name":
                                break;
                            case "enabled":
                                m.setEnabled(data.getValue().getAsBoolean());
                                break;
                            case "keybind":
                                m.setKeybind(data.getValue().getAsInt());
                                break;
                            case "hidden":
                                m.setHidden(data.getValue().getAsBoolean());
                                break;
                        }
                    });
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
