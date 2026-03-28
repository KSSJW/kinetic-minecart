package com.kssjw.kineticminecart.manager;

import java.util.List;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.vehicle.minecart.AbstractMinecart;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import com.kssjw.kineticminecart.util.CartImpactUtil;
import com.kssjw.kineticminecart.util.CartKnockUtil;
import com.kssjw.kineticminecart.util.DelayUtil;
import com.kssjw.kineticminecart.util.FilterUtil;
import com.kssjw.kineticminecart.util.SpeedUtil;

public class KineticManager {

    private static void damage(Entity target, float speed) {
        if (target == null || target.level().isClientSide()) return;

        if (ConfigManager.getSelectedDamageMode() == "TieredDamage") CartImpactUtil.tryApplyTieredDamage(target, speed);
        if (ConfigManager.getSelectedDamageMode() == "DirectlyKill") CartImpactUtil.tryKill(target, speed);
    }
    
    public static void handler(AbstractMinecart self) {
        if (self == null) return;

        Level world = self.level();
        float speed = SpeedUtil.getSpeed(self);
        Vec3 mv = SpeedUtil.getVelocity(self);

        if (world == null
            || world.isClientSide() // 仅服务端处理
            || !ConfigManager.isEnabled()   // 开关拦截
        ) return;

        // 若矿车速度不足则跳过
        if (speed > 2) {

            if (ConfigManager.getSelectedApplicaionMode() == "Collide") {

                // 获取附近实体并过滤一些不需要的类型：自身、已死、被排除、不在碰撞箱内
                List<Entity> list = world.getEntities(
                    self,
                    self.getBoundingBox(),
                    e -> (
                        e != self
                        && e.isAlive()
                        && !FilterUtil.isExclued(self, e)
                        && self.getBoundingBox().intersects(e.getBoundingBox())
                    )
                );

                for (Entity target : list) {

                    // 对每个目标调用方法
                    if (ConfigManager.isEnabledKnock()) CartKnockUtil.tryApplyKnock(self, target, mv, speed);
                    if (ConfigManager.isEnabledDamage()) DelayUtil.schedule(1, () -> damage(target, speed));
                }
            }

            if (ConfigManager.getSelectedApplicaionMode() == "Radius") {
                AABB box = self.getBoundingBox().inflate(ConfigManager.getRadius());  // 检测范围

                // 获取附近实体并过滤一些不需要的类型：自身、已死、被排除
                List<Entity> list = world.getEntities(
                    self,
                    box,
                    e -> (
                        e != self
                        && e.isAlive()
                        && e.getVehicle() != self
                        && !FilterUtil.isExclued(self, e)
                    )
                );

                for (Entity target : list) {

                    // 对每个目标调用方法
                    if (ConfigManager.isEnabledKnock()) CartKnockUtil.tryApplyKnock(self, target, mv, speed);
                    if (ConfigManager.isEnabledDamage()) DelayUtil.schedule(1, () -> damage(target, speed));
                }
            }
        }

        SpeedUtil.setVelocity(self, mv);    // 恢复矿车原始速度
    }

    public static int getCollideStatus(AbstractMinecart minecart, Entity target) {
        if (!ConfigManager.isEnabled()
            || ConfigManager.getSelectedApplicaionMode() != "Collide"
            || minecart == null
            || target instanceof AbstractMinecart
        ) return -1;
        
        if (SpeedUtil.getSpeed(minecart) > 2) {
            return 0;
        } else {
            return -1;
        }
    }
}