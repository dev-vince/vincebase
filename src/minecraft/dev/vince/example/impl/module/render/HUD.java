package dev.vince.example.impl.module.render;

import best.azura.eventbus.handler.EventHandler;
import best.azura.eventbus.handler.Listener;
import dev.vince.example.Client;
import dev.vince.example.api.module.Module;
import dev.vince.example.api.module.ModuleCategory;
import dev.vince.example.impl.event.OverlayEvent;
import dev.vince.example.impl.settings.BooleanSetting;
import org.lwjgl.input.Keyboard;

import java.util.concurrent.atomic.AtomicReference;

public final class HUD extends Module {
    private final BooleanSetting array = new BooleanSetting("Array", true);

    public HUD() {
        super("HUD", "Displays the HUD", ModuleCategory.RENDER, Keyboard.KEY_L);
        this.setEnabled(true);
        this.setHidden(false);
        this.addSetting(array);
    }

    @EventHandler
    public final Listener<OverlayEvent> overlayListener = e -> {
        Client.INSTANCE.getMc().fontRendererObj.drawStringWithShadow(Client.INSTANCE.getName() + " " + Client.INSTANCE.getVersion(), 3, 3, -1);

        if (this.array.getValue()) {
            float xOffset = 3;
            AtomicReference<Float> yOffset = new AtomicReference<>(3F);
            Client.INSTANCE.getModuleManager().getSortedModules().forEach(module -> {
                if (module.isEnabled() && !module.isHidden()) {
                    Client.INSTANCE.getMc().fontRendererObj.drawStringWithShadow(module.getName(), e.getSr().getScaledWidth() - Client.INSTANCE.getMc().fontRendererObj.getStringWidth(module.getName()) - xOffset, yOffset.get(), -1);
                    yOffset.updateAndGet(v -> v + Client.INSTANCE.getMc().fontRendererObj.FONT_HEIGHT + 1F);
                }
            });
        }
    };
}
