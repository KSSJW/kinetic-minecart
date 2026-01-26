package com.kssjw.kineticminecart.util;

import com.kssjw.kineticminecart.manager.ConfigManager;

import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.passive.AbstractHorseEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
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

        return false;
    }

    // 排除列表
    private static boolean isInExclusionList(Entity entity) {
        
        String id = Registries.ENTITY_TYPE.getId(entity.getType()).toString();  // 获取实体的注册名

        if (ConfigManager.getExclusionList().contains(id)) {
            return true;
        } else return false;
    }


    // 默认排除
    private static boolean isInDefaultExclusion(AbstractMinecartEntity minecart, Entity entity) {
        if (entity instanceof AbstractMinecartEntity    // 避免矿车被"撞死"，哈哈哈矿车也是生物(
            || entity.getVehicle() == minecart  // 避免被自己坐的矿车干掉
        ) {
            return true;
        } else {
            return false;
        }
    }

    // 判断是否被用户排除
    private static boolean isInCustomExclusion(Entity entity) {
        if (ConfigManager.isExcludePlayer() && entity instanceof PlayerEntity
            || ConfigManager.isExcluePet() && isPet(entity)
            || ConfigManager.isExcludePassenger() && entity.hasVehicle()
            || ConfigManager.isExcludeNamedEntity() && entity.hasCustomName()
            || ConfigManager.isExcludItemEntity() && entity instanceof ItemEntity
            || ConfigManager.isEnabledExclusionList() && isInExclusionList(entity)
        ) {
            return true;
        } else return false;
    }

    // 总方法
    public static boolean isExclued(AbstractMinecartEntity minecart, Entity entity) {
        return isInDefaultExclusion(minecart, entity) || isInCustomExclusion(entity) ? true : false;
    }
}