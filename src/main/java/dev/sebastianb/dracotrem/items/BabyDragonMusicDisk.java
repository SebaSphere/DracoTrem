package dev.sebastianb.dracotrem.items;

import dev.sebastianb.dracotrem.sounds.DracoTremSounds;
import net.minecraft.item.MusicDiscItem;
import net.minecraft.sound.SoundEvent;


public class BabyDragonMusicDisk extends MusicDiscItem {
    protected BabyDragonMusicDisk(int comparatorOutput, SoundEvent sound, Settings settings) {
        super(comparatorOutput, DracoTremSounds.DRAGON_BOSS_MUSIC, settings);
    }
}
