package dev.vince.example.impl.command;

import dev.vince.example.Client;
import dev.vince.example.api.command.Command;
import dev.vince.example.api.utils.LoggingUtil;

public class ToggleCommand extends Command {
    public ToggleCommand() {
        super("Toggle", "Toggle a module", new String[]{"toggle", "t"});
    }

    @Override
    public void run(String[] args, String message) {
        try {
            Client.INSTANCE.getModuleManager().getModuleByName(args[0]).setEnabled(!Client.INSTANCE.getModuleManager().getModuleByName(args[0]).isEnabled());
        } catch (Exception e) {
            LoggingUtil.addChatError("Unknown module: " + args[0]);
        }
    }
}
