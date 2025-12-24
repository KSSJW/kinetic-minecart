package com.kssjw.kineticminecart.config;

import java.util.ArrayList;
import java.util.List;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import net.minecraft.text.Text;

@Config(name = "kinetic-minecart")
public class ValueConfig implements ConfigData {

    public static enum applicaionMode {
        Radius("option.kinetic-minecart.radius"),
        Collide("option.kinetic-minecart.collide");

        private final String key;
        applicaionMode(String key) { this.key = key;}

        @Override
        public String toString() {
            return Text.translatable(key).getString();
        }
        
    }

    // 启用开关
    @ConfigEntry.Category("general")
    public boolean enabled = true;

    // 应用模式
    @ConfigEntry.Category("general")
    @ConfigEntry.Gui.EnumHandler(option = ConfigEntry.Gui.EnumHandler.EnumDisplayOption.BUTTON)
    public applicaionMode selectedApplicaionMode = applicaionMode.Collide;
    
    // 启用伤害
    @ConfigEntry.Category("general")
    public boolean enabledImpact = true;

    // 启用击退
    @ConfigEntry.Category("general")
    public boolean enabledKnock = true;

    /* ------ */

    // 排除玩家
    @ConfigEntry.Category("types")
    public boolean excludePlayer = false;  

    // 排除宠物
    @ConfigEntry.Category("types")
    @ConfigEntry.Gui.Tooltip(count = 5)
    public boolean excludePet = false;

    // 排除乘客
    @ConfigEntry.Category("types")
    public boolean excludePassenger = false;

    // 排除被命名的实体
    @ConfigEntry.Category("types")
    public boolean excludeNamedEntity = false;

    // 排除掉落物
    @ConfigEntry.Category("types")
    public boolean excludeItemEntity = false;

    /* ------ */

    // 排除列表功能
    @ConfigEntry.Category("advanced")
    @ConfigEntry.Gui.Tooltip(count = 2)
    public boolean enabledExclusionList = false;

    // 排除列表
    @ConfigEntry.Category("advanced")
    @ConfigEntry.Gui.Tooltip(count = 7)
    public List<String> exclusionList = new ArrayList<>();
}
