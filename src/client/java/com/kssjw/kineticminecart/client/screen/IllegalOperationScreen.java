package com.kssjw.kineticminecart.client.screen;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ConfirmScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class IllegalOperationScreen {

    // 拦截提示界面
    public static ConfirmScreen get(Screen parent) {
        return new ConfirmScreen(
            (result) -> MinecraftClient.getInstance().setScreen(parent), // 返回上一级菜单
            Text.translatable("screen.kinetic-minecart.IllegalOperationScreen.title"),
            Text.translatable("screen.kinetic-minecart.IllegalOperationScreen.desc"),
            Text.translatable("screen.kinetic-minecart.IllegalOperationScreen.yes"),
            Text.translatable("screen.kinetic-minecart.IllegalOperationScreen.no")
        );
    }
}
