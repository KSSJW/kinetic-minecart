package com.kssjw.kineticminecart.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.util.math.Vec3d;

public class CartKnockUtil {

    private CartKnockUtil() {}

    public static void tryApplyKnock(AbstractMinecartEntity minecart, Entity target) {

        // 应用击退
        Vec3d dir = target.getEntityPos().subtract(minecart.getEntityPos());
        double len = dir.length();
        Vec3d knockDir;
        if (len <= 1e-6) {
            Vec3d carDir = SpeedUtil.mv.lengthSquared() > 1e-6 ? SpeedUtil.mv.normalize() : new Vec3d(0, 0.5, 0);
            knockDir = carDir.normalize();
        } else {
            knockDir = dir.multiply(1.0 / len);
        }

        // 速度过低不击退
        if (SpeedUtil.catchedSpeed <= 2) return;

        // 撞飞
        double knockDistance = (double)SpeedUtil.catchedSpeed;
        EntityUtil.knockBack(target, knockDir, knockDistance);  // 第三个参数是击退距离
    }
}
