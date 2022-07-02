package dev.vince.example.api.friend;

import java.util.ArrayList;

public class FriendManager {
    private final ArrayList<Friend> friends = new ArrayList<>();

    public void addFriend(Friend friend) {
        friends.add(friend);
    }

    public ArrayList<Friend> getFriends() {
        return friends;
    }

    public void removeFriend(Friend friend) {
        friends.remove(friend);
    }

    public void clearFriends() {
        friends.clear();
    }
}
