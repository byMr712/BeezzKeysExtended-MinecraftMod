package land.beezz.keys;

import net.fabricmc.api.ClientModInitializer;

/**
 * Fabric entrypoint. The NeoForge JAR excludes this class and uses
 * {@code land.beezz.keys.BeezzKeysNeoForge} instead.
 */
public class BeezzKeysClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        BeezzKeys.init();
    }
}
