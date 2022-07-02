package dev.vince.example.impl.command;

import dev.vince.example.Client;
import dev.vince.example.api.command.Command;
import dev.vince.example.api.module.Module;

public class HideCommand extends Command {
    public HideCommand() {
        super("hide", "Hides a module from the ArrayList");
    }

    @Override
    public void run(String[] args, String message) {
        try {
            Module m = Client.INSTANCE.getModuleManager().getModuleByName(args[0]);
            m.setHidden(!m.isHidden());
            Client.INSTANCE.getLoggingUtil().addChatSuccess("Set "+ args[0] + (m.isHidden() ? " to hidden" : " to visible"));
        } catch (Exception e){
            Client.INSTANCE.getLoggingUtil().addChatError("Unknown module: " + args[0]);
        }
    }
}
