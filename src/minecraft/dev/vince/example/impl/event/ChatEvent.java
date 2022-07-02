package dev.vince.example.impl.event;

import dev.vince.example.api.event.Event;

public final class ChatEvent extends Event {
    private final String message;

    public ChatEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
