package dev.vince.example.api.target;

import java.util.UUID;

public final class Target {
    private String name;
    private UUID uuid;

    public Target(String name, UUID uuid) {
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
