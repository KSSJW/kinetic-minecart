package org.fuseleaf.kineticminecart.client.config;

public class ClientConfigManager {

    public static boolean isConfigAvailable() {
        try {
            Class.forName("me.shedaniel.autoconfig.AutoConfig");

            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }
}
