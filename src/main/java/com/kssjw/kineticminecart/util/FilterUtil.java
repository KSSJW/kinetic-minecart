package com.kssjw.kineticminecart.util;

import com.kssjw.kineticminecart.manager.ConfigManager;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.animal.equine.AbstractHorse;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.minecart.AbstractMinecart;

public class FilterUtil {

    private FilterUtil() {}

    // 排除宠物
    private static boolean isPet(Entity entity) {

        // 判断继承自TameableEntity的类
        if (entity instanceof TamableAnimal animal) {
            return animal.isTame() || animal.getOwner() != null;
        }

        // 判断继承自AbstractHorseEntity的类
        if (entity instanceof AbstractHorse horse) {
            return horse.isTamed() || horse.getOwner() != null;
        }

        return false;
    }

    // 排除列表
    private static boolean isInExclusionList(Entity entity) {
        
        String id = BuiltInRegistries.ENTITY_TYPE.getKey(entity.getType()).toString();  // 获取实体的注册名

        return ConfigManager.getExclusionList().contains(id);
    }


    // 默认排除
    private static boolean isInDefaultExclusion(AbstractMinecart minecart, Entity entity) {
        return entity instanceof AbstractMinecart    // 避免矿车被"撞死"，哈哈哈矿车也是生物(
            || entity.getVehicle() == minecart;  // 避免被自己坐的矿车干掉
    }

    // 判断是否被用户排除
    private static boolean isInCustomExclusion(Entity entity) {
        return ConfigManager.isExcludePlayer() && entity instanceof Player
                || ConfigManager.isExcluePet() && isPet(entity)
                || ConfigManager.isExcludePassenger() && entity.isPassenger()
                || ConfigManager.isExcludeNamedEntity() && entity.hasCustomName()
                || ConfigManager.isExcludItemEntity() && entity instanceof ItemEntity
                || ConfigManager.isEnabledExclusionList() && isInExclusionList(entity);
    }

    // 总方法
    public static boolean isExclued(AbstractMinecart minecart, Entity entity) {
        return isInDefaultExclusion(minecart, entity) || isInCustomExclusion(entity);
    }
}