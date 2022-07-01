package dev.vince.example.impl.module.misc;

import dev.vince.example.api.module.Module;
import dev.vince.example.api.module.ModuleCategory;
import org.lwjgl.input.Keyboard;

public class TestModule extends Module {
    public TestModule() {
        super("TestModule", "A example module for the base",ModuleCategory.MISC, Keyboard.KEY_R);
    }
}
