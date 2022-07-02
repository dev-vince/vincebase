package dev.vince.example.impl.command;

import dev.vince.example.Client;
import dev.vince.example.api.command.Command;
import dev.vince.example.api.friend.Friend;
import dev.vince.example.api.utils.LoggingUtil;

public class FriendCommand extends Command {
    public FriendCommand() {
        super("Friend", "Adds a friend", new String[]{"friend", "f","friends"});
    }

    @Override
    public void run(String[] args, String message) {
        switch (args[0].toLowerCase()) {
            case "add":
                try {
                    Friend friend = new Friend(args[1], Client.INSTANCE.getMc().theWorld.getPlayerEntityByName(args[1]).getUniqueID());
                    if (Client.INSTANCE.getFriendManager().getFriends().contains(friend)) {
                        LoggingUtil.addChatError("Friend already added.");
                        return;
                    } else {
                        Client.INSTANCE.getFriendManager().addFriend(friend);
                        LoggingUtil.addChatSuccess("Added friend: " + args[1]);
                    }
                } catch (Exception e) {
                    LoggingUtil.addChatError("Player not found");
                }
                return;
            case "remove":
                try {
                    Client.INSTANCE.getFriendManager().removeFriend(new Friend(args[1], Client.INSTANCE.getMc().theWorld.getPlayerEntityByName(args[1]).getUniqueID()));
                    LoggingUtil.addChatSuccess("Remove friend: " + args[1]);
                } catch (Exception e) {
                    LoggingUtil.addChatError("Player not found");
                }
                return;
            case "list":
                LoggingUtil.addChatInformation("Friends: ");
                Client.INSTANCE.getFriendManager().getFriends().forEach(f -> LoggingUtil.addChatInformation(f.getName()));
                return;
            case "clear":
                Client.INSTANCE.getFriendManager().clearFriends();
                LoggingUtil.addChatSuccess("Cleared friends");
                return;

        }

        LoggingUtil.addChatError("Invalid argument");
    }
}
