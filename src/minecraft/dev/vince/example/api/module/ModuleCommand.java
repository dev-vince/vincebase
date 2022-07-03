package dev.vince.example.api.module;

import dev.vince.example.api.settings.Setting;
import dev.vince.example.api.utils.LoggingUtil;
import dev.vince.example.impl.settings.BooleanSetting;
import lombok.extern.java.Log;

import java.util.Locale;
import java.util.Objects;

public final class ModuleCommand {
    private final String name, description;
    private final Module module;

    public ModuleCommand(Module m, String name, String description) {
        this.module = m;
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Module getModule() {
        return module;
    }

    public void run(String[] args, String message) {
        LoggingUtil.addChatInformation(name + " - " + description);

        for (Setting setting : this.module.getSettings()) {
            if(Objects.equals(setting.getName().toLowerCase(Locale.ROOT), args[0].toLowerCase(Locale.ROOT))){
                switch (args[1].toLowerCase()){
                    case "get":
                        LoggingUtil.addChatInformation(setting.getName() + " - " + setting.getValueObject());
                        return;
                    case "set":
                        if(setting instanceof BooleanSetting){
                            ((BooleanSetting) setting).setEnabled(Boolean.parseBoolean(args[2]));
                            LoggingUtil.addChatInformation("Set setting to " + args[2]);
                            return;
                        }
                        LoggingUtil.addChatInformation("Invalid setting type");
                        return;
                    default:
                        LoggingUtil.addChatInformation("Invalid argument");
                        return;
                }
            }

            LoggingUtil.addChatInformation(setting.getName() + " - " + setting.getValueObject());
        }
    }
}
