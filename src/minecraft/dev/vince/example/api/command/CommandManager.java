package dev.vince.example.api.command;

import best.azura.eventbus.core.EventPriority;
import best.azura.eventbus.handler.EventHandler;
import best.azura.eventbus.handler.Listener;
import dev.vince.example.Client;
import dev.vince.example.api.module.Module;
import dev.vince.example.impl.command.ToggleCommand;
import dev.vince.example.impl.event.ChatEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public final class CommandManager {
    private String prefix;

    private final HashMap<Class<? extends Command>, Command> commands = new HashMap<Class<? extends Command>, Command>();

    public CommandManager(String prefix) {
        this.prefix = prefix;
        addCommands();
        Client.INSTANCE.getEventBus().register(this);
    }

    @EventHandler(EventPriority.HIGHEST)
    public final Listener<ChatEvent> onChat = e -> {
        //TODO: Improve this
        if(e.getMessage().startsWith(prefix)) {
            e.setCancelled(true);
            String message = e.getMessage().substring(1);
            String command = message.split(" ")[0];
            String args = message.substring(command.length()).trim();
            for(Command cmd : commands.values()) {
                if (Objects.equals(cmd.getName(), command)) {
                    cmd.run(args.split(" "), args);
                    break;
                }
            }
        }
    };

    private void addCommands() {
        this.commands.put(ToggleCommand.class, new ToggleCommand());
    }

    public List<Command> getCommands() {
        return new ArrayList<>(this.commands.values());
    }
}
