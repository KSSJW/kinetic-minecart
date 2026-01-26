package com.kssjw.kineticminecart.manager;

import java.util.List;

import com.kssjw.kineticminecart.config.ValueConfig;

public class ConfigManager {
    
    public static ValueConfig config = LoadManager.isAPIFound() ? me.shedaniel.autoconfig.AutoConfig.getConfigHolder(ValueConfig.class).getConfig() : null;

    /* */

    public static boolean isEnabled() {
        return LoadManager.isAPIFound() ? config.enabled : true;
    }

    /* */

    public static String getSelectedApplicaionMode() {
        if (LoadManager.isAPIFound()) {
            switch (config.selectedApplicaionMode) {
                case ValueConfig.applicaionMode.Collide:
                    return "Collide";
            
                case ValueConfig.applicaionMode.Radius:
                    return "Radius";

                default:
                    return null;
            }
        } else {
            return "Collide";
        }
    }

    public static double getRadius() {
        return (double)config.intRadius / 10.0;
    }

    /* */

    public static boolean isEnabledDamage() {
        return LoadManager.isAPIFound() ? config.enabledDamage : true;
    }

    public static String getSelectedDamageMode() {
        if (LoadManager.isAPIFound()) {
            switch (config.selectedDamageMode) {
                case ValueConfig.damageMode.TieredDamage:
                    return "TieredDamage";
            
                case ValueConfig.damageMode.DirectlyKill:
                    return "DirectlyKill";

                default:
                    return null;
            }
        } else {
            return "TieredDamage";
        }
    }

    /* */

    public static boolean isEnabledKnock() {
        return LoadManager.isAPIFound() ? config.enabledKnock : true;
    }

    /* ------ */

    public static boolean isEnabledHUDSpeed() {
        return LoadManager.isAPIFound() ? config.enabledHUDSpeed : true;
    }

    /* ------ */

    public static boolean isExcludePlayer() {
        return LoadManager.isAPIFound() ? config.excludePlayer : false;
    }

    public static boolean isExcluePet() {
        return LoadManager.isAPIFound() ? config.excludePet : false;
    }

    public static boolean isExcludePassenger() {
        return LoadManager.isAPIFound() ? config.excludePassenger : false;
    }

    public static boolean isExcludeNamedEntity() {
        return LoadManager.isAPIFound() ? config.excludeNamedEntity : false;
    }

    public static boolean isExcludItemEntity() {
        return  LoadManager.isAPIFound() ?config.excludeItemEntity : false;
    }

    /* ------ */

    public static boolean isEnabledExclusionList() {
        return LoadManager.isAPIFound() ? config.enabledExclusionList : false;
    }

    public static List<String> getExclusionList() {
        return config.exclusionList;
    }
}