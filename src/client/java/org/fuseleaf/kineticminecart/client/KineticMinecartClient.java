package org.fuseleaf.kineticminecart.client;

import org.fuseleaf.kineticminecart.client.initializer.ClientInitializer;

import net.fabricmc.api.ClientModInitializer;

public class KineticMinecartClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ClientInitializer.init();
    }
}
