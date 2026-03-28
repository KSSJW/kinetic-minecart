package com.kssjw.kineticminecart.client;

import com.kssjw.kineticminecart.client.manager.ClientHolderManager;
import com.kssjw.kineticminecart.client.manager.ClientLoadManager;
import com.kssjw.kineticminecart.manager.LoadManager;

import net.fabricmc.api.ClientModInitializer;

public class KineticMinecartClient implements ClientModInitializer {
    
    @Override
    public void onInitializeClient() {
        if (LoadManager.isAPIFound() && ClientLoadManager.isClientAPIFound()) ClientHolderManager.init(); // 监听器初始化
    }
}