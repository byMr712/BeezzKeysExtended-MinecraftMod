package land.beezz.keys.mixin;

import land.beezz.keys.Macros;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/** Replaces Fabric API's client tick event. */
@Mixin(Minecraft.class)
public class MinecraftMixin {

    @Inject(method = "tick", at = @At("TAIL"))
    private void beezzkeys$tick(CallbackInfo ci) {
        Macros.tick((Minecraft) (Object) this);
    }
}
