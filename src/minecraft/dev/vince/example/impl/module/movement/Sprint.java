package dev.vince.example.impl.module.movement;

import best.azura.eventbus.core.EventPriority;
import best.azura.eventbus.handler.EventHandler;
import best.azura.eventbus.handler.Listener;
import dev.vince.example.Client;
import dev.vince.example.api.module.Module;
import dev.vince.example.api.module.ModuleCategory;
import dev.vince.example.impl.event.UpdateEvent;

public class Sprint extends Module {
    public Sprint() {
        super("Sprint", "Automatically sprints!", ModuleCategory.MOVEMENT, 0);
    }

    @EventHandler(EventPriority.LOWEST)
    public final Listener<UpdateEvent> onUpdate = e -> {
        if (Client.INSTANCE.getMoveUtil().isMoving()) {
            Client.INSTANCE.getMc().thePlayer.setSprinting(true);
        }
    };
}
