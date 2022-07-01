package dev.vince.example.impl.event;


import dev.vince.example.api.event.Event;

public class KeyEvent extends Event {
    private final int key;

    public KeyEvent(int key) {
        this.key = key;
    }

    public final int getKey() {
        return key;
    }
}
