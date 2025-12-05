package com.kssjw.kineticminecart;

import com.kssjw.kineticminecart.manager.HolderManager;
import com.kssjw.kineticminecart.util.LogUtil;

import net.fabricmc.api.ModInitializer;

public class KineticMinecart implements ModInitializer {
    
    @Override
    public void onInitialize() {

        // 注册配置与监听器
        HolderManager.init();
        
        LogUtil.print("Initialized.");
    }
}