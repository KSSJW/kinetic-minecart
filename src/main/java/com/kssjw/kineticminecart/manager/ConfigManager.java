package com.kssjw.kineticminecart.manager;

import java.util.List;

import com.kssjw.kineticminecart.config.ValueConfig;

import me.shedaniel.autoconfig.AutoConfig;

public class ConfigManager {
    
    public static ValueConfig config = AutoConfig.getConfigHolder(ValueConfig.class).getConfig();

    public static boolean isEnabled() {
        return config.enabled;
    }

    public static boolean isEnabledImpact() {
        return config.enabledImpact;
    }

    public static boolean isEnabledKnock() {
        return config.enabledKnock;
    }

    public static ValueConfig.applicaionMode getSelectedApplicaionMode() {
        return config.selectedApplicaionMode;
    }

    /* ------ */

    public static boolean isExcludePlayer() {
        return config.excludePlayer;
    }

    public static boolean isExcluePet() {
        return config.excludePet;
    }

    public static boolean isExcludePassenger() {
        return config.excludePassenger;
    }

    public static boolean isExcludeNamedEntity() {
        return config.excludeNamedEntity;
    }

    public static boolean isExcludItemEntity() {
        return config.excludeItemEntity;
    }

    /* ------ */

    public static boolean isEnabledExclusionList() {
        return config.enabledExclusionList;
    }

    public static List<String> getExclusionList() {
        return config.exclusionList;
    }
}
