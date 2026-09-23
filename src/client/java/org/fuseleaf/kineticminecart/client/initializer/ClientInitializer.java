package org.fuseleaf.kineticminecart.client.initializer;

import java.util.stream.Collectors;

import org.fuseleaf.kineticminecart.client.util.ToastUtil;
import org.fuseleaf.kineticminecart.config.ConfigManager;
import org.fuseleaf.kineticminecart.entity.ExclusionList;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;

public class ClientInitializer {

    public static void init() {
        Config.init();
    }

    private static class Config {

        private static void init() {
            try {
                Class.forName("me.shedaniel.autoconfig.AutoConfig");

                ConfigManager.getConfigHolder().registerSaveListener((configHolder, config) -> {
                    if (ConfigManager.isEnabledExclusionList()) {

                        config.exclusionList = config.exclusionList.stream()
                            .map(ExclusionList::normalizeId)
                            .collect(Collectors.toList());

                        Component title = Component.translatable("toast.kinetic-minecart.ExclusionList.title");
                        Component desc = ExclusionList.buildMessage(ConfigManager.getExclusionList());
                        ToastUtil.toast(title, desc);
                    }

                    return InteractionResult.SUCCESS;
                });
            } catch (ClassNotFoundException e) {}
        }
    }
}
