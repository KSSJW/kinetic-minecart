package com.kssjw.kineticminecart.util;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;

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
    public static void knockBack(Entity entity, Vec3 direction, double strength) {
        if (entity == null) return;

        // 计算附加速度（direction 乘以强度）
        Vec3 add = direction.scale(strength);

        // 获取当前速度并累加
        Vec3 current = entity.getDeltaMovement();
        Vec3 next = current.add(add);

        // 应用新的速度
        entity.setDeltaMovement(next);
    }
}