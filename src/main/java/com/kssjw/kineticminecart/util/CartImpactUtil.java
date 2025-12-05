package com.kssjw.kineticminecart.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.vehicle.AbstractMinecart;
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

        // 排除情况
        if (
            target instanceof AbstractMinecart  // 避免矿车被"撞死"，哈哈哈矿车也是生物(
            || (target.isPassenger() && target.getVehicle() instanceof AbstractMinecart)    // 坐在矿车上的乘客不被撞死
        ) return;

        // 计算伤害
        float damage;
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