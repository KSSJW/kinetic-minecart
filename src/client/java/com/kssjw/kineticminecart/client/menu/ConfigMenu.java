package com.kssjw.kineticminecart.client.menu;

import com.kssjw.kineticminecart.client.screen.IllegalOperationScreen;
import com.kssjw.kineticminecart.config.ValueConfig;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;

import me.shedaniel.autoconfig.AutoConfigClient;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.ConfirmScreen;

public class ConfigMenu implements ModMenuApi {
    
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> {
            Minecraft mc = Minecraft.getInstance();

            boolean inWorld = mc.level != null && mc.player != null;
            boolean isMultiplayerWorld = inWorld && !mc.hasSingleplayerServer();
            
            if (isMultiplayerWorld) {
                ConfirmScreen confirmScreen = IllegalOperationScreen.get(parent);
                return confirmScreen;   // 多人模式拦截
            } else {
                return AutoConfigClient.getConfigScreen(ValueConfig.class, parent).get();
            }
        };
    }
}
