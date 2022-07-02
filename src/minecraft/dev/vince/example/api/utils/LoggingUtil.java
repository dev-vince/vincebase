package dev.vince.example.api.utils;

import dev.vince.example.Client;
import net.minecraft.util.ChatComponentText;

public final class LoggingUtil {

    public static void log(String message) {
        System.out.println("[" + Client.INSTANCE.getName() + "] " + message);
    }

    public static void addChat(String message) {
        if(Client.INSTANCE.getMc().thePlayer != null) {
            Client.INSTANCE.getMc().thePlayer.addChatMessage(new ChatComponentText(ChatColor.GRAY + "[" + ChatColor.GREEN + Client.INSTANCE.getName() + ChatColor.GRAY + "] " + message));
        }
    }

    public static void addChatSuccess(String message) {
        if(Client.INSTANCE.getMc().thePlayer != null) {
            Client.INSTANCE.getMc().thePlayer.addChatMessage(new ChatComponentText(ChatColor.GRAY + "[" + ChatColor.GREEN + Client.INSTANCE.getName() + ChatColor.GRAY + "] " + message));
        }
    }

    public static void addChatInformation(String message) {
        if(Client.INSTANCE.getMc().thePlayer != null) {
            Client.INSTANCE.getMc().thePlayer.addChatMessage(new ChatComponentText(ChatColor.GRAY + "[" + ChatColor.BLUE + Client.INSTANCE.getName() + ChatColor.GRAY + "] " + message));
        }
    }

    public static void addChatWarning(String message) {
        if(Client.INSTANCE.getMc().thePlayer != null) {
            Client.INSTANCE.getMc().thePlayer.addChatMessage(new ChatComponentText(ChatColor.GRAY + "[" + ChatColor.GOLD + Client.INSTANCE.getName() + ChatColor.GRAY + "] " + message));
        }
    }

    public static void addChatError(String message) {
        if(Client.INSTANCE.getMc().thePlayer != null) {
            Client.INSTANCE.getMc().thePlayer.addChatMessage(new ChatComponentText(ChatColor.GRAY + "[" + ChatColor.RED + Client.INSTANCE.getName() + ChatColor.GRAY + "] " + message));
        }
    }
}
