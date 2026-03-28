package com.kssjw.kineticminecart.manager;

import com.kssjw.kineticminecart.extension.config.ConfigValue;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigHolder;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;

public class HolderManager {

    private static ConfigHolder<ConfigValue> holder;
    
    public static void init() {
        holder = AutoConfig.register(ConfigValue.class, GsonConfigSerializer::new);   // 配置与监听器初始化
    }

    public static ConfigHolder<ConfigValue> getHolder() {
        return holder;
    }
}