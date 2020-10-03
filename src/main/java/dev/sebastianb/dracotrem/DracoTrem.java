package dev.sebastianb.dracotrem;

import dev.sebastianb.dracotrem.sounds.DracoTremSounds;
import net.fabricmc.api.ModInitializer;

public class DracoTrem implements ModInitializer {
    @Override
    public void onInitialize() {
        DracoTremSounds.register();

    }
}
