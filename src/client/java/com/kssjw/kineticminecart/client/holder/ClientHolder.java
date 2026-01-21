package com.kssjw.kineticminecart.client.holder;

import java.util.stream.Collectors;

import com.kssjw.kineticminecart.client.toast.Toast;
import com.kssjw.kineticminecart.config.ValueConfig;
import com.kssjw.kineticminecart.manager.ConfigManager;
import com.kssjw.kineticminecart.manager.HolderManager;
import com.kssjw.kineticminecart.util.ExclusionListUtil;
import com.kssjw.kineticminecart.util.LogUtil;

import me.shedaniel.autoconfig.ConfigHolder;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;

public class ClientHolder {

    // 从服务端获取 holder
    public static ConfigHolder<ValueConfig> holder = HolderManager.holder;

    public static void init() {

        // 监听器，保存配置后触发
        holder.registerSaveListener((configHolder, config) -> {
            if (ConfigManager.isEnabledExclusionList() == true) {

                // 列表归一化
                config.exclusionList = config.exclusionList.stream()
                    .map(ExclusionListUtil::normalizeId)
                    .collect(Collectors.toList());

                // 显示提示
                Text title = Text.translatable("toast.kinetic-minecart.ExclusionList.title");
                Text desc = ExclusionListUtil.buildMessage(ConfigManager.getExclusionList());
                Toast.toast(title, desc);
            }
            LogUtil.print("The configuration has been saved.");
            return ActionResult.SUCCESS;
        });
    }
}