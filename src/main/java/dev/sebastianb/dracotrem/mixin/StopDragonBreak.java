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


//Going to move the multiblock out the mixin. Register this later
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

    @Inject(method = "onUse", at = @At("HEAD"), cancellable = true)
    private void onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit, CallbackInfoReturnable<ActionResult> cir) {
        BlockPos hitBlockPos = hit.getBlockPos();
        BlockPos respawnAnchor = hitBlockPos.add(0,-1,0); // checks block beneath the egg
        boolean anchorUnderEgg = (world.getBlockState(respawnAnchor).getBlock() instanceof RespawnAnchorBlock) && (world.getBlockState(respawnAnchor).get(RespawnAnchorBlock.CHARGES) == 4); //Boolean for if anchor is under a dragon egg

            if (anchorUnderEgg) {
                world.playSound(player, hitBlockPos, DracoTremSounds.DRAGONEGGHIT_ERROR, SoundCategory.BLOCKS, 1f, 1f);
                cir.setReturnValue(ActionResult.PASS); //Use this so the teleportation animation does not happen
            }
    }
}
