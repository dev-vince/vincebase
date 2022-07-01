package dev.vince.example.impl.event;


import dev.vince.example.api.event.Event;

public class KeyEvent extends Event {
    private int key;

    public KeyEvent(int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }
}
