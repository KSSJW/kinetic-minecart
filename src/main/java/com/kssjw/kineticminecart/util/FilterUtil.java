package com.kssjw.kineticminecart.util;

import com.kssjw.kineticminecart.manager.ConfigManager;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;

public class FilterUtil {

    private FilterUtil() {}

    // 排除宠物
    private static boolean isPet(Entity entity) {

        // 判断继承自TamableAnimal的类
        if (entity instanceof TamableAnimal) {
            TamableAnimal animal = (TamableAnimal)entity;
            if (
                animal.isTame()
                || animal.getOwner() != null
            ) return true;
        }

        // 判断继承自AbstractHorse的类
        if (entity instanceof AbstractHorse) {
            AbstractHorse horse = (AbstractHorse)entity;
            if (
                horse.isTamed()
                || horse.getOwner() != null
            ) {
                return true;
            }
        }
        return false;   // 默认不排除
    }

    // 排除列表
    private static boolean isInExclusionList(Entity entity) {

        // 获取实体的注册名
        String id = EntityType.getKey(entity.getType()).toString();

        if (ConfigManager.getExclusionList().contains(id) == true) {
            return true;
        } else return false;
    }

    // 主方法，判断是非被排除
    public static boolean isEntityExcluded(Entity entity) {
        if (
            ConfigManager.isExcludePlayer() == true && entity instanceof Player
            || ConfigManager.isExcluePet() == true && isPet(entity) == true
            || ConfigManager.isExcludePassenger() == true && entity.isPassenger() == true
            || ConfigManager.isExcludeNamedEntity() == true && entity.hasCustomName() == true
            || ConfigManager.isExcludItemEntity() == true && entity instanceof ItemEntity
            || ConfigManager.isEnabledExclusionList() == true && isInExclusionList(entity) == true
        ) {
            return true;
        } else return false;    // 默认不排除
    }
}
