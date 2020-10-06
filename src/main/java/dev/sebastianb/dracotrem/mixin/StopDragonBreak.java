package dev.sebastianb.dracotrem.mixin;

import dev.sebastianb.dracotrem.sounds.DracoTremSounds;
import net.minecraft.block.BlockState;
import net.minecraft.block.DragonEggBlock;
import net.minecraft.block.RespawnAnchorBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


//move to event later
@SuppressWarnings("unused")
@Mixin(DragonEggBlock.class)
public class StopDragonBreak {

    @Inject(method = "onBlockBreakStart", at = @At("HEAD"), cancellable = true)
    private void preventBlockBreak(BlockState state, World world, BlockPos dragonEggPos, PlayerEntity player, CallbackInfo ci) {
        if (world.getBlockState(dragonEggPos.add(0,-1,0)).getBlock() instanceof RespawnAnchorBlock) {
            world.playSound(player, dragonEggPos, DracoTremSounds.DRAGONEGGHIT_ERROR, SoundCategory.BLOCKS, 1f, 1f);
            ci.cancel();
        }
    }

}
