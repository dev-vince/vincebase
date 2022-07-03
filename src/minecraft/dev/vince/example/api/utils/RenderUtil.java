package dev.vince.example.api.utils;

import net.minecraft.client.gui.Gui;

public class RenderUtil {

    public static void drawRectWidth(float posX, float posY, float width, float height, int color) {
        Gui.drawRect(posX, posY, posX+width, posY+height, color);
    }

}
