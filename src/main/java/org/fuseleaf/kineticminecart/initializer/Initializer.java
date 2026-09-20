package org.fuseleaf.kineticminecart.initializer;

import org.fuseleaf.kineticminecart.manager.LoadManager;
import org.fuseleaf.kineticminecart.util.DelayUtil;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;

public class Initializer {

    public static void init() {
        Config.init();
        Events.init();
    }

    private static class Config {

        private static void init() {
            LoadManager.init();
        }
    }

    private static class Events {

        private static void init() {
            ServerTickEvents.END_SERVER_TICK.register(
                (server) -> {
                    DelayUtil.tick();
                }
            );
        }
    }
}
