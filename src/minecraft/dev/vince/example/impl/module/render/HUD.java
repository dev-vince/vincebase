package dev.vince.example.impl.module.render;

import best.azura.eventbus.handler.EventHandler;
import best.azura.eventbus.handler.Listener;
import dev.vince.example.Client;
import dev.vince.example.api.module.Module;
import dev.vince.example.api.module.ModuleCategory;
import dev.vince.example.impl.event.OverlayEvent;
import org.lwjgl.input.Keyboard;

import java.util.concurrent.atomic.AtomicReference;

public class HUD extends Module {
    public HUD() {
        super("HUD", "Displays the HUD", ModuleCategory.RENDER, Keyboard.KEY_L);
        this.setEnabled(true);
    }

    @EventHandler
    public final Listener<OverlayEvent> overlayListener = e -> {
        mc.fontRendererObj.drawStringWithShadow(Client.INSTANCE.getName() + " " + Client.INSTANCE.getVersion(), 3, 3, -1);

        float xOffset = 3;
        AtomicReference<Float> yOffset = new AtomicReference<>((float) 3);
        Client.INSTANCE.getModuleManager().getSortedModules().forEach(module -> {
            if (module.isEnabled()) {
                mc.fontRendererObj.drawStringWithShadow(module.getName(), e.getSr().getScaledWidth() - mc.fontRendererObj.getStringWidth(module.getName()) - xOffset, yOffset.get(), -1);
                yOffset.updateAndGet(v -> v + mc.fontRendererObj.FONT_HEIGHT + 1);
            }
        });
    };
}
