package dev.vince.example.api.command;

public abstract class Command {
    private final String name, description;

    public abstract void run(String[] args, String message);

    public Command(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
