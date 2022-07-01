package dev.vince.example.impl.event;

import best.azura.eventbus.events.CancellableEvent;
import dev.vince.example.api.event.Event;

public class UpdateEvent extends Event {
    private double posX, posY, posZ;
    private boolean onGround;
    private float rotationYaw, rotationPitch;

    public UpdateEvent(double posX, double posY, double posZ, float rotationYaw, float rotationPitch, boolean onGround) {
        this.posX = posX;
        this.posY = posY;
        this.posZ = posZ;
        this.rotationYaw = rotationYaw;
        this.rotationPitch = rotationPitch;
        this.onGround = onGround;
    }

    public final void setX(double newPos){
        this.posX = newPos;
    }

    public final void setY(double newPos){
        this.posY = newPos;
    }

    public final void setZ(double newPos){
        this.posZ = newPos;
    }

    public final void setYaw(float newYaw){
        this.rotationYaw = newYaw;
    }

    public final void setPitch(float newPitch){
        this.rotationPitch = newPitch;
    }

    public final void setOnGround(boolean newGround){
        this.onGround = newGround;
    }

    public final double getPosX(){
        return this.posX;
    }

    public final double getPosY(){
        return this.posY;
    }

    public final double getPosZ(){
        return this.posZ;
    }

    public final float getRotationYaw(){
        return this.rotationYaw;
    }

    public final float getRotationPitch(){
        return this.rotationPitch;
    }

    public final boolean isOnGround(){
        return this.onGround;
    }
}

