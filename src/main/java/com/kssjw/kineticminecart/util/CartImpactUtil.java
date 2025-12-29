package com.kssjw.kineticminecart.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

public class CartImpactUtil {

    private CartImpactUtil() {}

    // lastHitTicks 存储 entityId -> lastHitGameTime（server tick time）
    private static final Map<Integer, Long> lastHitTicks = new ConcurrentHashMap<>();

    // 仅在服务端执行
    public static void tryApplyImpact(AbstractMinecartEntity minecart, Entity target) {

        if (minecart == null || target == null) return;

        World world = minecart.getEntityWorld();
        if (world == null || world.isClient()) return; // 仅在服务端执行
               
        // 强转 ServerWorld 以获取 DamageSource 工厂（DamageSources 工具类）
        if (!(world instanceof ServerWorld serverWorld)) return;

        // 冷却检查
        long now = world.getTime();
        int tid = target.getId();
        Long last = lastHitTicks.get(tid);
        if (last != null && (now - last) < SpeedUtil.COOLDOWN_TICKS) return;
        lastHitTicks.put(tid, now);

        // 避免矿车被"撞死"，哈哈哈矿车也是生物(
        if (target instanceof AbstractMinecartEntity) return;

        // 避免被自己坐的矿车干掉
        if (target.getVehicle() == minecart) return;

        float damage;

        // 目标为乘客时计算伤害（模拟载具抵挡了部分伤害）
        if (target.hasVehicle() == true) damage = (float)SpeedUtil.catchedSpeed;

        // 正常情况计算伤害
        if (SpeedUtil.catchedSpeed >= 6) {
            damage = (float)Math.pow(SpeedUtil.catchedSpeed, 3);
        } else if (SpeedUtil.catchedSpeed < 6 && SpeedUtil.catchedSpeed > 2) {
            damage = (float)Math.pow(SpeedUtil.catchedSpeed, 2);
        } else return;

        // 处刑
        DamageSource src = DamageSourcesUtil.generic(serverWorld);  
        target.damage(src, damage);    // 对实体造成伤害
    }
}