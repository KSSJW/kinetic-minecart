package com.kssjw.kineticminecart.util;

import net.minecraft.world.entity.vehicle.minecart.AbstractMinecart;
import net.minecraft.world.phys.Vec3;

public class SpeedUtil {

    private SpeedUtil() {}

    private static double MIN_SPEED_THRESHOLD = 0.001; // 最低速度阈值

    public static float getSpeed(AbstractMinecart minecart) {
        double speedSqr = minecart.getDeltaMovement().lengthSqr();

        if (speedSqr < Math.pow(MIN_SPEED_THRESHOLD, 2.0)) return 0;    // 速度阈值判断（使用平方以避免开方）

        float speed = (float)Math.sqrt(speedSqr) * 4;   // 速度转换（每秒）

        if (RailUtil.isCurved(minecart)) {
            return speed *= Math.sqrt(2.0); // 修正为斜角速度
        } else {
            return speed;
        }
    }

    public static Vec3 getVelocity(AbstractMinecart minecart) {
        return minecart.getDeltaMovement();
    }

    public static void setVelocity(AbstractMinecart minecart, Vec3 mv) {
        minecart.setDeltaMovement(mv);
    }
}