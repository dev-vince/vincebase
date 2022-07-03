package dev.vince.example.impl.command;

import dev.vince.example.Client;
import dev.vince.example.api.command.Command;
import dev.vince.example.api.utils.LoggingUtil;

public class HelpCommand extends Command {
    public HelpCommand() {
        super("Help", "Help command");
    }

    @Override
    public void run(String[] args, String message) {
        Client.INSTANCE.getCommandManager().getCommands().forEach(c -> LoggingUtil.addChatInformation(c.getName() + " - " + c.getDescription()));
        LoggingUtil.addChatInformation("Use \"" + Client.INSTANCE.getCommandManager().getPrefix() + "info\" for more information about the client.");
    }
}
