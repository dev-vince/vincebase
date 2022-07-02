package dev.vince.example.api.command;

public abstract class Command {
    private final String name, description;
    private final String[] aliases;

    public abstract void run(String[] args, String message);

    public Command(String name, String description) {
        this.name = name;
        this.description = description;
        this.aliases = new String[]{name};
    }

    public Command(String name, String description, String[] aliases) {
        this.name = name;
        this.description = description;
        this.aliases = aliases;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String[] getAliases() {
        return aliases;
    }
}
