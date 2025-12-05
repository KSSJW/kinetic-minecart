package com.kssjw.kineticminecart.client;

import com.kssjw.kineticminecart.client.holder.ClientHolder;

import net.fabricmc.api.ClientModInitializer;

public class KineticMinecartClient implements ClientModInitializer {
    
    @Override
    public void onInitializeClient() {

        // 监听器初始化
        ClientHolder.init();
    }
}