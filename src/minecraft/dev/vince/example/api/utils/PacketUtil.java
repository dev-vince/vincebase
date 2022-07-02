package dev.vince.example.api.utils;

import dev.vince.example.Client;
import net.minecraft.network.Packet;

@SuppressWarnings("rawtypes")
public final class PacketUtil {
    public void sendPacket(Packet packet) {
        Client.INSTANCE.getMc().getNetHandler().addToSendQueue(packet);
    }

    public void sendPacketNoEvent(Packet packet) {
        Client.INSTANCE.getMc().getNetHandler().addToSendQueueSilent(packet);
    }
}
