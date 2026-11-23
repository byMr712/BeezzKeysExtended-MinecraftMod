package land.beezz.keys;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.sounds.SoundEvents;

/** What differs between game versions. The branch picks one copy in build.gradle. */
final class Compat {
    static final InputConstants.Type KEYBOARD = InputConstants.Type.KEYSYM;
    static final int LGUI = 343, RGUI = 347, MENU = 348, KP_DIVIDE = 331, KP_SUBTRACT = 333, KP_DECIMAL = 330;

    private Compat() {}

    static Screen screen(Minecraft mc) {
        return mc.screen;
    }

    static void setScreen(Minecraft mc, Screen screen) {
        mc.setScreen(screen);
    }

    /** No loading overlay on top. */
    static boolean loaded(Minecraft mc) {
        return mc.getOverlay() == null;
    }

    /** The vanilla button click, also used for the keyboard's own hits. */
    static void click(Minecraft mc) {
        mc.getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.UI_BUTTON_CLICK, 1.0F));
    }
}
