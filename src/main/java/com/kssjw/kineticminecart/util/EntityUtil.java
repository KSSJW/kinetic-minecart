package com.kssjw.kineticminecart.util;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;

public final class EntityUtil {

    private EntityUtil() {}

    public static boolean isValidTarget(Entity entity) {
        return entity.isAlive();
    }

    /**
     * 对实体施加一次速度推力（非直接伤害）
     *
     * @param entity    目标实体
     * @param direction 单位方向向量（不必规范化，方法内部会按 strength 缩放）
     * @param strength  强度系数
     */
    public static void knockBack(Entity entity, Vec3d direction, double strength) {
        if (entity == null) return;

        // 计算附加速度（direction 乘以强度）
        Vec3d add = direction.multiply(strength);

        // 获取当前速度并累加
        Vec3d current = entity.getVelocity();
        Vec3d next = current.add(add);

        // 应用新的速度
        entity.setVelocity(next);
    }
}