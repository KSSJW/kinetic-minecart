package com.kssjw.kineticminecart.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import net.minecraft.entity.Entity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

public class CartImpactUtil {

    private CartImpactUtil() {}

    private static final Map<Integer, Long> lastHitTicks = new ConcurrentHashMap<>();   // lastHitTicks 存储 entityId -> lastHitGameTime（server tick time）

    public static void tryApplyTieredDamage(Entity target, float speed) {
        World world = target.getEntityWorld();
               
        // 冷却检查
        long now = world.getTime();
        int tid = target.getId();
        Long last = lastHitTicks.get(tid);
        if (last != null && (now - last) < 10L) return; // 每实体冷却时间（tick），避免每 tick 重复伤害
        lastHitTicks.put(tid, now);

        float damage;

        if (target.hasVehicle()) damage = (float)speed;    // 目标为乘客时计算伤害（模拟载具抵挡了部分伤害）

        // 正常情况计算伤害
        if (speed >= 6) {
            damage = (float)Math.pow(speed, 3);
        } else if (speed < 6 && speed > 2) {
            damage = (float)Math.pow(speed, 2);
        } else return;

        target.damage((ServerWorld)world, world.getDamageSources().generic(), damage);  // 处刑
    }

    public static void tryKill(Entity target, float speed) {
        if (speed > 2) target.kill((ServerWorld)target.getEntityWorld());
    }
}