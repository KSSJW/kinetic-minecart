package com.kssjw.kineticminecart.damage;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.kssjw.kineticminecart.util.SpeedJudge;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.vehicle.AbstractMinecart;
import net.minecraft.world.level.Level;

public class MinecartImpactHandler {

    private MinecartImpactHandler() {}

    // lastHitTicks 存储 entityId -> lastHitGameTime（server tick time）
    private static final Map<Integer, Long> lastHitTicks = new ConcurrentHashMap<>();

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
        if (last != null && (now - last) < SpeedJudge.COOLDOWN_TICKS) return;
        lastHitTicks.put(tid, now);

        // 排除情况
        if (
            target instanceof AbstractMinecart  // 避免矿车被"撞死"，哈哈哈矿车也是生物(
            || target instanceof ItemEntity // 避免掉落物被"撞死"
            || (target.isPassenger() && target.getVehicle() instanceof AbstractMinecart)    // 坐在矿车上的乘客不被撞死
        ) return;

        // 计算伤害
        float damage;
        if (SpeedJudge.catchedSpeed >= 6) {
            damage = (float)Math.pow(SpeedJudge.catchedSpeed, 3);
        } else if (SpeedJudge.catchedSpeed < 6 && SpeedJudge.catchedSpeed > 2) {
            damage = (float)Math.pow(SpeedJudge.catchedSpeed, 2);
        } else return;

        // 处刑
        DamageSource src = DamageSources.generic(serverLevel);  
        target.hurt(src, damage);   // 对实体造成伤害，该方法虽然可能过时，但是能用就行（

        // Testing Logger
        // Logger.printDamage(damage);
    }
}