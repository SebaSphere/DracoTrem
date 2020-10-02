package dev.sebastianb.cringemod.sounds;

import dev.sebastianb.cringemod.Constants;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModIDSounds {
    public static final SoundEvent DRAGONEGGHIT_ERROR = new SoundEvent(new Identifier(Constants.MOD_ID, "dragonegghit_error"));


    public static void register() {
        Registry.register(Registry.SOUND_EVENT, new Identifier(Constants.MOD_ID, "dragonegghit_error"), DRAGONEGGHIT_ERROR);

    }


}
