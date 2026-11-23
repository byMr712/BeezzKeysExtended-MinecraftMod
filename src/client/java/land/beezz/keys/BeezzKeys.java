package land.beezz.keys;

import land.beezz.keys.mixin.OptionsSubScreenAccessor;
import land.beezz.keys.mixin.ScreenAccessor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.options.controls.KeyBindsScreen;
import net.minecraft.network.chat.Component;

import java.lang.ref.WeakReference;

/**
 * BeezzKeys: "Key Binds" in the controls opens a drawn keyboard instead of the plain list. Used keys are painted in the
 * colour of their category, free keys stay grey, conflicts are red. The vanilla list is one button away and has a button
 * that leads back to the keyboard.
 */
public final class BeezzKeys {
    /** The vanilla screen the player asked for with the "List" button; it is left alone. */
    private static WeakReference<Screen> vanillaWanted = new WeakReference<>(null);

    private BeezzKeys() {}

    /** The mixins do the ticks, the key binds screen hook and the translations; nothing to register here. */
    public static void init() {
    }

    /** Called at the end of the vanilla key binds screen init. */
    public static void afterKeyBindsInit(Minecraft mc, KeyBindsScreen screen) {
        Screen parent = ((OptionsSubScreenAccessor) screen).beezzkeys$lastScreen();
        if (vanillaWanted.get() == screen) {
            ((ScreenAccessor) screen).beezzkeys$addRenderableWidget(Button.builder(
                    Component.translatable("beezzkeys.button.keyboard"),
                    b -> Compat.setScreen(mc, new KeyboardScreen(parent))).bounds(6, 6, 90, 20).build());
        } else {
            mc.execute(() -> {
                if (Compat.screen(mc) == screen) Compat.setScreen(mc, new KeyboardScreen(parent));
            });
        }
    }

    static void openVanilla(Screen parent) {
        Minecraft mc = Minecraft.getInstance();
        KeyBindsScreen screen = new KeyBindsScreen(parent, mc.options);
        vanillaWanted = new WeakReference<>(screen);
        Compat.setScreen(mc, screen);
    }
}
