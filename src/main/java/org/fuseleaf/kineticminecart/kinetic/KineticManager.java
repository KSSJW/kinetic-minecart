package org.fuseleaf.kineticminecart.kinetic;

import java.util.List;

import org.fuseleaf.kineticminecart.config.ConfigManager;
import org.fuseleaf.kineticminecart.entity.TargetFilter;
import org.fuseleaf.kineticminecart.extension.config.ConfigEnum;
import org.fuseleaf.kineticminecart.util.DelayUtil;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.vehicle.minecart.AbstractMinecart;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class KineticManager {

    private static final double DELTA_SPEED_THRESHOLD = 10.0;   // The maximum delta speed is about 40.

    public static void tick(AbstractMinecart cart) {
        if (cart == null || !ConfigManager.isEnabled()) {
            return;
        }

        Level world = cart.level();
        double speed = getDeltaSpeedPerSecond(cart);
        Vec3 mv = cart.getDeltaMovement();

        if (world.isClientSide() || !isSpeedThresholdReached(cart)) {
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
                        && !TargetFilter.isExclued(cart, e)
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
                        && !TargetFilter.isExclued(cart, e)
                        && e.getVehicle() != cart   // Exclude the passenger of this vehicle.
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

        cart.setDeltaMovement(mv);
    }

    public static double getDeltaSpeedPerSecond(AbstractMinecart cart) {
        return cart.getDeltaMovement().length() * 20.0;
    }

    public static boolean isSpeedThresholdReached(AbstractMinecart cart) {
        return getDeltaSpeedPerSecond(cart) >= DELTA_SPEED_THRESHOLD;
    }
}
