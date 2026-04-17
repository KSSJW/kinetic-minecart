package com.kssjw.kineticminecart.manager;

import java.util.List;

import com.kssjw.kineticminecart.extension.config.ConfigValue;

public class ConfigManager {
    
    public static ConfigValue config = LoadManager.isAPIFound() ? me.shedaniel.autoconfig.AutoConfig.getConfigHolder(ConfigValue.class).getConfig() : null;

    /* */

    public static boolean isEnabled() {
        return !LoadManager.isAPIFound() || config.enabled;
    }

    /* */

    public static String getSelectedApplicaionMode() {
        if (LoadManager.isAPIFound()) {
            switch (config.selectedApplicaionMode) {
                case ConfigValue.applicaionMode.Collide:
                    return "Collide";
            
                case ConfigValue.applicaionMode.Radius:
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
        return !LoadManager.isAPIFound() || config.enabledDamage;
    }

    public static String getSelectedDamageMode() {
        if (LoadManager.isAPIFound()) {
            switch (config.selectedDamageMode) {
                case ConfigValue.damageMode.TieredDamage:
                    return "TieredDamage";
            
                case ConfigValue.damageMode.DirectlyKill:
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
        return !LoadManager.isAPIFound() || config.enabledKnock;
    }

    /* ------ */

    public static boolean isExcludePlayer() {
        return LoadManager.isAPIFound() && config.excludePlayer;
    }

    public static boolean isExcluePet() {
        return LoadManager.isAPIFound() && config.excludePet;
    }

    public static boolean isExcludePassenger() {
        return LoadManager.isAPIFound() && config.excludePassenger;
    }

    public static boolean isExcludeNamedEntity() {
        return LoadManager.isAPIFound() && config.excludeNamedEntity;
    }

    public static boolean isExcludItemEntity() {
        return LoadManager.isAPIFound() && config.excludeItemEntity;
    }

    /* ------ */

    public static boolean isEnabledExclusionList() {
        return LoadManager.isAPIFound() && config.enabledExclusionList;
    }

    public static List<String> getExclusionList() {
        return config.exclusionList;
    }
}