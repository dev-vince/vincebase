package dev.vince.example.impl.module.misc;

import dev.vince.example.Client;
import dev.vince.example.api.module.Module;
import dev.vince.example.api.module.ModuleCategory;
import dev.vince.example.impl.event.TestEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import org.lwjgl.input.Keyboard;

public final class Benchmark extends Module {
    public Benchmark() {
        super("Benchmark", "A example module for the base", ModuleCategory.MISC, Keyboard.KEY_K);
    }


    @Override
    public void onEnable() {
        final long current = System.nanoTime();
        int iterations = 100000;
        for (int i = 0; i < iterations; i++) {
            Client.INSTANCE.getEventBus().call(new TestEvent());
        }
        Client.INSTANCE.getLoggingUtil().addChat(iterations + " iterations took " + ((System.nanoTime() - current) / 1000000.0) + "ms");
        this.setEnabled(false);
        super.onEnable();
    }
}