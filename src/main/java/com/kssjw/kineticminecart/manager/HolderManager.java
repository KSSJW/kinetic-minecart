package com.kssjw.kineticminecart.manager;

import com.kssjw.kineticminecart.config.ValueConfig;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigHolder;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;

public class HolderManager {

    public static ConfigHolder<ValueConfig> holder;
    
    public static void init() {

        // 配置与监听器初始化
        holder = AutoConfig.register(ValueConfig.class, GsonConfigSerializer::new);
    }
}