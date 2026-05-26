package com.kssjw.kineticminecart.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.kssjw.kineticminecart.manager.KineticManager;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.vehicle.minecart.AbstractMinecart;

@Mixin(AbstractMinecart.class)
public class AbstractMinecartMixin {

    @Inject(method = "tick", at = @At("TAIL"))
    private void injectTick(CallbackInfo ci) {
        AbstractMinecart self = (AbstractMinecart)(Object)this;
        KineticManager.handler(self);
    }

    @Inject(method = "canCollideWith", at = @At("HEAD"), cancellable = true)
    private void injectCanCollideWith(Entity other, CallbackInfoReturnable<Boolean> cir) {
        AbstractMinecart self = (AbstractMinecart)(Object)this;
        int status = KineticManager.getCollideStatus(self, other);

        if (status == 0) {
            cir.setReturnValue(false);
        }
	}
}