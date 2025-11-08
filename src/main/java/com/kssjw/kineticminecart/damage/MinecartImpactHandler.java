package com.kssjw.kineticminecart.damage;

import com.kssjw.kineticminecart.mount.MinecartAutoMount;
import com.kssjw.kineticminecart.util.EntityUtils;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.vehicle.AbstractMinecart;
import net.minecraft.world.level.Level;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.phys.Vec3;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MinecartImpactHandler {

    // 可调参数，伤害值与击退强度被移用到后面
    public static double MIN_SPEED_THRESHOLD = 0.2; // 最低速度阈值（平方和阈值会用 lengthSqr 比较）
    public static long COOLDOWN_TICKS = 10L;        // 每实体冷却时间（tick），避免每 tick 重复伤害

    // lastHitTicks 存储 entityId -> lastHitGameTime（server tick time）
    private static final Map<Integer, Long> lastHitTicks = new ConcurrentHashMap<>();

    private MinecartImpactHandler() {}

    // 忽略某些过时方法的警告
    @SuppressWarnings("deprecation")

    // 尝试对 target 应用矿车撞击效果（伤害 + 击退）
    // 仅在服务端执行
    public static void tryApplyImpact(AbstractMinecart minecart, Entity target) {
        if (minecart == null || target == null) return;

        Level level = minecart.level();
        if (level == null || level.isClientSide()) return; // 仅在服务端执行

        // 保存矿车原始速度，确保最后恢复
        Vec3 originalMv = minecart.getDeltaMovement();

        // 速度阈值判断（使用平方以避免开方）
        Vec3 mv = originalMv;
        double speedSqr = mv.lengthSqr();
        if (speedSqr < MIN_SPEED_THRESHOLD * MIN_SPEED_THRESHOLD) return;

        // 避免矿车被"撞死"
        // 哈哈哈矿车也是生物(
        if (target instanceof AbstractMinecart) return;

        // 避免掉落物被"撞死"
        if (target instanceof ItemEntity) return;

        // 冷却检查
        long now = level.getGameTime();
        int tid = target.getId();
        Long last = lastHitTicks.get(tid);
        if (last != null && (now - last) < COOLDOWN_TICKS) return;
        lastHitTicks.put(tid, now);

        // 强转 ServerLevel 以获取 DamageSource 工厂（DamageSources 工具类）
        if (!(level instanceof ServerLevel serverLevel)) return;

        // 计算伤害
        float speed = (float)Math.sqrt(speedSqr);
        float speed_per_sec = speed * 4;
        float damage;
        if (speed_per_sec >= 6) {
            damage = (float)Math.pow(speed_per_sec, 3);
        } else if (speed_per_sec < 6 && speed_per_sec > 2) {
            damage = (float)Math.pow(speed_per_sec, 2);
        } else {

            // 还原生物上车
            MinecartAutoMount.maybeAutoMount(minecart);
            return;
        }
        
        DamageSource src = DamageSources.generic(serverLevel);  

        if (target.isPassenger() && target.getVehicle() instanceof AbstractMinecart) {
            return; // 坐在矿车上的乘客不被撞死
        } else target.hurt(src, damage);   // 对实体造成伤害，该方法虽然可能过时，但是能用就行（

        // 应用击退
        Vec3 dir = target.position().subtract(minecart.position());
        double len = dir.length();
        Vec3 knockDir;
        if (len <= 1e-6) {
            Vec3 carDir = mv.lengthSqr() > 1e-6 ? mv.normalize() : new Vec3(0, 0.5, 0);
            knockDir = carDir.normalize();
        } else {
            knockDir = dir.scale(1.0 / len);
        }

        if (speed_per_sec <= 2) {
            return; // 速度过低不击退
        } else EntityUtils.knockBack(target, knockDir, (double)speed_per_sec);  // 第三个参数是击退距离
        
        // 恢复矿车原始速度，确保矿车动量不丢失
        minecart.setDeltaMovement(originalMv);
    }
}