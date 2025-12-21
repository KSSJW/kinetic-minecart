package com.kssjw.kineticminecart.mixin;

import net.minecraft.world.entity.vehicle.AbstractMinecart;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kssjw.kineticminecart.manager.KineticManager;

@Mixin(AbstractMinecart.class)
public abstract class AbstractMinecartMixin {

    @Inject(method = "tick", at = @At("TAIL"))
    private void onTick(CallbackInfo ci) {
        AbstractMinecart self = (AbstractMinecart) (Object) this;
        KineticManager.handler(self);
    }
}