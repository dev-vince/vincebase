package dev.vince.example.impl.command;

import dev.vince.example.Client;
import dev.vince.example.api.command.Command;
import dev.vince.example.api.utils.LoggingUtil;

public class VClipCommand extends Command {
    public VClipCommand() {
        super("VClip", "Clips up and down!");
    }

    @Override
    public void run(String[] args, String message) {
        if(message.equals("")){
            LoggingUtil.addChatInformation("You need to specify a number!");
            return;
        }

        double distance = Double.parseDouble(args[0]);
        Client.INSTANCE.getMc().thePlayer.setPosition(Client.INSTANCE.getMc().thePlayer.posX, Client.INSTANCE.getMc().thePlayer.posY + distance, Client.INSTANCE.getMc().thePlayer.posZ);
        LoggingUtil.addChatSuccess("Clipped " + distance + " blocks!");
    }
}
