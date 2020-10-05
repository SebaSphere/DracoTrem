package dev.sebastianb.dracotrem.items;

import dev.sebastianb.dracotrem.Constants;
import dev.sebastianb.dracotrem.DracoTrem;
import dev.sebastianb.dracotrem.sounds.DracoTremSounds;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;


public class DracoTremItems {
    public static final ItemGroup ITEMS_GROUP = FabricItemGroupBuilder.create(
            new Identifier(DracoTrem.MOD_ID, Constants.CreativeTabs.ITEM_GROUP))
            .icon(() -> new ItemStack(DracoTremItems.DRAGON_EGG_SHARD))
            .build();


    private static final Item.Settings MUSIC_DISC = new Item.Settings().maxCount(1).group(ITEMS_GROUP).rarity(Rarity.RARE).maxCount(1);



    // Items
    public static final Item DRAGON_EGG_SHARD = registerItem(Constants.Items.DRAGON_EGG_SHARD, new Item(new Item.Settings().group(ITEMS_GROUP)));
    public static final Item DRAGON_HEART = registerItem(Constants.Items.DRAGON_HEART, new Item(new Item.Settings().group(ITEMS_GROUP)));

    // Music Disc
    public static final Item DRAGON_BOSS_MUSIC = registerItem(Constants.Items.DRAGON_BOSS_MUSIC_DISC, new MusicDisk(14,DracoTremSounds.DRAGON_BOSS_MUSIC, MUSIC_DISC));




    private static <T extends Item> T registerItem(String id, T item) {
        return Registry.register(Registry.ITEM, new Identifier(DracoTrem.MOD_ID, id), item);
    }

    public static void register() {
    }
}
