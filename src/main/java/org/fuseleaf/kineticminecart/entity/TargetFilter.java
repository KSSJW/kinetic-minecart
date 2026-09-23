package org.fuseleaf.kineticminecart.entity;

import org.fuseleaf.kineticminecart.config.ConfigManager;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.animal.equine.AbstractHorse;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.minecart.AbstractMinecart;

public class TargetFilter {

    private TargetFilter() {}

    public static boolean isExclued(AbstractMinecart minecart, Entity entity) {
        return isInDefaultExclusion(minecart, entity) || isInCustomExclusion(entity);
    }

    private static boolean isInDefaultExclusion(AbstractMinecart minecart, Entity entity) {
        return entity instanceof AbstractMinecart || entity.getVehicle() == minecart;
    }

    private static boolean isInCustomExclusion(Entity entity) {
        return (
            ConfigManager.isExcludePlayer() && entity instanceof Player
            || ConfigManager.isExcluePet() && isPet(entity)
            || ConfigManager.isExcludePassenger() && entity.isPassenger()
            || ConfigManager.isExcludeNamedEntity() && entity.hasCustomName()
            || ConfigManager.isExcludItemEntity() && entity instanceof ItemEntity
            || ConfigManager.isEnabledExclusionList() && isInExclusionList(entity)
        );
    }

    private static boolean isPet(Entity entity) {
        if (entity instanceof TamableAnimal animal) {
            return animal.isTame() || animal.getOwner() != null;
        }

        if (entity instanceof AbstractHorse horse) {
            return horse.isTamed() || horse.getOwner() != null;
        }

        return false;
    }

    private static boolean isInExclusionList(Entity entity) {
        String id = BuiltInRegistries.ENTITY_TYPE.getKey(entity.getType()).toString();

        return ConfigManager.getExclusionList().contains(id);
    }
}
