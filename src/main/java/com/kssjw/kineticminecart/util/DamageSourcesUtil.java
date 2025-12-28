package com.kssjw.kineticminecart.util;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;

public final class DamageSourcesUtil {
    
    private DamageSourcesUtil() {}

    // 从 ServerLevel 获取通用伤害源（用于服务端执行的伤害调用）。
    public static DamageSource generic(ServerLevel serverLevel) {
        return serverLevel.damageSources().generic();
    }
}