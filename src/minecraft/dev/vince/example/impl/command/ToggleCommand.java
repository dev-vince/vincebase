package dev.vince.example.impl.command;

import dev.vince.example.Client;
import dev.vince.example.api.command.Command;

public class ToggleCommand extends Command {
    public ToggleCommand() {
        super("Toggle", "Toggle a module");
    }

    @Override
    public void run(String[] args, String message) {
        Client.INSTANCE.getLoggingUtil().addChatInformation("Toggling module " + args[0]);
    }
}
