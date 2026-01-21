package com.kssjw.kineticminecart.client.load;

public class ClientLoad {
    public static boolean isClientAPIFound() {
        boolean apiFound;

        try {
            Class.forName("me.shedaniel.autoconfig.AutoConfigClient");
            apiFound = true;
        } catch (ClassNotFoundException e) {
            apiFound = false;
        }

        if (apiFound) {
            return true;
        } else {
            return false;
        }
    }
}