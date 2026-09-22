package org.fuseleaf.kineticminecart.config;

import java.util.List;

import org.fuseleaf.kineticminecart.extension.config.ConfigData;
import org.fuseleaf.kineticminecart.extension.config.ConfigEnum;

import org.jspecify.annotations.NonNull;

import me.shedaniel.autoconfig.ConfigHolder;

public class ConfigManager {

    private static ConfigData config;

    private static ConfigHolder<@NonNull ConfigData> holder;

    public static void setConfig(ConfigHolder<@NonNull ConfigData> configHolder) {
        if (configHolder != null) {
            config = configHolder.getConfig();
            holder = configHolder;
        }
    }

    public static boolean isConfigAvailable() {
        return config != null;
    }

    public static ConfigHolder<@NonNull ConfigData> getConfigHolder() {
        return holder;
    }

    /* # Get Configuration */

    /* ## General */

    public static boolean isEnabled() {
        return !ConfigManager.isConfigAvailable() || config.enabled;
    }

    public static boolean isOverrideCollision() {
        return !ConfigManager.isConfigAvailable() || config.overrideCollision;
    }

    public static boolean isOverrideRiding() {
        return !ConfigManager.isConfigAvailable() || config.overrideRiding;
    }

    public static ConfigEnum.ApplicationMode getSelectedApplicationMode() {
        return isConfigAvailable() ? config.selectedApplicationMode : null;
    }

    public static double getRadius() {
        return (double)config.intRadius / 10.0;
    }

    public static boolean isEnabledDamage() {
        return !ConfigManager.isConfigAvailable() || config.enabledDamage;
    }

    public static ConfigEnum.DamageMode getSelectedDamageMode() {
        return isConfigAvailable() ? config.selectedDamageMode : null;
    }

    public static boolean isEnabledKnock() {
        return !ConfigManager.isConfigAvailable() || config.enabledKnock;
    }

    /* ## Types */

    public static boolean isExcludePlayer() {
        return ConfigManager.isConfigAvailable() && config.excludePlayer;
    }

    public static boolean isExcluePet() {
        return ConfigManager.isConfigAvailable() && config.excludePet;
    }

    public static boolean isExcludePassenger() {
        return !ConfigManager.isConfigAvailable() || config.excludePassenger;
    }

    public static boolean isExcludeNamedEntity() {
        return ConfigManager.isConfigAvailable() && config.excludeNamedEntity;
    }

    public static boolean isExcludItemEntity() {
        return ConfigManager.isConfigAvailable() && config.excludeItemEntity;
    }

    /* ## Advanced */

    public static boolean isEnabledExclusionList() {
        return ConfigManager.isConfigAvailable() && config.enabledExclusionList;
    }

    public static List<String> getExclusionList() {
        return config.exclusionList;
    }
}
