package org.fuseleaf.kineticminecart.client;

import org.fuseleaf.kineticminecart.client.manager.ClientHolderManager;
import org.fuseleaf.kineticminecart.client.manager.ClientLoadManager;
import org.fuseleaf.kineticminecart.manager.LoadManager;

import net.fabricmc.api.ClientModInitializer;

public class KineticMinecartClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ClientLoadManager.init();
        if (ClientLoadManager.isAPIFound() && LoadManager.isAPIFound()) {
            ClientHolderManager.init(); // 监听器初始化
        }
    }
}
