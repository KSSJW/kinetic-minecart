package com.kssjw.kineticminecart;

import com.kssjw.kineticminecart.manager.HolderManager;
import com.kssjw.kineticminecart.manager.LoadManager;
import com.kssjw.kineticminecart.util.DelayUtil;
import com.kssjw.kineticminecart.util.LogUtil;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;

public class KineticMinecart implements ModInitializer {
    
    @Override
    public void onInitialize() {
        ServerTickEvents.END_SERVER_TICK.register(
            (server) -> {
                DelayUtil.tick();
            }
        );
        if (LoadManager.isAPIFound()) HolderManager.init(); // 注册配置与监听器
        LogUtil.print("Initialized.");
    }
}