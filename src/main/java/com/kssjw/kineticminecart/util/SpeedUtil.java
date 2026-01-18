package com.kssjw.kineticminecart.util;

import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.util.math.Vec3d;

public class SpeedUtil {

    private SpeedUtil() {}

    // 全局调用数值
    public static Vec3d mv;
    public static double speedSqr;
    public static long COOLDOWN_TICKS = 10L;    // 可调参数，每实体冷却时间（tick），避免每 tick 重复伤害
    public static float catchedSpeed;

    // 可调参数，伤害值与击退强度被移用到后面
    private static double MIN_SPEED_THRESHOLD = 0.2; // 最低速度阈值（平方和阈值会用 lengthSqr 比较）

    public static float getSpeed(AbstractMinecartEntity minecart) {
        return normalSpeedProcessor(minecart);  // 目前不需要 advancedSpeedProcessor
    }

    public static float normalSpeedProcessor (AbstractMinecartEntity minecart) {

        // 保存矿车原始速度，确保最后恢复
        Vec3d originalMv = minecart.getVelocity();

         // 速度阈值判断（使用平方以避免开方）
        mv = originalMv;
        speedSqr = mv.lengthSquared();
        if (speedSqr < MIN_SPEED_THRESHOLD * MIN_SPEED_THRESHOLD) return 0;

        // 速度转换
        float speed = (float)Math.sqrt(SpeedUtil.speedSqr);
        float speed_per_sec = speed * 4;

        // 恢复矿车原始速度，确保矿车动量不丢失
        minecart.setVelocity(originalMv);

        return (float)speed_per_sec;
    }

    public static void advancedSpeedProcessor () {
        // 好像目前不需要
    }
}