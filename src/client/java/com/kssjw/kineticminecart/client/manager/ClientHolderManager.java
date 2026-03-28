package com.kssjw.kineticminecart.client.manager;

import java.util.stream.Collectors;

import com.kssjw.kineticminecart.client.util.ToastUtil;
import com.kssjw.kineticminecart.manager.ConfigManager;
import com.kssjw.kineticminecart.manager.HolderManager;
import com.kssjw.kineticminecart.util.ExclusionListUtil;
import com.kssjw.kineticminecart.util.LogUtil;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;

public class ClientHolderManager {

    public static void init() {
        if (HolderManager.getHolder() == null) return;

        // 监听器，保存配置后触发
        HolderManager.getHolder().registerSaveListener((configHolder, config) -> {
            if (ConfigManager.isEnabledExclusionList() == true) {

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