package com.kssjw.kineticminecart.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.kssjw.kineticminecart.manager.KineticManager;

import net.minecraft.entity.Entity;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;

@Mixin(AbstractMinecartEntity.class)
public class AbstractMinecartEntityMixin {

    @Inject(method = "tick", at = @At("TAIL"))
    private void onTick(CallbackInfo ci) {
        AbstractMinecartEntity self = (AbstractMinecartEntity)(Object)this;
        KineticManager.handler(self);
        KineticManager.sendSpeedToClient(self);
    }

    @Inject(method = "collidesWith", at = @At("HEAD"), cancellable = true)
    private void onCollidesWith(Entity other, CallbackInfoReturnable<Boolean> cir) {
        AbstractMinecartEntity self = (AbstractMinecartEntity)(Object)this;
        switch (KineticManager.getCollideStatus(self, other)) {
            case -1:
                return;
            case 0:
                cir.setReturnValue(false);
                break;
            default:
                return;
        }
	}
}