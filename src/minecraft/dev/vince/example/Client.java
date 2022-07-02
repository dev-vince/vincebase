package dev.vince.example;

import dev.vince.example.api.client.BuildType;
import dev.vince.example.api.command.CommandManager;
import dev.vince.example.api.module.Module;
import dev.vince.example.api.module.ModuleManager;
import dev.vince.example.api.utils.*;
import dev.vince.example.impl.event.KeyEvent;
import best.azura.eventbus.core.EventBus;
import best.azura.eventbus.handler.EventHandler;
import best.azura.eventbus.handler.Listener;
import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.Display;

public enum Client {
    INSTANCE;
    private final Minecraft mc = Minecraft.getMinecraft();
    private String name, version, author;
    private EventBus eventBus;
    private LoggingUtil loggingUtil;
    private PacketUtil packetUtil;
    private EntityUtil entityUtil;
    private MathUtil mathUtil;
    private MoveUtil moveUtil;
    private ModuleManager moduleManager;
    private CommandManager commandManager;
    private BuildType buildType;

    public final Runnable start = () -> {
        System.out.println("Client initiated!");
        this.name = "BinceBase";
        this.version = "1.0";
        this.author = "Vince";
        this.buildType = BuildType.DEVELOPER;

        Display.setTitle(this.name + " v" + this.version + " by " + this.author);

        this.eventBus = new EventBus(); // Initialize the event bus
        this.moduleManager = new ModuleManager(); // Initialize the module manager
        this.commandManager = new CommandManager("."); // Initialize the command manager along with setting the prefix

        //Post initialization
        LoggingUtil.log(name + " started on build " + this.version);
        this.eventBus.register(this); // Register the client to the event bus

    };

    @EventHandler()
    public final Listener<KeyEvent> onKey = e -> getModuleManager().getModules().stream().filter(m -> m.getKeybind() == e.getKey()).forEach(Module::enable);

    public final String getName() {
        return name;
    }

    public final String getVersion() {
        return version;
    }

    public final String getAuthor() {
        return author;
    }

    public final EventBus getEventBus() {
        return eventBus;
    }

    public final ModuleManager getModuleManager() {
        return moduleManager;
    }

    public final BuildType getBuildType() {
        return buildType;
    }

    public final CommandManager getCommandManager() {
        return commandManager;
    }

    public final Minecraft getMc() {
        return mc;
    }
}
