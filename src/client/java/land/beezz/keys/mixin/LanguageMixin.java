package land.beezz.keys.mixin;

import land.beezz.keys.Lang;
import net.minecraft.locale.Language;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/** Makes Component.translatable resolve the mod's keys even when its resource pack is not loaded. */
@Mixin(Language.class)
public class LanguageMixin {

    @Inject(method = "getOrDefault(Ljava/lang/String;)Ljava/lang/String;", at = @At("HEAD"), cancellable = true)
    private void beezzkeys$translation(String key, CallbackInfoReturnable<String> cir) {
        String value = Lang.raw(key);
        if (value != null) cir.setReturnValue(value);
    }
}
