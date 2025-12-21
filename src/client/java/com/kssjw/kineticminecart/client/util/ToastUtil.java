package com.kssjw.kineticminecart.client.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.toasts.SystemToast;
import net.minecraft.network.chat.Component;

public class ToastUtil {

    private ToastUtil() {}
    
    public static void showToast(Component title, Component desc) {
        Minecraft mc = Minecraft.getInstance();

        // 创建一个 SystemToast
        SystemToast toast = new SystemToast(
            SystemToast.SystemToastId.NARRATOR_TOGGLE,
            title,
            desc
        );

        // 显示
        mc.getToasts().addToast(toast);
    }
}

    
