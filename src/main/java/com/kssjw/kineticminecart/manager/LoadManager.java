package com.kssjw.kineticminecart.manager;

public class LoadManager {
    
    public static boolean isAPIFound() {
        try {
            Class.forName("me.shedaniel.autoconfig.AutoConfig");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }
}