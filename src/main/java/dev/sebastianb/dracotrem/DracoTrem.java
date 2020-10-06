package dev.sebastianb.dracotrem;

import dev.sebastianb.dracotrem.blocks.multiblock.dragonbossalter.EndAlterCheck;
import dev.sebastianb.dracotrem.entity.DracoTremEntities;
import dev.sebastianb.dracotrem.items.DracoTremItems;
import dev.sebastianb.dracotrem.sounds.DracoTremSounds;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.util.ActionResult;


public class DracoTrem implements ModInitializer {

    public static final String MOD_ID = "dracotrem";

    @Override
    public void onInitialize() {
        DracoTremSounds.register();
        DracoTremItems.register();
        DracoTremEntities.register();

        //register features
        EndAlterCheck.register();
    }
}
