package dev.vince.example.impl.command;

import dev.vince.example.Client;
import dev.vince.example.api.command.Command;

public class InformationCommand extends Command {
    public InformationCommand() {
        super("information", "Displays information about the client",new String[]{"info","i","information"});
    }

    @Override
    public void run(String[] args, String message) {
        Client.INSTANCE.getLoggingUtil().addChatInformation(Client.INSTANCE.getName());
        Client.INSTANCE.getLoggingUtil().addChatInformation("Version: " + Client.INSTANCE.getVersion());
        Client.INSTANCE.getLoggingUtil().addChatInformation("Build: " + Client.INSTANCE.getBuildType().getName());
        Client.INSTANCE.getLoggingUtil().addChatInformation("Written by: " + Client.INSTANCE.getAuthor());
    }
}
