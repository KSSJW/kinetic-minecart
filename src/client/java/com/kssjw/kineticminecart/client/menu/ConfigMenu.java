package com.kssjw.kineticminecart.client.menu;

import com.kssjw.kineticminecart.client.load.ClientLoad;
import com.kssjw.kineticminecart.client.screen.IllegalOperationScreen;
import com.kssjw.kineticminecart.client.toast.Toast;
import com.kssjw.kineticminecart.config.ValueConfig;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ConfirmScreen;
import net.minecraft.text.Text;

public class ConfigMenu implements ModMenuApi {
    
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> {
            MinecraftClient mc = MinecraftClient.getInstance();

            boolean inWorld = mc.world != null && mc.player != null;
            boolean isMultiplayerWorld = inWorld && mc.getCurrentServerEntry() != null;
            
            // 多人模式拦截
            if (isMultiplayerWorld) {
                ConfirmScreen confirmScreen = IllegalOperationScreen.get(parent);
                return confirmScreen;
            }

            if (!ClientLoad.isClientAPIFound()) {
                Text title = Text.translatable("toast.kinetic-minecart.apinotfound.title");
                Text desc = Text.translatable("toast.kinetic-minecart.apinotfound.desc");
                Toast.toast(title, desc);
                return null;
            } else {
                return me.shedaniel.autoconfig.AutoConfigClient.getConfigScreen(ValueConfig.class, parent).get();
            }
        };
    }
}