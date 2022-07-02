package dev.vince.example.api.utils;

import dev.vince.example.Client;
import net.minecraft.network.Packet;

@SuppressWarnings("rawtypes")
public final class PacketUtil {
    public void sendPacket(Packet packet) {
        Client.INSTANCE.getMc().getNetHandler().getNetworkManager().sendPacket(packet);
    }
}
