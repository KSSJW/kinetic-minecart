package org.fuseleaf.kineticminecart.kinetic;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.vehicle.minecart.AbstractMinecart;
import net.minecraft.world.phys.Vec3;

public class CartKnock {

    private CartKnock() {}

    public static void knock(AbstractMinecart minecart, Entity target, Vec3 mv, float speed) {
        Vec3 dir = target.position().subtract(minecart.position());
        double len = dir.length();
        Vec3 knockDir;

        if (len <= 1e-6) {
            Vec3 carDir = mv.lengthSqr() > 1e-6 ? mv.normalize() : new Vec3(0, 0.5, 0);
            knockDir = carDir.normalize();
        } else {
            knockDir = dir.scale(1.0 / len);
        }

        Vec3 add = knockDir.scale(speed);
        Vec3 current = target.getDeltaMovement();
        Vec3 next = current.add(add);

        target.setDeltaMovement(next);
    }
}
