package dev.vince.example.api.module;

import dev.vince.example.Client;
import dev.vince.example.api.utils.LoggingUtil;
import net.minecraft.client.Minecraft;

public class Module {
    public Minecraft mc = Minecraft.getMinecraft();
    private String name, description;
    private boolean enabled;
    private ModuleCategory category;
    private int keybind;

    public Module(String name, String description, ModuleCategory category, int defaultKey) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.enabled = false;
        this.keybind = defaultKey;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean enable() {
        setEnabled(!enabled);
        return enabled;
    }

    private boolean isEnabled() {
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

    public int getKeybind() {
        return keybind;
    }

    public void setKeybind(int keybind) {
        this.keybind = keybind;
    }

    public void onEnable() {
        Client.INSTANCE.getEventBus().register(this);
        Client.INSTANCE.getLoggingUtil().log("Enabled module: " + name);
    }

    public void onDisable() {
        Client.INSTANCE.getEventBus().unregister(this);
        Client.INSTANCE.getLoggingUtil().log("Disabled module: " + name);
    }
}
