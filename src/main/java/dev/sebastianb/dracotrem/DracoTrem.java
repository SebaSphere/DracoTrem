package dev.sebastianb.dracotrem;

import dev.sebastianb.dracotrem.items.DracoTremItems;
import dev.sebastianb.dracotrem.sounds.DracoTremSounds;
import net.fabricmc.api.ModInitializer;


public class DracoTrem implements ModInitializer {

    public static final String MOD_ID = "dracotrem";

    @Override
    public void onInitialize() {
        DracoTremSounds.register();
        DracoTremItems.register();
    }
}
