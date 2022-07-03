package dev.vince.example.api.module;

import dev.vince.example.Client;
import dev.vince.example.api.settings.Setting;
import dev.vince.example.api.utils.LoggingUtil;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;
import java.util.Arrays;

public class Module {
    private final ArrayList<Setting> settings = new ArrayList<>();
    private final String name;
    private final String description;
    private final ModuleCategory category;
    private int keybind;
    private boolean enabled,hidden;
    private final ModuleCommand command;


    public Module(String name, String description, ModuleCategory category, int defaultKey) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.enabled = false;
        this.keybind = defaultKey;
        this.hidden = false;
        this.command = new ModuleCommand(this, name, description);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void toggle() {
        setEnabled(!enabled);
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        if (this.enabled) {
            this.onEnable();
        } else {
            this.onDisable();
        }
    }

    public ModuleCategory getCategory() {
        return category;
    }

    public String getKeybindString(){
        return Keyboard.getKeyName(keybind);
    }

    public int getKeybind() {
        return keybind;
    }

    public void setKeybind(int keybind) {
        this.keybind = keybind;
    }

    public void onEnable() {
        if(!Client.INSTANCE.getEventBus().isRegistered(this)) {
            Client.INSTANCE.getEventBus().register(this);
        }
        LoggingUtil.addChatSuccess("Enabled module: " + name);
    }

    public void onDisable() {
        if(Client.INSTANCE.getEventBus().isRegistered(this)) {
            Client.INSTANCE.getEventBus().unregister(this);
        }
        LoggingUtil.addChatError("Disabled module: " + name);
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public void addSetting(Setting... setting) {
        settings.addAll(Arrays.asList(setting));
    }

    public ArrayList<Setting> getSettings() {
        return settings;
    }

    public ModuleCommand getCommand() {
        return command;
    }
}
