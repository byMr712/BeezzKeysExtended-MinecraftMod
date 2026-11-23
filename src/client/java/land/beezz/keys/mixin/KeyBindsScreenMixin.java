package land.beezz.keys.mixin;

import land.beezz.keys.BeezzKeys;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.options.controls.KeyBindsScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/** Replaces Fabric API's screen init event: the vanilla key binds screen becomes the drawn keyboard. */
@Mixin(KeyBindsScreen.class)
public class KeyBindsScreenMixin {

    @Inject(method = "init", at = @At("TAIL"))
    private void beezzkeys$keyboard(CallbackInfo ci) {
        BeezzKeys.afterKeyBindsInit(Minecraft.getInstance(), (KeyBindsScreen) (Object) this);
    }
}
