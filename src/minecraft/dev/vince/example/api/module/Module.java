package dev.vince.example.api.module;

import dev.vince.example.Client;
import dev.vince.example.api.utils.LoggingUtil;
import net.minecraft.client.Minecraft;

public class Module {
    public final Minecraft mc = Minecraft.getMinecraft();
    private final String name;
    private final String description;
    private final ModuleCategory category;
    private int keybind;
    private boolean enabled;

    public Module(String name, String description, ModuleCategory category, int defaultKey) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.enabled = false;
        this.keybind = defaultKey;
    }

    public final String getName() {
        return name;
    }

    public final String getDescription() {
        return description;
    }

    public final boolean enable() {
        setEnabled(!enabled);
        return enabled;
    }

    public final boolean isEnabled() {
        return enabled;
    }

    public final void setEnabled(boolean enabled) {
        this.enabled = enabled;
        if (this.enabled) {
            this.onEnable();
        } else {
            this.onDisable();
        }
    }

    public final ModuleCategory getCategory() {
        return category;
    }

    public final int getKeybind() {
        return keybind;
    }

    public final void setKeybind(int keybind) {
        this.keybind = keybind;
    }

    public final void onEnable() {
        Client.INSTANCE.getEventBus().register(this);
        Client.INSTANCE.getLoggingUtil().log("Enabled module: " + name);
    }

    public final void onDisable() {
        Client.INSTANCE.getEventBus().unregister(this);
        Client.INSTANCE.getLoggingUtil().log("Disabled module: " + name);
    }
}
