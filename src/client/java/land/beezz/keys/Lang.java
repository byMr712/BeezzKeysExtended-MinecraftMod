package land.beezz.keys;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraft.client.Minecraft;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * Reads the mod's lang files straight from the JAR. Fabric API's resource loader is not a dependency, so
 * {@code Component.translatable} would show raw keys on a clean Fabric install.
 */
public final class Lang {
    private static String code;
    private static Map<String, String> lang = Map.of();

    private Lang() {}

    /** Raw translation for the vanilla Language mixin, or null when the key is not ours. */
    public static String raw(String key) {
        Minecraft minecraft = Minecraft.getInstance();
        String selected = minecraft != null && minecraft.getLanguageManager() != null
                ? minecraft.getLanguageManager().getSelected() : "en_us";
        if (selected.equals(code)) return lang.get(key);
        code = selected;
        String file = "ru_ru".equals(selected) ? "ru_ru" : "en_us";
        String path = "/assets/beezzkeys/lang/" + file + ".json";
        Map<String, String> map = new HashMap<>();
        try (InputStream in = Lang.class.getResourceAsStream(path)) {
            if (in != null) {
                JsonObject json = JsonParser.parseReader(new InputStreamReader(in, StandardCharsets.UTF_8)).getAsJsonObject();
                for (Map.Entry<String, com.google.gson.JsonElement> e : json.entrySet()) {
                    map.put(e.getKey(), e.getValue().getAsString());
                }
            }
        } catch (Exception e) {
            map.clear();
        }
        lang = map;
        return lang.get(key);
    }
}
