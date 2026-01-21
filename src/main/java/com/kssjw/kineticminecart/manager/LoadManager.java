package com.kssjw.kineticminecart.manager;

public class LoadManager {
    
    public static boolean isAPIFound() {
        boolean apiFound;

        try {
            Class.forName("me.shedaniel.autoconfig.AutoConfig");
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