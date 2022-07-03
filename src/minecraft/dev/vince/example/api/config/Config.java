package dev.vince.example.api.config;

public final class Config {
    private final String name, description, lastUpdated, version, buildType;

    public Config(String name, String description, String lastUpdated, String version, String buildType) {
        this.name = name;
        this.description = description;
        this.lastUpdated = lastUpdated;
        this.version = version;
        this.buildType = buildType;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public String getBuildType() {
        return buildType;
    }

    public String getVersion() {
        return version;
    }
}
