package com.kssjw.kineticminecart.client.util;

import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.toasts.SystemToast;
import net.minecraft.client.gui.components.toasts.ToastManager;
import net.minecraft.network.chat.Component;

public class ToastUtil {

    private ToastUtil() {}

    public static void toast(Component title, Component description) {
        if (FabricLoader.getInstance().getEnvironmentType() != EnvType.CLIENT) return;

        ToastManager toastManager = Minecraft.getInstance().getToastManager();
        SystemToast.addOrUpdate(
            toastManager,
            SystemToast.SystemToastId.NARRATOR_TOGGLE,
            title,
            description
        );
    }
}