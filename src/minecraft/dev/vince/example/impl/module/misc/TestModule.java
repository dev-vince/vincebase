package dev.vince.example.impl.module.misc;

import dev.vince.example.Client;
import dev.vince.example.api.config.Config;
import dev.vince.example.api.config.ConfigManager;
import dev.vince.example.api.module.Module;
import dev.vince.example.api.module.ModuleCategory;
import org.lwjgl.input.Keyboard;

public final class TestModule extends Module {
    public TestModule() {
        super("TestModule", "A example module for the base",ModuleCategory.MISC, Keyboard.KEY_R);
    }

    @Override
    public void onEnable() {
        Client.INSTANCE.getConfigManager().saveConfig(new Config("TestConfig", "A example module for the base", "2020-01-01", "1.0.0", "release"));
        super.onEnable();
    }
}
