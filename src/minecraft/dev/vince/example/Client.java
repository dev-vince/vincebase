package dev.vince.example;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import dev.vince.example.api.client.BuildType;
import dev.vince.example.api.module.Module;
import dev.vince.example.api.module.ModuleManager;
import dev.vince.example.api.utils.LoggingUtil;
import dev.vince.example.impl.event.KeyEvent;
import lombok.extern.java.Log;
import org.lwjgl.opengl.Display;

public enum Client {
    INSTANCE;

    private String name, version, author;
    private EventBus eventBus;
    private LoggingUtil loggingUtil;
    private ModuleManager moduleManager;
    private BuildType buildType;

    public final Runnable start = () -> {
        this.initiate.run(); // Initiate the client

        //Initiate the client x2
        this.loggingUtil = new LoggingUtil(); // Initialize the logging utility
        this.eventBus = new EventBus(this.name); //Event bus for the client using the client name as the identifier
        this.moduleManager = new ModuleManager(); // Initialize the module manager

        //Post initialization
        this.loggingUtil.log(name + " started on build " + this.version);
        this.eventBus.register(this); // Register the client to the event bus
    };

    private final Runnable initiate = () -> {
        System.out.println("Client initiated!");
        this.name = "BinceBase";
        this.version = "1.0";
        this.author = "Vince";
        this.buildType = BuildType.DEVELOPER;
        Display.setTitle(this.name + " v" + this.version + " by " + this.author);
    };

    @Subscribe
    public void onKey(KeyEvent e) {
        INSTANCE.getModuleManager().getModules().stream().filter(m -> m.getKeybind() == e.getKey()).forEach(Module::enable);
    }

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }

    public String getAuthor() {
        return author;
    }

    public EventBus getEventBus() {
        return eventBus;
    }

    public ModuleManager getModuleManager() {
        return moduleManager;
    }

    public LoggingUtil getLoggingUtil() {
        return loggingUtil;
    }

    public BuildType getBuildType() {
        return buildType;
    }
}
