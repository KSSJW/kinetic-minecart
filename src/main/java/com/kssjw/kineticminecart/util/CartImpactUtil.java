package com.kssjw.kineticminecart.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.vehicle.minecart.AbstractMinecart;
import net.minecraft.world.level.Level;

public class CartImpactUtil {

    private CartImpactUtil() {}

    // lastHitTicks 存储 entityId -> lastHitGameTime（server tick time）
    private static final Map<Integer, Long> lastHitTicks = new ConcurrentHashMap<>();

    // 忽略某些过时方法的警告
    @SuppressWarnings("deprecation")

    // 仅在服务端执行
    public static void tryApplyImpact(AbstractMinecart minecart, Entity target) {

        if (minecart == null || target == null) return;

        Level level = minecart.level();
        if (level == null || level.isClientSide()) return; // 仅在服务端执行
               
        // 强转 ServerLevel 以获取 DamageSource 工厂（DamageSources 工具类）
        if (!(level instanceof ServerLevel serverLevel)) return;

        // 冷却检查
        long now = level.getGameTime();
        int tid = target.getId();
        Long last = lastHitTicks.get(tid);
        if (last != null && (now - last) < SpeedUtil.COOLDOWN_TICKS) return;
        lastHitTicks.put(tid, now);

        // 避免矿车被"撞死"，哈哈哈矿车也是生物(
        if (target instanceof AbstractMinecart) return;

        // 避免被自己坐的矿车干掉
        if (target.getVehicle() == minecart) return;

        float damage;

        // 目标为乘客时计算伤害（模拟载具抵挡了部分伤害）
        if (target.isPassenger() == true) damage = (float)SpeedUtil.catchedSpeed;

        // 正常情况计算伤害
        if (SpeedUtil.catchedSpeed >= 6) {
            damage = (float)Math.pow(SpeedUtil.catchedSpeed, 3);
        } else if (SpeedUtil.catchedSpeed < 6 && SpeedUtil.catchedSpeed > 2) {
            damage = (float)Math.pow(SpeedUtil.catchedSpeed, 2);
        } else return;

        // 处刑
        DamageSource src = DamageSourcesUtil.generic(serverLevel);  
        target.hurt(src, damage);   // 对实体造成伤害，该方法虽然可能过时，但是能用就行（
    }
}