package com.kssjw.kineticminecart.damage;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;

public final class DamageSources {
    private DamageSources() {}

    // 从 ServerLevel 获取通用伤害源（用于服务端执行的伤害调用）。
    public static DamageSource generic(ServerLevel serverLevel) {
        return serverLevel.damageSources().generic();
    }
}