package com.kssjw.kineticminecart.util;

import com.kssjw.kineticminecart.manager.ConfigManager;

import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.passive.AbstractHorseEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.Registries;

public class FilterUtil {

    private FilterUtil() {}

    // 排除宠物
    private static boolean isPet(Entity entity) {

        // 判断继承自TameableEntity的类
        if (entity instanceof TameableEntity) {
            TameableEntity animal = (TameableEntity)entity;
            if (animal.isTamed() || animal.getOwner() != null) return true;
        }

        // 判断继承自AbstractHorseEntity的类
        if (entity instanceof AbstractHorseEntity) {
            AbstractHorseEntity horse = (AbstractHorseEntity)entity;
            if (horse.isTame() || horse.getOwner() != null) {
                return true;
            }
        }

        return false;   // 默认不排除
    }

    // 排除列表
    private static boolean isInExclusionList(Entity entity) {

        // 获取实体的注册名
        String id = Registries.ENTITY_TYPE.getId(entity.getType()).toString();

        if (ConfigManager.getExclusionList().contains(id) == true) {
            return true;
        } else return false;
    }

    // 主方法，判断是否被排除
    public static boolean isEntityExcluded(Entity entity) {
        if (
            ConfigManager.isExcludePlayer() == true && entity instanceof PlayerEntity
            || ConfigManager.isExcluePet() == true && isPet(entity) == true
            || ConfigManager.isExcludePassenger() == true && entity.hasVehicle() == true
            || ConfigManager.isExcludeNamedEntity() == true && entity.hasCustomName() == true
            || ConfigManager.isExcludItemEntity() == true && entity instanceof ItemEntity
            || ConfigManager.isEnabledExclusionList() == true && isInExclusionList(entity) == true
        ) {
            return true;
        } else return false;    // 默认不排除
    }
}