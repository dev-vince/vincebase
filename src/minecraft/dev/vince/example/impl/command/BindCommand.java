package dev.vince.example.impl.command;

import dev.vince.example.Client;
import dev.vince.example.api.command.Command;
import dev.vince.example.api.module.Module;
import dev.vince.example.api.utils.LoggingUtil;
import org.lwjgl.input.Keyboard;

import java.util.Locale;

public class BindCommand extends Command {
    public BindCommand() {
        super("Bind", "Binds a key to a command", new String[]{"bind", "b"});
    }

    @Override
    public void run(String[] args, String message) {
        try {
            Module m = Client.INSTANCE.getModuleManager().getModuleByName(args[0]);
            m.setKeybind(Keyboard.getKeyIndex(args[1].toUpperCase(Locale.ENGLISH)));
            LoggingUtil.addChatSuccess("Bound " + args[0] + " to " + m.getKeybindString());
        } catch (Exception e) {
            LoggingUtil.addChatError("Unknown module: " + args[0]);
        }
    }
}
