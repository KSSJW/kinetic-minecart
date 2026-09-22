package org.fuseleaf.kineticminecart.mixin;

import org.fuseleaf.kineticminecart.kinetic.CartBehavior;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.world.entity.vehicle.minecart.Minecart;

@Mixin(Minecart.class)
public class MinecartMixin {

    @Inject(method = "isRideable", at = @At("TAIL"), cancellable = true)
    private void injectIsRideable(CallbackInfoReturnable<Boolean> cir) {
        if (CartBehavior.isRidingDisabled((Minecart)(Object)this)) {
            cir.setReturnValue(false);
        }
    }
}
