package com.kssjw.kineticminecart.client.manager;

public class ClientLoadManager {
    public static boolean isClientAPIFound() {
        try {
            Class.forName("me.shedaniel.autoconfig.AutoConfigClient");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }
}