package com.kssjw.kineticminecart.manager;

import java.util.List;

import com.kssjw.kineticminecart.util.CartImpactUtil;
import com.kssjw.kineticminecart.util.CartKnockUtil;
import com.kssjw.kineticminecart.util.FilterUtil;
import com.kssjw.kineticminecart.util.SpeedUtil;
import com.kssjw.kineticminecart.config.ValueConfig;

import net.minecraft.entity.Entity;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

public class KineticManager {
    
    public static void handler(AbstractMinecartEntity self) {
        
        // 仅服务端处理
        World world = self.getWorld();
        if (world == null || world.isClient()) return;

        // 开关拦截
        if (ConfigManager.isEnabled() == false) return;

        SpeedUtil.catchedSpeed = SpeedUtil.getSpeed(self);

        // 若矿车速度不足则跳过,避免频繁查询实体
        if (SpeedUtil.catchedSpeed > 2) {

            if (ConfigManager.getSelectedApplicaionMode() == ValueConfig.applicaionMode.Collide) {

                // 检测范围
                Box box = self.getBoundingBox();

                // 获取附近实体并过滤一些不需要的类型（自身、已死、不可交互等）
                List<Entity> list = world.getOtherEntities(self, box, e -> e != self && e.isAlive());
                if (list.isEmpty()) return;

                for (Entity target : list) {

                    // 用户自定义排除
                    if (FilterUtil.isEntityExcluded(target) == true) continue;

                    // 未在碰撞箱内
                    if (self.getBoundingBox().intersects(target.getBoundingBox()) == false) continue;

                    // 读取配置，按需对每个目标调用 handler（handler 内部处理冷却/服务端校验）
                    if (ConfigManager.isEnabledImpact() == true) CartImpactUtil.tryApplyImpact(self, target);
                    if (ConfigManager.isEnabledKnock() == true) CartKnockUtil.tryApplyKnock(self, target);
                }
            }

            if (ConfigManager.getSelectedApplicaionMode() == ValueConfig.applicaionMode.Radius) {

                // 检测范围
                double radius = ConfigManager.getRadius();
                Box box = self.getBoundingBox().expand(radius);

                // 获取附近实体并过滤一些不需要的类型（自身、已死、不可交互等）
                List<Entity> list = world.getOtherEntities(self, box, e -> e != self && e.isAlive());
                if (list.isEmpty()) return;

                for (Entity target : list) {

                    // 用户自定义排除
                    if (FilterUtil.isEntityExcluded(target) == true) continue;

                    // 读取配置，按需对每个目标调用 handler（handler 内部处理冷却/服务端校验）
                    if (ConfigManager.isEnabledImpact() == true) CartImpactUtil.tryApplyImpact(self, target);
                    if (ConfigManager.isEnabledKnock() == true) CartKnockUtil.tryApplyKnock(self, target);
                }
            }
        }
    }

    public static int collide() {
        if (ConfigManager.getSelectedApplicaionMode() != ValueConfig.applicaionMode.Collide) return -1;
        if (SpeedUtil.catchedSpeed > 2) {
            return 0;
        } else {
            return -1;
        }
    }
}