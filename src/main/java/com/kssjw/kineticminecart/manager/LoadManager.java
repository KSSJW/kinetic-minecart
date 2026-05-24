package com.kssjw.kineticminecart.manager;

import org.jspecify.annotations.NonNull;

import com.kssjw.kineticminecart.extension.config.ConfigValue;

import me.shedaniel.autoconfig.ConfigHolder;

public class LoadManager {
    
    private static boolean apiFound;
    private static ConfigHolder<@NonNull ConfigValue> holder;

    public static void init() {
        try {
            Class.forName("me.shedaniel.autoconfig.AutoConfig");
            apiFound = true;
        } catch (ClassNotFoundException e) {
            apiFound = false;
        }

        if (apiFound == true) holder = me.shedaniel.autoconfig.AutoConfig.register(ConfigValue.class, me.shedaniel.autoconfig.serializer.GsonConfigSerializer::new); // 替代 AutoConfig.register(ValueConfig.class, GsonConfigSerializer::new);
    }

    public static boolean isAPIFound() {
        return apiFound;
    }

    public static ConfigHolder<@NonNull ConfigValue> getHolder() {
        return holder;
    }
}