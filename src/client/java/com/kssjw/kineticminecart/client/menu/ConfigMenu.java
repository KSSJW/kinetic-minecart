package com.kssjw.kineticminecart.client.menu;

import com.kssjw.kineticminecart.client.screen.IllegalOperationScreen;
import com.kssjw.kineticminecart.config.ValueConfig;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;

import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ConfirmScreen;

public class ConfigMenu implements ModMenuApi {
    
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> {
            MinecraftClient mc = MinecraftClient.getInstance();

            boolean inWorld = mc.world != null && mc.player != null;
            boolean isMultiplayerWorld = inWorld && mc.getCurrentServerEntry() != null;
            
            if (isMultiplayerWorld) {
                ConfirmScreen confirmScreen = IllegalOperationScreen.get(parent);
                return confirmScreen;   // 多人模式拦截
            } else {
                return AutoConfig.getConfigScreen(ValueConfig.class, parent).get();
            }
        };
    }
}
