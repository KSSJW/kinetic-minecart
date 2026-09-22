package org.fuseleaf.kineticminecart.kinetic;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.fuseleaf.kineticminecart.config.ConfigManager;
import org.fuseleaf.kineticminecart.extension.config.ConfigEnum;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public class CartImpact {

    private CartImpact() {}

    private static final Map<Integer, Long> LAST_HIT_TICKS = new ConcurrentHashMap<>();   // lastHitTicks 存储 entityId -> lastHitGameTime（server tick time）

    public static void damage(Entity target, float speed) {
        if (target == null) {
            return;
        }

        switch (ConfigManager.getSelectedDamageMode()) {
            case null:
            case ConfigEnum.DamageMode.TieredDamage:
                tieredDamage(target, speed);
                break;

            case ConfigEnum.DamageMode.DirectlyKill:
                kill(target, speed);
                break;

            default:
                break;
        }
    }

    private static void tieredDamage(Entity target, float speed) {
        Level world = target.level();

        long now = world.getGameTime();
        int tid = target.getId();
        Long last = LAST_HIT_TICKS.get(tid);

        if (last != null && (now - last) < 10L) {
            return;
        }

        LAST_HIT_TICKS.put(tid, now);

        float damage;

        if (target.isPassenger()) {
            damage = (float)speed;
        }

        if (speed >= 6) {
            damage = (float)Math.pow(speed, 3);
        } else if (speed < 6 && speed > 2) {
            damage = (float)Math.pow(speed, 2);
        } else {
            return;
        }

        target.hurtServer((ServerLevel)world, world.damageSources().flyIntoWall(), damage);
    }

    private static void kill(Entity target, float speed) {
        if (speed <= 2) {
            return;
        }

        ServerLevel level = (ServerLevel) target.level();

        if (target instanceof LivingEntity livingEntity) {
            livingEntity.hurtServer(level, level.damageSources().flyIntoWall(), Float.MAX_VALUE);
        } else {
            target.kill(level);
        }
    }
}
