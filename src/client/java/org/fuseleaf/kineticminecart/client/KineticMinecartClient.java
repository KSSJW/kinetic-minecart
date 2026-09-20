package org.fuseleaf.kineticminecart.client;

import org.fuseleaf.kineticminecart.client.manager.ClientHolderManager;
import org.fuseleaf.kineticminecart.client.manager.ClientLoadManager;
import org.fuseleaf.kineticminecart.config.ConfigManager;

import net.fabricmc.api.ClientModInitializer;

public class KineticMinecartClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ClientLoadManager.init();
        if (ClientLoadManager.isAPIFound() && ConfigManager.isConfigAvailable()) {
            ClientHolderManager.init(); // 监听器初始化
        }
    }
}
