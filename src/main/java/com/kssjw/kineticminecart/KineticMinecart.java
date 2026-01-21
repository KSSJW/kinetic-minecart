package com.kssjw.kineticminecart;

import com.kssjw.kineticminecart.manager.HolderManager;
import com.kssjw.kineticminecart.manager.LoadManager;
import com.kssjw.kineticminecart.util.LogUtil;

import net.fabricmc.api.ModInitializer;

public class KineticMinecart implements ModInitializer {
    
    @Override
    public void onInitialize() {
        if (LoadManager.isAPIFound()) HolderManager.init(); // 注册配置与监听器
        LogUtil.print("Initialized.");
    }
}