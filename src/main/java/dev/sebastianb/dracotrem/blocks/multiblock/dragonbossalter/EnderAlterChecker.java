package dev.sebastianb.dracotrem.blocks.multiblock.dragonbossalter;

import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.DragonEggBlock;
import net.minecraft.block.RespawnAnchorBlock;
import net.minecraft.util.ActionResult;

public class EnderAlterChecker {

    private void checkIfOnAnchor() {
        UseBlockCallback.EVENT.register(((playerEntity, world, hand, blockHitResult) -> ActionResult.success(world.getBlockState(blockHitResult.getBlockPos()).getBlock() instanceof RespawnAnchorBlock)));
    }


}
