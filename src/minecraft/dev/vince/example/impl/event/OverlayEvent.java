package dev.vince.example.impl.event;

import dev.vince.example.api.event.Event;
import net.minecraft.client.gui.ScaledResolution;

public final class OverlayEvent extends Event {
    private final ScaledResolution sr;

    public OverlayEvent(ScaledResolution sr) {
        this.sr = sr;
    }

    public ScaledResolution getSr() {
        return sr;
    }
}
