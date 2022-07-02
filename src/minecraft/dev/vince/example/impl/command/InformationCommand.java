package dev.vince.example.impl.command;

import dev.vince.example.Client;
import dev.vince.example.api.command.Command;
import dev.vince.example.api.utils.LoggingUtil;

public class InformationCommand extends Command {
    public InformationCommand() {
        super("information", "Displays information about the client",new String[]{"info","i","information"});
    }

    @Override
    public void run(String[] args, String message) {
        LoggingUtil.addChatInformation(Client.INSTANCE.getName());
        LoggingUtil.addChatInformation("Version: " + Client.INSTANCE.getVersion());
        LoggingUtil.addChatInformation("Build: " + Client.INSTANCE.getBuildType().getName());
        LoggingUtil.addChatInformation("Written by: " + Client.INSTANCE.getAuthor());
    }
}
