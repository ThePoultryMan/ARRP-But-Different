package io.github.thepoultryman.arrp_neoforge.mixins;

import io.github.thepoultryman.arrp_neoforge.ARRPCommon;
import net.minecraft.server.Bootstrap;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Bootstrap.class)
public class BootstrapMixin {
    @Shadow private static volatile boolean isBootstrapped;

    @Inject(at = @At("HEAD"), method = "bootStrap")
    private static void arrp_neoforge$bootStrap(CallbackInfo ci) {
        if (!isBootstrapped) {
            ARRPCommon.startEventBus();
        }
    }
}
