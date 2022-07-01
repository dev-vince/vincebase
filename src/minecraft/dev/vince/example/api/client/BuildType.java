package dev.vince.example.api.client;

public enum BuildType {
    RELEASE("Release"),
    BETA("Beta"),
    DEVELOPER("Developer");

    private final String name;

    BuildType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
