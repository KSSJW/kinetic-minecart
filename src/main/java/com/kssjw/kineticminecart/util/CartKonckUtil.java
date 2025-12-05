package com.kssjw.kineticminecart.util;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.vehicle.AbstractMinecart;
import net.minecraft.world.phys.Vec3;

public class CartKonckUtil {

    private CartKonckUtil() {}

    public static void tryApplyKonck(AbstractMinecart minecart, Entity target) {

        // 应用击退
        Vec3 dir = target.position().subtract(minecart.position());
        double len = dir.length();
        Vec3 knockDir;
        if (len <= 1e-6) {
            Vec3 carDir = SpeedUtil.mv.lengthSqr() > 1e-6 ? SpeedUtil.mv.normalize() : new Vec3(0, 0.5, 0);
            knockDir = carDir.normalize();
        } else {
            knockDir = dir.scale(1.0 / len);
        }

        // 速度过低不击退
        if (SpeedUtil.catchedSpeed <= 2) return;

        // 撞飞
        double knockDistance = (double)SpeedUtil.catchedSpeed;
        EntityUtil.knockBack(target, knockDir, knockDistance);  // 第三个参数是击退距离
    }
}
