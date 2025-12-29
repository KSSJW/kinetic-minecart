package com.kssjw.kineticminecart.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.kssjw.kineticminecart.manager.KineticManager;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.vehicle.AbstractMinecart;

@Mixin(AbstractMinecart.class)
public abstract class AbstractMinecartMixin {

    @Inject(method = "tick", at = @At("TAIL"))
    private void onTick(CallbackInfo ci) {
        AbstractMinecart self = (AbstractMinecart) (Object) this;
        KineticManager.handler(self);
    }

    @Inject(method = "canCollideWith", at = @At("HEAD"), cancellable = true)
    public void onCollidesWith(Entity other, CallbackInfoReturnable<Boolean> cir) {
        switch (KineticManager.collide()) {
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