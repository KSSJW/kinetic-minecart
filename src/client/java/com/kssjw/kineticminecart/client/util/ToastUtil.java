package com.kssjw.kineticminecart.client.util;

import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.toast.SystemToast;
import net.minecraft.client.toast.ToastManager;
import net.minecraft.text.Text;

public class ToastUtil {

    private ToastUtil() {}

    public static void toast(Text title, Text description) {
        if (FabricLoader.getInstance().getEnvironmentType() != EnvType.CLIENT) return;

        ToastManager toastManager = MinecraftClient.getInstance().getToastManager();
        SystemToast.show(
            toastManager,
            SystemToast.Type.NARRATOR_TOGGLE,
            title,
            description
        );
    }
}