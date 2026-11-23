package land.beezz.keys;

import net.neoforged.fml.common.Mod;

/** NeoForge entrypoint. The Fabric entrypoint is excluded from the NeoForge JAR. */
@Mod("beezzkeys")
public class BeezzKeysNeoForge {

    public BeezzKeysNeoForge() {
        BeezzKeys.init();
    }
}
