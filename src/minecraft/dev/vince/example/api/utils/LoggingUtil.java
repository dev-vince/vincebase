package dev.vince.example.api.utils;

import dev.vince.example.Client;
import net.minecraft.util.ChatComponentText;

public final class LoggingUtil {

    public void log(String message) {
        System.out.println("[" + Client.INSTANCE.getName() + "] " + message);
    }

    public void addChat(String message) {
        Client.INSTANCE.getMc().thePlayer.addChatMessage(new ChatComponentText(ChatColor.GRAY + "[" + ChatColor.GREEN + Client.INSTANCE.getName() + ChatColor.GRAY + "] " + message));
    }

    public void addChatSuccess(String message) {
        Client.INSTANCE.getMc().thePlayer.addChatMessage(new ChatComponentText(ChatColor.GRAY + "[" + ChatColor.GREEN + Client.INSTANCE.getName() + ChatColor.GRAY + "] " + message));
    }

    public void addChatInformation(String message) {
        Client.INSTANCE.getMc().thePlayer.addChatMessage(new ChatComponentText(ChatColor.GRAY + "[" + ChatColor.BLUE + Client.INSTANCE.getName() + ChatColor.GRAY + "] " + message));
    }

    public void addChatWarning(String message) {
        Client.INSTANCE.getMc().thePlayer.addChatMessage(new ChatComponentText(ChatColor.GRAY + "[" + ChatColor.GOLD + Client.INSTANCE.getName() + ChatColor.GRAY + "] " + message));
    }

    public void addChatError(String message) {
        Client.INSTANCE.getMc().thePlayer.addChatMessage(new ChatComponentText(ChatColor.GRAY + "[" + ChatColor.RED + Client.INSTANCE.getName() + ChatColor.GRAY + "] " + message));
    }
}
