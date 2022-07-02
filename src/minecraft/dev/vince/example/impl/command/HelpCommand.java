package dev.vince.example.impl.command;

import dev.vince.example.Client;
import dev.vince.example.api.command.Command;

public class HelpCommand extends Command {
    public HelpCommand() {
        super("Help", "Help command");
    }

    @Override
    public void run(String[] args, String message) {
        for (Command c : Client.INSTANCE.getCommandManager().getCommands()) {
            Client.INSTANCE.getLoggingUtil().addChatInformation(c.getName() + " - " + c.getDescription());
        }
        Client.INSTANCE.getLoggingUtil().addChatInformation("Use \"" + Client.INSTANCE.getCommandManager().getPrefix() + "info\" for more information about the client.");
    }
}
