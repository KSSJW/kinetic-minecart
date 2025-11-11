package com.kssjw.kineticminecart.knock;

import com.kssjw.kineticminecart.util.EntityUtils;
import com.kssjw.kineticminecart.util.SpeedJudge;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.vehicle.AbstractMinecart;
import net.minecraft.world.phys.Vec3;

public class MinecartKonckHandler {

    public static void tryApplyKonck(AbstractMinecart minecart, Entity target) {

        // 应用击退
        Vec3 dir = target.position().subtract(minecart.position());
        double len = dir.length();
        Vec3 knockDir;
        if (len <= 1e-6) {
            Vec3 carDir = SpeedJudge.mv.lengthSqr() > 1e-6 ? SpeedJudge.mv.normalize() : new Vec3(0, 0.5, 0);
            knockDir = carDir.normalize();
        } else {
            knockDir = dir.scale(1.0 / len);
        }

        // 速度过低不击退
        if (SpeedJudge.catchedSpeed <= 2) return;

        // 撞飞
        double knockDistance = (double)SpeedJudge.catchedSpeed;
        EntityUtils.knockBack(target, knockDir, knockDistance);  // 第三个参数是击退距离

        // Testing Logger
        // Logger.printKnockDistance(knockDistance);
    }
}
