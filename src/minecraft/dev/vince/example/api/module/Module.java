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
    private boolean enabled,hidden;

    public Module(String name, String description, ModuleCategory category, int defaultKey) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.enabled = false;
        this.keybind = defaultKey;
        this.hidden = false;
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

    public int getKeybind() {
        return keybind;
    }

    public void setKeybind(int keybind) {
        this.keybind = keybind;
    }

    public void onEnable() {
        Client.INSTANCE.getEventBus().register(this);
        Client.INSTANCE.getLoggingUtil().addChatSuccess("Enabled module: " + name);
    }

    public void onDisable() {
        Client.INSTANCE.getEventBus().unregister(this);
        Client.INSTANCE.getLoggingUtil().addChatError("Disabled module: " + name);
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }
}
