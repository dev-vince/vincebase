package dev.vince.example.api.module;

import dev.vince.example.impl.module.misc.TestModule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

public class ModuleManager {
    private HashMap<Class<? extends Module>, Module> modules = new HashMap<Class<? extends Module>, Module>();

    public ModuleManager() {
        this.addModules();
    }

    private void addModules(){
        this.modules.put(TestModule.class, new TestModule());
    }

    public List<Module> getModules() {
        return new ArrayList<>(this.modules.values());
    }
}
