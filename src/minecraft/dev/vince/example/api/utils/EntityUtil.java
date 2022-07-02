package dev.vince.example.api.utils;

import dev.vince.example.Client;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;

import java.util.Locale;

public class EntityUtil {
    public EntityLivingBase getClosestEntity(double range) {
        EntityLivingBase target = null;
        for (Entity entity : Minecraft.getMinecraft().theWorld.loadedEntityList) {
            if (canAttack(entity)) {
                double currentDist = Client.INSTANCE.getMc().thePlayer.getDistanceToEntity(entity);
                if (currentDist <= range) {
                    range = currentDist;
                    target = (EntityLivingBase) entity;
                }
            }
        }
        return target;
    }

    public boolean canAttack(Entity entity) {
        if ((!entity.isInvisible())) {
            return entity != Client.INSTANCE.getMc().thePlayer && entity.isEntityAlive() && Client.INSTANCE.getMc().thePlayer != null && Minecraft.getMinecraft().theWorld != null && Client.INSTANCE.getMc().thePlayer.ticksExisted > 30 && entity.ticksExisted > 15;
        } else {
            return false;
        }
    }

    public static float getPitchChangeToEntityHitpoint(Entity entity, String hitpoint) {
        double deltaX = entity.posX - Client.INSTANCE.getMc().thePlayer.posX;
        double deltaZ = entity.posZ - Client.INSTANCE.getMc().thePlayer.posZ;
        double offset = 1.6;
        switch (hitpoint.toLowerCase(Locale.ROOT)) { //leaked diablo feature (crazy)
            case "head":
                break;
            case "chest":
                offset = 2;
                break;
            case "lowerchest":
                offset = 2.275;
                break;
            case "stomach":
                offset = 2.475;
                break;
            case "cock":
                offset = 2.655;
                break;
            case "legs":
                offset = 2.825;
                break;
            case "feet":
                offset = 3.1;
                break;
            case "toes":
                offset = 3.225;
                break;

        }
        double deltaY = entity.posY - offset + entity.getEyeHeight() - Client.INSTANCE.getMc().thePlayer.posY;
        double distanceXZ = MathHelper.sqrt_double(deltaX * deltaX + deltaZ * deltaZ);
        double pitchToEntity = -Math.toDegrees(Math.atan(deltaY / distanceXZ));
        return Double.isNaN(Client.INSTANCE.getMc().thePlayer.rotationPitch - pitchToEntity) ? 0.0f : -MathHelper.wrapAngleTo180_float(Client.INSTANCE.getMc().thePlayer.rotationPitch - (float) pitchToEntity);
    }

    public static float getYawChangeToEntity(Entity entity) {
        double deltaX = entity.posX - Client.INSTANCE.getMc().thePlayer.posX;
        double deltaZ = entity.posZ - Client.INSTANCE.getMc().thePlayer.posZ;
        double yawToEntity;
        double v = Math.toDegrees(Math.atan(deltaZ / deltaX));
        if (deltaZ < 0.0 && deltaX < 0.0) {
            yawToEntity = 90.0 + v;
        } else if (deltaZ < 0.0 && deltaX > 0.0) {
            yawToEntity = -90.0 + v;
        } else {
            yawToEntity = Math.toDegrees(-Math.atan(deltaX / deltaZ));
        }
        return Double.isNaN(Client.INSTANCE.getMc().thePlayer.rotationYaw - yawToEntity) ? 0.0f : MathHelper.wrapAngleTo180_float(-(Client.INSTANCE.getMc().thePlayer.rotationYaw - (float) yawToEntity));
    }

    public float[] getAnglesFromHitpoint(Entity e, String hitpoint) {
        return new float[]{getYawChangeToEntity(e) + Client.INSTANCE.getMc().thePlayer.rotationYaw, getPitchChangeToEntityHitpoint(e, hitpoint) + Client.INSTANCE.getMc().thePlayer.rotationPitch};
    }
}
