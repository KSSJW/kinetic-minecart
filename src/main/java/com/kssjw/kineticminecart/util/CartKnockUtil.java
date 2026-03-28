package com.kssjw.kineticminecart.util;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.vehicle.minecart.AbstractMinecart;
import net.minecraft.world.phys.Vec3;

public class CartKnockUtil {

    private CartKnockUtil() {}

    public static void tryApplyKnock(AbstractMinecart minecart, Entity target, Vec3 mv, float speed) {

        // 应用击退
        Vec3 dir = target.position().subtract(minecart.position());
        double len = dir.length();
        Vec3 knockDir;
        if (len <= 1e-6) {
            Vec3 carDir = mv.lengthSqr() > 1e-6 ? mv.normalize() : new Vec3(0, 0.5, 0);
            knockDir = carDir.normalize();
        } else {
            knockDir = dir.scale(1.0 / len);
        }

        if (speed <= 2) return;    // 速度过低不击退

        EntityUtil.knockBack(target, knockDir, (double)speed);  // 撞飞
    }
}