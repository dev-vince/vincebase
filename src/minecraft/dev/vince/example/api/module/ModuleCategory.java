package dev.vince.example.api.module;

public enum ModuleCategory {
    COMBAT("Combat"),
    MOVEMENT("Movement"),
    PLAYER("Player"),
    RENDER("Render"),
    EXPLOIT("Exploit"),
    MISC("Misc"),
    GHOST("Ghost");

    final String name;
    ModuleCategory(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
