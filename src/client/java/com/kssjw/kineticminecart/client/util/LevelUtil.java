package com.kssjw.kineticminecart.client.util;

import com.kssjw.kineticminecart.client.screen.IllegalOperationScreen;
import com.kssjw.kineticminecart.config.ValueConfig;

import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.ConfirmScreen;
import net.minecraft.client.gui.screens.Screen;

public class LevelUtil {

    private LevelUtil() {}
    
    public static Screen judge(Screen parent) {
        Minecraft mc = Minecraft.getInstance();

        boolean inWorld = mc.level != null && mc.player != null;
        boolean isMultiplayerWorld = inWorld && !mc.hasSingleplayerServer();
        
        if (isMultiplayerWorld) {
            ConfirmScreen confirmScreen = IllegalOperationScreen.get(parent);
            return confirmScreen;   // 多人模式拦截
        } else {
            return AutoConfig.getConfigScreen(ValueConfig.class, parent).get();
        }
    }
}