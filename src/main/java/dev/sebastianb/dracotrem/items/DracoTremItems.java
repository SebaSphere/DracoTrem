package dev.sebastianb.dracotrem.items;

import dev.sebastianb.dracotrem.Constants;
import dev.sebastianb.dracotrem.DracoTrem;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class DracoTremItems {
    public static final ItemGroup ITEMS_GROUP = FabricItemGroupBuilder.create(
            new Identifier(DracoTrem.MOD_ID, Constants.Items.ITEM_GROUP))
            .icon(() -> new ItemStack(DracoTremItems.DRAGON_EGG_SHARD))
            .build();

    // MATERIALS
    public static final Item DRAGON_EGG_SHARD = registerItem(Constants.Items.DRAGON_EGG_SHARD, new Item(new Item.Settings().group(ITEMS_GROUP)));
    public static final Item DRAGON_HEART = registerItem(Constants.Items.DRAGON_HEART, new Item(new Item.Settings().group(ITEMS_GROUP)));



    private static <T extends Item> T registerItem(String id, T item) {
        return Registry.register(Registry.ITEM, new Identifier(DracoTrem.MOD_ID, id), item);
    }
    public static void register() {
    }
}
