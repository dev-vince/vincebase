package dev.vince.example.api.module;

import dev.vince.example.impl.module.misc.TestModule;
import dev.vince.example.impl.module.render.HUD;
import net.minecraft.client.Minecraft;

import java.util.*;
import java.util.stream.Collectors;

public class ModuleManager {
    private final HashMap<Class<? extends Module>, Module> modules = new HashMap<Class<? extends Module>, Module>();

    public ModuleManager() {
        this.addModules();
    }

    private final void addModules() {
        this.modules.put(TestModule.class, new TestModule());
        this.modules.put(HUD.class, new HUD());
    }

    public final List<Module> getModules() {
        return new ArrayList<>(this.modules.values());
    }


    public final List<Module> getSortedModules() {
        return this.getModules().stream().sorted(Comparator.comparing(module -> Minecraft.getMinecraft().fontRendererObj.getStringWidth(((Module) module).getName())).reversed()).collect(Collectors.toList());
    }
    public final <T extends Module> T getModule(Class<T> clas) {
        return (T) getModules().stream().filter(module -> module.getClass() == clas).findFirst().orElse(null);
    }

    public final Module getModuleByName(String name) {
        return this.getModules().stream().filter(module -> module.getName().equals(name)).findFirst().orElse(null);
    }

    public final List<Module> getModulesByCategory(ModuleCategory category) {
        return this.getModules().stream().filter(module -> module.getCategory() == category).collect(Collectors.toList());
    }
}
