package com.kssjw.kineticminecart.client;

import com.kssjw.kineticminecart.client.holder.ClientHolder;
import com.kssjw.kineticminecart.client.load.ClientLoad;
import com.kssjw.kineticminecart.manager.LoadManager;

import net.fabricmc.api.ClientModInitializer;

public class KineticMinecartClient implements ClientModInitializer {
    
    @Override
    public void onInitializeClient() {
        if (LoadManager.isAPIFound() && ClientLoad.isClientAPIFound()) ClientHolder.init(); // 监听器初始化
    }
}