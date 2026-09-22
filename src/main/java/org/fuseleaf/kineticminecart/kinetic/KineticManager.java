package org.fuseleaf.kineticminecart.kinetic;

import java.util.List;

import org.fuseleaf.kineticminecart.config.ConfigManager;
import org.fuseleaf.kineticminecart.extension.config.ConfigEnum;
import org.fuseleaf.kineticminecart.util.DelayUtil;
import org.fuseleaf.kineticminecart.util.FilterUtil;
import org.fuseleaf.kineticminecart.util.SpeedUtil;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.vehicle.minecart.AbstractMinecart;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class KineticManager {

    public static void tick(AbstractMinecart cart) {
        if (cart == null || !ConfigManager.isEnabled()) {
            return;
        }

        Level world = cart.level();
        float speed = SpeedUtil.getSpeed(cart);
        Vec3 mv = SpeedUtil.getVelocity(cart);

        if (world.isClientSide() || speed <= 2) {
            return;
        }

        switch (ConfigManager.getSelectedApplicationMode()) {
            case null:
            case ConfigEnum.ApplicationMode.Collide:
                List<Entity> list1 = world.getEntities(
                    cart,
                    cart.getBoundingBox(),
                    e -> (
                        e != cart
                        && e.isAlive()
                        && !FilterUtil.isExclued(cart, e)
                        && cart.getBoundingBox().intersects(e.getBoundingBox())
                    )
                );

                for (Entity target : list1) {

                    if (ConfigManager.isEnabledKnock()) {
                        CartKnock.knock(cart, target, mv, speed);
                    }
                    if (ConfigManager.isEnabledDamage()) {
                        DelayUtil.schedule(1, () -> CartImpact.damage(target, speed));
                    }
                }
                break;

            case ConfigEnum.ApplicationMode.Radius:
                List<Entity> list2 = world.getEntities(
                    cart,
                    cart.getBoundingBox().inflate(ConfigManager.getRadius()),
                    e -> (
                        e != cart
                        && e.isAlive()
                        && !FilterUtil.isExclued(cart, e)
                        && e.getVehicle() != cart   // Exclude the passenger of this vehicle
                    )
                );

                for (Entity target : list2) {

                    if (ConfigManager.isEnabledKnock()) {
                        CartKnock.knock(cart, target, mv, speed);
                    }
                    if (ConfigManager.isEnabledDamage()) {
                        DelayUtil.schedule(1, () -> CartImpact.damage(target, speed));
                    }
                }
                break;

            default:
                break;
        }

        SpeedUtil.setVelocity(cart, mv);
    }

    public static int getCollideStatus(AbstractMinecart minecart, Entity target) {
        if (!ConfigManager.isEnabled()
            || !ConfigManager.isOverrideCollision()
            || minecart == null
            || target instanceof AbstractMinecart
        ) {
            return -1;
        }

        if (SpeedUtil.getSpeed(minecart) > 2) {
            return 0;
        } else {
            return -1;
        }
    }
}
