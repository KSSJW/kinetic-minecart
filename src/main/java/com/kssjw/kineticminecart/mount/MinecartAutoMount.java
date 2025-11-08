package com.kssjw.kineticminecart.mount;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.AbstractMinecart;
import net.minecraft.world.entity.vehicle.Minecart;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.phys.AABB;

import java.util.List;
import java.util.function.Predicate;

public final class MinecartAutoMount {
    // 配置项
    private static final double CHECK_RADIUS = 0.76468; // 单位：blocks 自己测量的哦，比较精确了
    private static final int COOLDOWN_TICKS = 10; // 可选：触发后短暂冷却，避免重复（0 不启用）

    // 简单内存：记录最近触发时间（可根据需要换成 WeakHashMap）
    private static final java.util.Map<AbstractMinecart, Integer> cooldownMap = new java.util.WeakHashMap<>();

    // 主入口：在每个矿车 tick 或全局 tick 调用
    public static void maybeAutoMount(AbstractMinecart minecart) {

        // 只允许普通矿车触发，排除运输矿车这些杂鱼
        if (!(minecart instanceof Minecart)) return;

        // 仅服务器端
        if (minecart.level().isClientSide()) return;
        if (!(minecart.level() instanceof ServerLevel)) return;

        // 已有乘客则不触发
        if (!minecart.getPassengers().isEmpty()) return;

        // 冷却检查（可选）
        if (COOLDOWN_TICKS > 0) {
            Integer ticksLeft = cooldownMap.get(minecart);
            if (ticksLeft != null && ticksLeft > 0) {
                cooldownMap.put(minecart, ticksLeft - 1);
                return;
            }
        }

        // 检测范围内的实体
        AABB box = minecart.getBoundingBox().inflate(CHECK_RADIUS);
        List<Entity> nearby = minecart.level().getEntities(minecart, box, canAutoMountFilter(minecart));

        for (Entity e : nearby) {
            // 再次确认：目标没有已在骑乘、并且能被骑
            if (e.isPassenger() || e.isVehicle()) continue;

            // 强制上车（服务器端调用）
            boolean mounted = e.startRiding(minecart);
            if (mounted) {
                if (COOLDOWN_TICKS > 0) cooldownMap.put(minecart, COOLDOWN_TICKS);
                break; // 一个实体上车就够了
            }
        }
    }

    // 白名单过滤：只针对想要自动上车的实体类型
    private static Predicate<Entity> canAutoMountFilter(AbstractMinecart minecart) {
        return entity -> {
            // 排除客户端、非生物实体或已经在骑乘/被骑的实体
            if (entity.level().isClientSide()) return false;
            if (entity.isPassenger() || entity.isVehicle()) return false;

            // 明确排除掉落物与其他非生物实体
            if (entity instanceof ItemEntity) return false;

            // 排除玩家
            if (entity instanceof Player) return false;

            // 允许所有 LivingEntity（动物、村民、怪物、马等）
            if (entity instanceof LivingEntity) return true;

            // 其余类型一律不允许
            return false;
        };
    }
}