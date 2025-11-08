package com.kssjw.kineticminecart.util;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;

public final class EntityUtils {

    public static boolean isValidTarget(Entity entity) {
        return entity.isAlive();
    }

    /**
     * 对实体施加一次速度推力（非直接伤害），在现代映射中使用 setDeltaMovement 替代已移除的 velocityModified / addVelocity。
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

        // 在部分映射/版本中，你可能希望标记实体已被外力影响以便同步表现
        // 如果需要，可在这里对特定实体类型触发额外逻辑（例如对 LivingEntity 调用 knockback 效果）
    }

    private EntityUtils() {}
}