package com.kssjw.kineticminecart.mixin;

import com.kssjw.kineticminecart.damage.MinecartImpactHandler;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.vehicle.AbstractMinecart;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(AbstractMinecart.class)
public abstract class AbstractMinecartMixin {

    @Inject(method = "tick", at = @At("TAIL"))
    private void onTick(CallbackInfo ci) {
        AbstractMinecart self = (AbstractMinecart) (Object) this;

        Level level = self.level(); 
        if (level == null || level.isClientSide()) return; // 仅服务端处理

        // 速度短路：若矿车速度不足则跳过（避免频繁查询实体）
        Vec3 mv = self.getDeltaMovement();
        double speedSqr = mv.lengthSqr();
        double threshold = MinecartImpactHandler.MIN_SPEED_THRESHOLD;
        if (speedSqr < threshold * threshold) return;

        // 检测范围
        double radius = 0.5;
        AABB box = self.getBoundingBox().inflate(radius);

        // 获取附近实体并过滤一些不需要的类型（自身、已死、不可交互等）
        List<Entity> list = level.getEntities(self, box, e -> e != self && e.isAlive());
        if (list.isEmpty()) return;

        for (Entity target : list) {

            // 对每个目标调用 handler（handler 内部处理冷却/服务端校验）
            MinecartImpactHandler.tryApplyImpact(self, target);
        }
    }
}