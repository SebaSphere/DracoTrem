package dev.sebastianb.dracotrem.sounds;

import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static dev.sebastianb.dracotrem.DracoTrem.MOD_ID;


public class DracoTremSounds {

    public static final SoundEvent DRAGONEGGHIT_ERROR = new SoundEvent(new Identifier(MOD_ID, "dragonegghit_error"));
    public static final SoundEvent DRAGON_BOSS_MUSIC = new SoundEvent(new Identifier(MOD_ID,"babydragonbossmusic01"));


    public static void register() {
        Registry.register(Registry.SOUND_EVENT, new Identifier(MOD_ID, "dragonegghit_error"), DRAGONEGGHIT_ERROR);
        Registry.register(Registry.SOUND_EVENT, new Identifier(MOD_ID, "babydragonbossmusic01"), DRAGON_BOSS_MUSIC);
    }
}
