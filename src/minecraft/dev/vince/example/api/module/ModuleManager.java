package dev.vince.example.api.module;

import dev.vince.example.impl.module.misc.TestModule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class ModuleManager {
    private HashMap<Class<? extends Module>, Module> modules = new HashMap<Class<? extends Module>, Module>();

    public ModuleManager() {
        this.addModules();
    }

    private void addModules() {
        this.modules.put(TestModule.class, new TestModule());
    }

    public List<Module> getModules() {
        return new ArrayList<>(this.modules.values());
    }

    public <T extends Module> T getModule(Class<T> clas) {
        return (T) getModules().stream().filter(module -> module.getClass() == clas).findFirst().orElse(null);
    }

    public Module getModuleByName(String name) {
        return this.getModules().stream().filter(module -> module.getName().equals(name)).findFirst().orElse(null);
    }

    public List<Module> getModulesByCategory(ModuleCategory category) {
        return this.getModules().stream().filter(module -> module.getCategory() == category).collect(Collectors.toList());
    }
}
