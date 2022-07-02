package dev.vince.example.impl.command;

import dev.vince.example.Client;
import dev.vince.example.api.command.Command;
import dev.vince.example.api.utils.LoggingUtil;

public class PrefixCommand extends Command {
    public PrefixCommand(){
        super("Prefix","Sets the suffix of the command manager",new String[]{"prefix","p","setprefix"});
    }

    @Override
    public void run(String[] args, String message) {
        Client.INSTANCE.getCommandManager().setPrefix(args[0]);
        LoggingUtil.addChatSuccess("Set suffix to: " + args[0]);
    }
}
