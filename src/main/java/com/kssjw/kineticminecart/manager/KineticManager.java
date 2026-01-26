package com.kssjw.kineticminecart.manager;

import java.util.List;

import com.kssjw.kineticminecart.util.CartImpactUtil;
import com.kssjw.kineticminecart.util.CartKnockUtil;
import com.kssjw.kineticminecart.util.DelayUtil;
import com.kssjw.kineticminecart.util.FilterUtil;
import com.kssjw.kineticminecart.util.SpeedUtil;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class KineticManager {

    private static void damage(Entity target, float speed) {
        if (target == null || target.getEntityWorld().isClient()) return;

        if (ConfigManager.getSelectedDamageMode() == "TieredDamage") CartImpactUtil.tryApplyTieredDamage(target, speed);
        if (ConfigManager.getSelectedDamageMode() == "DirectlyKill") CartImpactUtil.tryKill(target, speed);
    }
    
    public static void handler(AbstractMinecartEntity self) {
        if (self == null) return;

        World world = self.getEntityWorld();
        float speed = SpeedUtil.getSpeed(self);
        Vec3d mv = SpeedUtil.getVelocity(self);

        if (world == null
            || world.isClient() // 仅服务端处理
            || !ConfigManager.isEnabled()   // 开关拦截
        ) return;

        // 若矿车速度不足则跳过
        if (speed > 2) {

            if (ConfigManager.getSelectedApplicaionMode() == "Collide") {

                // 获取附近实体并过滤一些不需要的类型（自身、已死、不可交互等）
                List<Entity> list = world.getOtherEntities(self, self.getBoundingBox(), e -> e != self && e.isAlive());
                if (list.isEmpty()) return;

                for (Entity target : list) {

                    if (FilterUtil.isExclued(self, target) || !self.getBoundingBox().intersects(target.getBoundingBox())) continue; // 排除工具、未在碰撞箱内

                    // 对每个目标调用方法
                    if (ConfigManager.isEnabledKnock()) CartKnockUtil.tryApplyKnock(self, target, mv, speed);
                    if (ConfigManager.isEnabledDamage()) DelayUtil.schedule(1, () -> damage(target, speed));
                }
            }

            if (ConfigManager.getSelectedApplicaionMode() == "Radius") {
                Box box = self.getBoundingBox().expand(ConfigManager.getRadius());  // 检测范围

                // 获取附近实体并过滤一些不需要的类型（自身、已死、不可交互等）
                List<Entity> list = world.getOtherEntities(self, box, e -> e != self && e.isAlive());
                if (list.isEmpty()) return;

                for (Entity target : list) {

                    if (FilterUtil.isExclued(self, target)) continue;   // 排除工具

                    // 对每个目标调用方法
                    if (ConfigManager.isEnabledKnock()) CartKnockUtil.tryApplyKnock(self, target, mv, speed);
                    if (ConfigManager.isEnabledDamage()) DelayUtil.schedule(1, () -> damage(target, speed));
                }
            }
        }

        SpeedUtil.setVelocity(self, mv);    // 恢复矿车原始速度
    }

    public static int getCollideStatus(AbstractMinecartEntity minecart, Entity target) {
        if (!ConfigManager.isEnabled()
            || ConfigManager.getSelectedApplicaionMode() != "Collide"
            || target instanceof AbstractMinecartEntity
        ) return -1;
        
        if (SpeedUtil.getSpeed(minecart) > 2) {
            return 0;
        } else {
            return -1;
        }
    }

    public static void sendSpeedToClient(AbstractMinecartEntity minecart) {
        if (!ConfigManager.isEnabled()
            || !ConfigManager.isEnabledHUDSpeed()
            || minecart.getEntityWorld().isClient()
        ) return;

        List<Entity> list = minecart.getPassengerList();
        for (Entity e : list) {
            if (e instanceof PlayerEntity player) {
                player.sendMessage(
                    Text.translatable("message.kinetic-minecart.speed.head")
                        .append(String.format("%.2f", (double)SpeedUtil.getSpeed(minecart)))
                        .append(Text.translatable("message.kinetic-minecart.speed.tail")),
                    true
                );
            }
        }
    }
}