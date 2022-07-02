package dev.vince.example.impl.command;

import dev.vince.example.Client;
import dev.vince.example.api.command.Command;
import net.minecraft.network.play.client.C03PacketPlayer;

public class VClipCommand extends Command {
    public VClipCommand() {
        super("VClip", "Clips up and down!");
    }

    @Override
    public void run(String[] args, String message) {
        if(message.equals("")){
            Client.INSTANCE.getLoggingUtil().addChatInformation("You need to specify a number!");
            return;
        }

        double distance = Double.parseDouble(args[0]);
        Client.INSTANCE.getMc().thePlayer.setPosition(Client.INSTANCE.getMc().thePlayer.posX, Client.INSTANCE.getMc().thePlayer.posY + distance, Client.INSTANCE.getMc().thePlayer.posZ);
        Client.INSTANCE.getLoggingUtil().addChatSuccess("Clipped " + distance + " blocks!");
    }
}
