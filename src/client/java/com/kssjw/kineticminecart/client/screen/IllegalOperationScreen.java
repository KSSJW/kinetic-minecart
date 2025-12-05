package com.kssjw.kineticminecart.client.screen;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.ConfirmScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class IllegalOperationScreen {

    // 拦截提示界面
    public static ConfirmScreen get(Screen parent) {
        return new ConfirmScreen(
            (result) -> Minecraft.getInstance().setScreen(parent), // 返回上一级菜单
            Component.translatable("screen.kinetic-minecart.IllegalOperationScreen.title"),
            Component.translatable("screen.kinetic-minecart.IllegalOperationScreen.desc"),
            Component.translatable("screen.kinetic-minecart.IllegalOperationScreen.yes"),
            Component.translatable("screen.kinetic-minecart.IllegalOperationScreen.no")
        );
    }
}
