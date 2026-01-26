package com.kssjw.kineticminecart.manager;

import java.util.List;

import com.kssjw.kineticminecart.util.CartImpactUtil;
import com.kssjw.kineticminecart.util.CartKnockUtil;
import com.kssjw.kineticminecart.util.DelayUtil;
import com.kssjw.kineticminecart.util.FilterUtil;
import com.kssjw.kineticminecart.util.SpeedUtil;

import net.minecraft.entity.Entity;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class KineticManager {

    public static float speed;
    public static Vec3d mv;
    
    public static void handler(AbstractMinecartEntity self) {
        
        World world = self.getEntityWorld();
        speed = SpeedUtil.getSpeed(self);
        mv = SpeedUtil.getVelocity(self);

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
                    if (ConfigManager.isEnabledKnock() == true) CartKnockUtil.tryApplyKnock(self, target);
                    if (ConfigManager.isEnabledImpact() == true) DelayUtil.schedule(1, () -> CartImpactUtil.tryApplyImpact(self, target));  // 在Minecraft1.19.3发现不使用延迟工具可能导致Knock无效
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
                    if (ConfigManager.isEnabledKnock() == true) CartKnockUtil.tryApplyKnock(self, target);
                    if (ConfigManager.isEnabledImpact() == true) DelayUtil.schedule(1, () -> CartImpactUtil.tryApplyImpact(self, target));  // 在Minecraft1.19.3发现不使用延迟工具可能导致Knock无效
                }
            }
        }

        SpeedUtil.setVelocity(self, mv);    // 恢复矿车原始速度
    }

    public static int collide() {
        if (ConfigManager.getSelectedApplicaionMode() != "Collide") return -1;
        if (speed > 2) {
            return 0;
        } else {
            return -1;
        }
    }
}