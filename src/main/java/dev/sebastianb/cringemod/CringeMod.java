package dev.sebastianb.cringemod;

import dev.sebastianb.cringemod.sounds.ModIDSounds;
import net.fabricmc.api.ModInitializer;

public class CringeMod implements ModInitializer {
    @Override
    public void onInitialize() {
        ModIDSounds.register();

    }
}
