package com.kssjw.kineticminecart.manager;

import java.util.List;

import com.kssjw.kineticminecart.util.CartImpactUtil;
import com.kssjw.kineticminecart.util.CartKnockUtil;
import com.kssjw.kineticminecart.util.FilterUtil;
import com.kssjw.kineticminecart.util.SpeedUtil;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.vehicle.minecart.AbstractMinecart;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

public class KineticManager {
    
    public static void handler(AbstractMinecart self) {
        
        // 仅服务端处理
        Level level = self.level(); 
        if (level == null || level.isClientSide()) return;

        // 开关拦截
        if (ConfigManager.isEnabled() == false) return;

        SpeedUtil.catchedSpeed = SpeedUtil.getSpeed(self);

        // 若矿车速度不足则跳过,避免频繁查询实体
        if (SpeedUtil.catchedSpeed > 2) {

            // 检测范围
            double radius = 0.5;
            AABB box = self.getBoundingBox().inflate(radius);

            // 获取附近实体并过滤一些不需要的类型（自身、已死、不可交互等）
            List<Entity> list = level.getEntities(self, box, e -> e != self && e.isAlive());
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
