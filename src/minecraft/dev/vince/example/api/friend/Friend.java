package dev.vince.example.api.friend;

import dev.vince.example.Client;

import java.util.UUID;

public final class Friend {
    private String name;
    private UUID uuid;

    public Friend(String name, UUID uuid) {
        this.name = name;
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
}
