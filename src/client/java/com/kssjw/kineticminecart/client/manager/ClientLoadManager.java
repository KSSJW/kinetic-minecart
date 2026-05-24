package com.kssjw.kineticminecart.client.manager;

public class ClientLoadManager {
    private static boolean apiFound;

    public static void init() {
        try {
            Class.forName("me.shedaniel.autoconfig.AutoConfig");
            apiFound = true;
        } catch (ClassNotFoundException e) {
            apiFound = false;
        }
    }

    public static boolean isAPIFound() {
        return apiFound;
    }
}