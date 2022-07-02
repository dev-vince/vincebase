package dev.vince.example.impl.event;

import dev.vince.example.api.event.Event;
import net.minecraft.network.Packet;

@SuppressWarnings("rawtypes")
public final class PacketEvent extends Event {
    private Packet packet;

    public PacketEvent(Packet packet){
        this.packet = packet;
    }

    public Packet getPacket(){
        return packet;
    }
    public void setPacket(Packet p){
        this.packet = p;
    }
}

