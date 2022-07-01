package dev.vince.example;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Launcher {
    public static void start() { //needs to be static sadly
        //You can put some authentication shit in here but this is fairly easy to crack so don't bother
        Client.INSTANCE.start.run();
    }
}
