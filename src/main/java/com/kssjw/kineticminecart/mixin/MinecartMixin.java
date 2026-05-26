package com.kssjw.kineticminecart.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.kssjw.kineticminecart.manager.ConfigManager;
import com.kssjw.kineticminecart.util.SpeedUtil;

import net.minecraft.world.entity.vehicle.minecart.Minecart;

@Mixin(Minecart.class)
public class MinecartMixin {

    @Inject(method = "isRideable", at = @At("TAIL"), cancellable = true)
    private void injectIsRideable(CallbackInfoReturnable<Boolean> cir) {
        Minecart self = (Minecart)(Object)this;

        if (ConfigManager.isOverroadRiding() && SpeedUtil.getSpeed(self) > 2) cir.setReturnValue(false);
    }
}