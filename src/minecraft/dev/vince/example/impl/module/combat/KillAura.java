package dev.vince.example.impl.module.combat;

import best.azura.eventbus.core.EventPriority;
import best.azura.eventbus.handler.EventHandler;
import best.azura.eventbus.handler.Listener;
import dev.vince.example.Client;
import dev.vince.example.api.event.EventType;
import dev.vince.example.api.module.Module;
import dev.vince.example.api.module.ModuleCategory;
import dev.vince.example.api.utils.EntityUtil;
import dev.vince.example.api.utils.MathUtil;
import dev.vince.example.api.utils.PacketUtil;
import dev.vince.example.api.utils.Stopwatch;
import dev.vince.example.impl.event.UpdateEvent;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.play.client.C02PacketUseEntity;
import org.lwjgl.input.Keyboard;

public final class KillAura extends Module {
    private final Stopwatch stopwatch = new Stopwatch();
    private EntityLivingBase target;

    public KillAura() {
        super("KillAura", "Kills all players in your radius", ModuleCategory.COMBAT, Keyboard.KEY_R);
    }

    final double range = 4.2;

    @EventHandler(EventPriority.HIGHEST)
    public final Listener<UpdateEvent> updateListener = e -> {
        target = EntityUtil.getClosestEntity(range);

        if (target != null) {
            if (e.getType() == EventType.PRE) {
                rotate(e, target);
            } else if (e.getType() == EventType.POST) {
                attack(target);
            }
        }
    };

    private void rotate(UpdateEvent e,EntityLivingBase entity) {
        final float[] rotations = EntityUtil.getAnglesFromHitpoint(entity,"Head");

        final float yaw = rotations[0];
        final float pitch = rotations[1];

        e.setYaw(yaw);
        e.setPitch(pitch);
        Client.INSTANCE.getMc().thePlayer.rotationYawHead = yaw;
        Client.INSTANCE.getMc().thePlayer.rotationPitchHead = pitch;
        Client.INSTANCE.getMc().thePlayer.renderYawOffset = yaw;
    }

    private void attack(EntityLivingBase e) {
        if (e != null) {
            if (stopwatch.hasReached(1000 / MathUtil.getRandInt(9, 14))) {
                Client.INSTANCE.getMc().thePlayer.swingItem();
                PacketUtil.sendPacketNoEvent(new C02PacketUseEntity(e, C02PacketUseEntity.Action.ATTACK));
                stopwatch.reset();
            }
        }
    }

    public EntityLivingBase getTarget() {
        return target;
    }
}
