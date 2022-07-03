package dev.vince.example.impl.module.render;

import best.azura.eventbus.handler.EventHandler;
import best.azura.eventbus.handler.Listener;
import dev.vince.example.Client;
import dev.vince.example.api.module.Module;
import dev.vince.example.api.module.ModuleCategory;
import dev.vince.example.api.utils.RenderUtil;
import dev.vince.example.api.utils.Stopwatch;
import dev.vince.example.impl.event.KeyEvent;
import dev.vince.example.impl.event.OverlayEvent;
import org.lwjgl.input.Keyboard;

import java.util.List;

public class TabGUI extends Module {
    public TabGUI() {
        super("TabGUI", "TabGUI to toggle modules", ModuleCategory.RENDER, 0);
        this.setEnabled(true);
        this.setHidden(true);

        for(ModuleCategory cat : ModuleCategory.values()) {
            float newWidth = mc.fontRendererObj.getStringWidth(cat.toString())+12f;
            System.out.printf("%s width is %f%n", cat, newWidth);
            if(newWidth > this.width) this.width = newWidth;
        }

    }

    private final Stopwatch lastSwitchTimer = new Stopwatch();
    private int currentCategoryIndex = 0;
    private int currentModuleIndex = 0;
    private boolean categoryExpanded = false;
    private boolean moduleExpanded = false;

    private final float baseY = 6f + mc.fontRendererObj.FONT_HEIGHT;
    private final float baseX = 3f;
    private float width = 12f;
    private final float height = 4 + (ModuleCategory.values().length * 10);

    @EventHandler
    public final Listener<OverlayEvent> overlayEventListener = event -> {
        RenderUtil.drawRectWidth(baseX, baseY, width, height, 0xAA252525);
        float stringPosY = baseY + 3f;
        if(!categoryExpanded)
            RenderUtil.drawRectWidth(baseX, baseY + 2  + (currentCategoryIndex * 10), width, 10, 0xAAA04040);
        int index = 0;
        for(ModuleCategory cat : ModuleCategory.values()) {
            boolean selected = index == currentCategoryIndex;
            float posX = baseX + (selected ? 3 + Math.min(1,(lastSwitchTimer.getTime()/100f))*3f : 3f);
            mc.fontRendererObj.drawStringWithShadow(cat.toString(), posX, stringPosY, 0xFFDADADA);

            if(categoryExpanded && selected) {
                List<Module> moduleList = Client.INSTANCE.getModuleManager().getModulesByCategory(cat);
                float moduleHeight = 4f + (moduleList.size() * 10);
                float maxModuleWidth = 0f;
                for (Module mod : moduleList) {
                    float currentModuleWidth = mc.fontRendererObj.getStringWidth(mod.getName()) + 6f;
                    if(currentModuleWidth > maxModuleWidth) maxModuleWidth = currentModuleWidth;
                }
                RenderUtil.drawRectWidth(posX+width-6f, stringPosY-3f, maxModuleWidth, moduleHeight, 0xAA252525);
                float moduleStringPosY = stringPosY;
                float moduleIndex = 0;
                for(Module mod : moduleList) {
                    boolean moduleSelected = moduleIndex == currentModuleIndex;
                    if(moduleSelected) {
                        RenderUtil.drawRectWidth(posX + width - 6f,
                                moduleStringPosY - 1f,
                                maxModuleWidth,
                                10,
                                0xAAA04040);
                    }
                    mc.fontRendererObj.drawStringWithShadow(mod.getName(), posX+width-3f, moduleStringPosY, 0xFFDADADA);
                    moduleStringPosY += mc.fontRendererObj.FONT_HEIGHT+1;
                    moduleIndex++;
                }
            }
            
            stringPosY += mc.fontRendererObj.FONT_HEIGHT+1;
            index++;
        }



    };

    @EventHandler
    public final Listener<KeyEvent> keyEventListener = event -> {
        int key = event.getKey();
        if(key == Keyboard.KEY_DOWN || key == Keyboard.KEY_NUMPAD2) {
            if(!categoryExpanded) {
                currentCategoryIndex++;
                lastSwitchTimer.reset();
            } else if(!moduleExpanded) {
                currentModuleIndex++;
            }
        } else if (key == Keyboard.KEY_UP || key == Keyboard.KEY_NUMPAD8) {
            if(!categoryExpanded) {
                currentCategoryIndex--;
                lastSwitchTimer.reset();
            } else if(!moduleExpanded) {
                currentModuleIndex--;
            }
        } else if (key == Keyboard.KEY_LEFT || key == Keyboard.KEY_NUMPAD4) {
            if(categoryExpanded) {
                categoryExpanded = false;
                currentModuleIndex = 0;
            }
        } else if (key == Keyboard.KEY_RIGHT || key == Keyboard.KEY_NUMPAD6) {
            if(!categoryExpanded) {
                if(!(Client.INSTANCE.getModuleManager()
                        .getModulesByCategory(ModuleCategory.values()[currentCategoryIndex]).size() == 0))
                    categoryExpanded = true;
            }
        } else if (key == Keyboard.KEY_RETURN && categoryExpanded) {
            Client.INSTANCE.getModuleManager()
                    .getModulesByCategory(ModuleCategory.values()[currentCategoryIndex]).get(currentModuleIndex).toggle();
        }


        int maxCategoryIndex = ModuleCategory.values().length-1;
        if(currentCategoryIndex < 0) {
            currentCategoryIndex = maxCategoryIndex;
        } else if (currentCategoryIndex > maxCategoryIndex) {
            currentCategoryIndex = 0;
        }
        if(categoryExpanded) {
            int maxModuleIndex = Client.INSTANCE.getModuleManager()
                    .getModulesByCategory(ModuleCategory.values()[currentCategoryIndex])
                    .size()-1;
            if(currentModuleIndex < 0) {
                currentModuleIndex = maxModuleIndex;
            } else if (currentModuleIndex > maxModuleIndex) {
                currentModuleIndex = 0;
            }
        }
    };
}
