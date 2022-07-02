package dev.vince.example.api.utils;

import dev.vince.example.Client;
import net.minecraft.util.ChatComponentText;

public final class LoggingUtil {

    public void log(String message) {
        System.out.println("[CLIENT] " + message);
    }

    public void addChat(String message) {
        Client.INSTANCE.getMc().thePlayer.addChatMessage(new ChatComponentText(ChatColor.GRAY + "[" + ChatColor.GREEN + Client.INSTANCE.getName() + ChatColor.GRAY + "] " + message));
    }
}
