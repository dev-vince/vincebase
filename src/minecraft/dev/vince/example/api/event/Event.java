package dev.vince.example.api.event;

public abstract class Event {
    private boolean canceled;
    private EventType type;

    public void setType(EventType type) {
        this.type = type;
    }

    public EventType getType() {
        return type;
    }

    public boolean isCanceled() {
        return canceled;
    }

    public void setCanceled() {
        this.canceled = true;
    }

    public void setCanceled(boolean value) {
        this.canceled = value;
    }
}
