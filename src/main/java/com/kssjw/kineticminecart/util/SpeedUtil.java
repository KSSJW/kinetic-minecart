package com.kssjw.kineticminecart.util;

import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.util.math.Vec3d;

public class SpeedUtil {

    private SpeedUtil() {}

    private static double MIN_SPEED_THRESHOLD = 0.001; // 最低速度阈值

    public static float getSpeed(AbstractMinecartEntity minecart) {
        double speedSqr = minecart.getVelocity().lengthSquared();

        if (speedSqr < Math.pow(MIN_SPEED_THRESHOLD, 2.0)) return 0;    // 速度阈值判断（使用平方以避免开方）

        return (float)Math.sqrt(speedSqr) * 4;  // 速度转换（每秒）
    }

    public static Vec3d getVelocity(AbstractMinecartEntity minecart) {
        return minecart.getVelocity();
    }

    public static void setVelocity(AbstractMinecartEntity minecart, Vec3d mv) {
        minecart.setVelocity(mv);
    }
}