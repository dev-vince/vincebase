package dev.vince.example.api.command;

import best.azura.eventbus.core.EventPriority;
import best.azura.eventbus.handler.EventHandler;
import best.azura.eventbus.handler.Listener;
import dev.vince.example.Client;
import dev.vince.example.impl.command.*;
import dev.vince.example.impl.event.ChatEvent;

import java.util.*;

public final class CommandManager {
    private final String prefix;

    private final HashMap<Class<? extends Command>, Command> commands = new HashMap<>();

    public CommandManager(String prefix) {
        this.prefix = prefix;
        this.addCommands();
        Client.INSTANCE.getEventBus().register(this);
    }

    @EventHandler(EventPriority.HIGHEST)
    public final Listener<ChatEvent> onChat = e -> {
        //TODO: Improve this
        if (e.getMessage().startsWith(getPrefix())) {
            e.setCancelled(true);
            String message = e.getMessage().substring(1);
            String command = message.split(" ")[0];
            for (Command cmd : commands.values()) {
                for (String alias : cmd.getAliases()) {
                    if (Objects.equals(alias.toLowerCase(Locale.ROOT), command.toLowerCase(Locale.ROOT))) {
                        String args = message.substring(alias.length()).trim();
                        cmd.run(args.split(" "), args);
                        return;
                    }
                }
            }

            Client.INSTANCE.getLoggingUtil().addChatError("Unknown command: " + command);
            Client.INSTANCE.getLoggingUtil().addChatError("Use \"" + getPrefix() + "help\" for a list of commands.");
        }
    };

    private void addCommands() {
        this.commands.put(ToggleCommand.class, new ToggleCommand());
        this.commands.put(HelpCommand.class, new HelpCommand());
        this.commands.put(VClipCommand.class, new VClipCommand());
        this.commands.put(InformationCommand.class, new InformationCommand());
        this.commands.put(BindCommand.class, new BindCommand());
        this.commands.put(HideCommand.class, new HideCommand());
        this.commands.put(ModuleCommand.class, new ModuleCommand());
    }

    public List<Command> getCommands() {
        return new ArrayList<>(this.commands.values());
    }

    public String getPrefix() {
        return prefix;
    }
}
