package com.kssjw.kineticminecart;

import com.kssjw.kineticminecart.manager.LoadManager;
import com.kssjw.kineticminecart.util.DelayUtil;

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
        LoadManager.init();
    }
}