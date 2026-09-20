package org.fuseleaf.kineticminecart;

import org.fuseleaf.kineticminecart.initializer.Initializer;

import net.fabricmc.api.ModInitializer;

public class KineticMinecart implements ModInitializer {

    @Override
    public void onInitialize() {
        Initializer.init();
    }
}
