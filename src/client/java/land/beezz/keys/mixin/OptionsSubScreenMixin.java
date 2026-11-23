package land.beezz.keys.mixin;

import land.beezz.keys.BeezzKeys;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.options.OptionsSubScreen;
import net.minecraft.client.gui.screens.options.controls.KeyBindsScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Replaces Fabric API's screen init event. KeyBindsScreen does not override init itself: the hook lives in its parent
 * OptionsSubScreen and only acts on the key binds screen.
 */
@Mixin(OptionsSubScreen.class)
public class OptionsSubScreenMixin {

    @Inject(method = "init", at = @At("TAIL"))
    private void beezzkeys$keyboard(CallbackInfo ci) {
        if ((Object) this instanceof KeyBindsScreen screen) {
            BeezzKeys.afterKeyBindsInit(Minecraft.getInstance(), screen);
        }
    }
}
