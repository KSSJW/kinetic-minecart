package com.kssjw.kineticminecart.config;

import java.util.ArrayList;
import java.util.List;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = "kinetic-minecart")
public class ValueConfig implements ConfigData {

    // 启用开关
    @ConfigEntry.Category("general")
    public boolean enabled = true;

    // 排除玩家
    @ConfigEntry.Category("types")
    public boolean excludePlayer = false;  

    // 排除宠物
    @ConfigEntry.Category("types")
    @ConfigEntry.Gui.Tooltip(count = 5)
    public boolean excludePet = false;

    // 排除被命名的实体
    @ConfigEntry.Category("types")
    public boolean excludeNamedEntity = false;

    // 排除掉落物
    @ConfigEntry.Category("types")
    public boolean excludeItemEntity = false;

    // 排除列表功能
    // TODO
    @ConfigEntry.Category("types")
    @ConfigEntry.Gui.Tooltip(count = 2)
    public boolean enabledExclusionList = false;

    // 排除列表
    @ConfigEntry.Category("types")
    @ConfigEntry.Gui.Tooltip(count = 7)
    public List<String> exclusionList = new ArrayList<>();
}
