package dev.vince.example.impl.command;

import dev.vince.example.Client;
import dev.vince.example.api.command.Command;

public class ModuleCommand extends Command {
    public ModuleCommand(){
        super("Module","Lists all modules and their respective descriptions",new String[]{"modules","mods","features"});
    }

    @Override
    public void run(String[] args, String message) {
        System.out.println("List of all modules");
        Client.INSTANCE.getModuleManager().getModules().forEach(m -> System.out.println(m.getName() + " - " + m.getDescription()));
    }
}
