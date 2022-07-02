package dev.vince.example.api.utils;

import dev.vince.example.Client;

public final class MoveUtil {
    public static boolean isMoving() {
        return Client.INSTANCE.getMc().thePlayer.moveForward != 0 || Client.INSTANCE.getMc().thePlayer.moveStrafing != 0;
    }

}
