package org.fuseleaf.kineticminecart.mixin;

import org.fuseleaf.kineticminecart.kinetic.CartBehavior;
import org.fuseleaf.kineticminecart.kinetic.KineticManager;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.vehicle.minecart.AbstractMinecart;

@Mixin(AbstractMinecart.class)
public class AbstractMinecartMixin {

    @Inject(method = "tick", at = @At("TAIL"))
    private void injectTick(CallbackInfo ci) {
        KineticManager.tick((AbstractMinecart)(Object)this);
    }

    @Inject(method = "canCollideWith", at = @At("HEAD"), cancellable = true)
    private void injectCanCollideWith(Entity entity, CallbackInfoReturnable<Boolean> cir) {
        if (CartBehavior.isCollisionDisabledWith((AbstractMinecart)(Object)this, entity)) {
            cir.setReturnValue(false);
        }
    }
}
