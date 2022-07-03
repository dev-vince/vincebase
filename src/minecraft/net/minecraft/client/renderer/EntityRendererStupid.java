package net.minecraft.client.renderer;

import java.util.concurrent.Callable;
import net.minecraft.client.Minecraft;

class EntityRendererStupid implements Callable
{
    final EntityRenderer field_90025_c;
    private static final String __OBFID = "CL_00000948";

    EntityRendererStupid(EntityRenderer p_i46419_1_)
    {
        this.field_90025_c = p_i46419_1_;
    }

    public String call() throws Exception
    {
        return Minecraft.getMinecraft().currentScreen.getClass().getCanonicalName();
    }
}
