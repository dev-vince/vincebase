package dev.vince.example.impl.command;

import dev.vince.example.Client;
import dev.vince.example.api.command.Command;
import dev.vince.example.api.target.Target;
import dev.vince.example.api.utils.LoggingUtil;

public class TargetCommand extends Command {
    public TargetCommand() {
        super("Target","Sets the target of the command manager",new String[]{"target","t","settarget"});
    }

    @Override
    public void run(String[] args, String message) {
        switch (args[0].toLowerCase()) {
            case "add":
                try {
                    Target friend = new Target(args[1], Client.INSTANCE.getMc().theWorld.getPlayerEntityByName(args[1]).getUniqueID());
                    if (Client.INSTANCE.getTargetManager().getTargets().contains(friend)) {
                        LoggingUtil.addChatError("Target already added.");
                        return;
                    } else {
                        Client.INSTANCE.getTargetManager().addTarget(friend);
                        LoggingUtil.addChatSuccess("Added target: " + args[1]);
                    }
                } catch (Exception e) {
                    LoggingUtil.addChatError("Player not found");
                }
                return;
            case "remove":
                try {
                    Client.INSTANCE.getTargetManager().removeTarget(new Target(args[1], Client.INSTANCE.getMc().theWorld.getPlayerEntityByName(args[1]).getUniqueID()));
                    LoggingUtil.addChatSuccess("Remove target: " + args[1]);
                } catch (Exception e) {
                    LoggingUtil.addChatError("Player not found");
                }
                return;
            case "list":
                LoggingUtil.addChatInformation("Targets: ");
                Client.INSTANCE.getTargetManager().getTargets().forEach(f -> LoggingUtil.addChatInformation(f.getName()));
                return;
            case "clear":
                Client.INSTANCE.getTargetManager().clearTargets();
                LoggingUtil.addChatSuccess("Cleared targets");
                return;

        }

        LoggingUtil.addChatError("Invalid argument");
    }
}
