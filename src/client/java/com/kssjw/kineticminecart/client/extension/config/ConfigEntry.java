package com.kssjw.kineticminecart.client.extension.config;

import com.kssjw.kineticminecart.client.manager.ClientLoadManager;
import com.kssjw.kineticminecart.client.util.IllegalOperationScreenUtil;
import com.kssjw.kineticminecart.client.util.ToastUtil;
import com.kssjw.kineticminecart.extension.config.ConfigValue;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;

public class ConfigEntry implements ModMenuApi {
    
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> {
            Minecraft mc = Minecraft.getInstance();

            boolean inWorld = mc.level != null && mc.player != null;
            boolean isMultiplayerWorld = inWorld && mc.getCurrentServer() != null;
            
            if (isMultiplayerWorld) return IllegalOperationScreenUtil.get(parent);  // 多人模式拦截

            if (!ClientLoadManager.isClientAPIFound()) {
                Component title = Component.translatable("toast.kinetic-minecart.apinotfound.title");
                Component desc = Component.translatable("toast.kinetic-minecart.apinotfound.desc");
                ToastUtil.toast(title, desc);
                return null;
            } else {
                return me.shedaniel.autoconfig.AutoConfigClient.getConfigScreen(ConfigValue.class, parent).get();
            }
        };
    }
}