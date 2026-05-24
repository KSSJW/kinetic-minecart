package com.kssjw.kineticminecart.client;

import com.kssjw.kineticminecart.client.manager.ClientHolderManager;
import com.kssjw.kineticminecart.client.manager.ClientLoadManager;
import com.kssjw.kineticminecart.manager.LoadManager;

import net.fabricmc.api.ClientModInitializer;

public class KineticMinecartClient implements ClientModInitializer {
    
    @Override
    public void onInitializeClient() {
        ClientLoadManager.init();
        if (ClientLoadManager.isAPIFound() && LoadManager.isAPIFound()) ClientHolderManager.init(); // 监听器初始化
    }
}