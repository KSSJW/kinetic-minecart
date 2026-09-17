package org.fuseleaf.kineticminecart.client.manager;

import java.util.stream.Collectors;

import org.fuseleaf.kineticminecart.client.util.ToastUtil;
import org.fuseleaf.kineticminecart.manager.ConfigManager;
import org.fuseleaf.kineticminecart.manager.LoadManager;
import org.fuseleaf.kineticminecart.util.ExclusionListUtil;
import org.fuseleaf.kineticminecart.util.LogUtil;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;

public class ClientHolderManager {

    public static void init() {
        if (LoadManager.getHolder() == null) {
            return;
        }

        // 监听器，保存配置后触发
        LoadManager.getHolder().registerSaveListener((configHolder, config) -> {
            if (ConfigManager.isEnabledExclusionList()) {

                // 列表归一化
                config.exclusionList = config.exclusionList.stream()
                    .map(ExclusionListUtil::normalizeId)
                    .collect(Collectors.toList());

                // 显示提示
                Component title = Component.translatable("toast.kinetic-minecart.ExclusionList.title");
                Component desc = ExclusionListUtil.buildMessage(ConfigManager.getExclusionList());
                ToastUtil.toast(title, desc);
            }
            LogUtil.print("The configuration has been saved.");
            return InteractionResult.SUCCESS;
        });
    }
}
