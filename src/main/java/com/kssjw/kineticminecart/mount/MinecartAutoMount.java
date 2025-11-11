package com.kssjw.kineticminecart.mount;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.AbstractMinecart;
import net.minecraft.world.entity.vehicle.Minecart;
import net.minecraft.world.level.block.PoweredRailBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.phys.AABB;

import java.util.List;
import java.util.function.Predicate;

public final class MinecartAutoMount {

    // 主入口：在每个矿车 tick 或全局 tick 调用
    public static void maybeAutoMount(AbstractMinecart minecart) {

        // 仅服务器端
        if (minecart.level().isClientSide()) return;
        if (!(minecart.level() instanceof ServerLevel)) return;

        // 排除情况
        if (
            !(minecart instanceof Minecart) // 只允许普通矿车触发，排除运输矿车这些杂鱼
            || !minecart.getPassengers().isEmpty()  // 已有乘客则不触发
        ) return;

        // 原版未激活的动力铁轨上的矿车不能吸引生物
        BlockPos pos = minecart.blockPosition();
        BlockState state = minecart.level().getBlockState(pos);
        if (state.getBlock() instanceof PoweredRailBlock && !state.getValue(PoweredRailBlock.POWERED)) return;
        
        // 检测范围内的实体
        AABB box = minecart.getBoundingBox();
        List<Entity> nearby = minecart.level().getEntities(minecart, box, canAutoMountFilter(minecart));

        for (Entity e : nearby) {

            // 再次确认：目标没有已在骑乘、并且能被骑
            if (e.isPassenger() || e.isVehicle()) continue;

            // 强制上车（服务器端调用）
            boolean mounted = e.startRiding(minecart);
            if (mounted) break; // 一个实体上车就够了
        }
    }

    // 白名单过滤：只针对想要自动上车的实体类型
    private static Predicate<Entity> canAutoMountFilter(AbstractMinecart minecart) {
        return entity -> {

            // 排除情况
            if (entity.level().isClientSide()   // 排除客户端
                || entity.isPassenger() // 排除已经在骑乘
                || entity.isVehicle()   // 排除被骑
                || entity instanceof ItemEntity // 排除掉落物与其他非生物实体
                || entity instanceof Player // 排除玩家
            ) return false;

            // 允许所有 LivingEntity（动物、村民、怪物、马等）
            if (entity instanceof LivingEntity) return true;

            // 其余类型一律不允许
            return false;
        };
    }
}