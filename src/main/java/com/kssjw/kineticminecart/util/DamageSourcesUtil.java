package com.kssjw.kineticminecart.util;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.server.world.ServerWorld;

public final class DamageSourcesUtil {
    
    private DamageSourcesUtil() {}

    // 从 ServerWorld 获取通用伤害源（用于服务端执行的伤害调用）。
    public static DamageSource generic(ServerWorld serverWorld) {
        return serverWorld.getDamageSources().generic();
    }
}