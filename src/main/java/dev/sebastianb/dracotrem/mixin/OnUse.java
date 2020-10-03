package dev.sebastianb.dracotrem.mixin;

import dev.sebastianb.dracotrem.blocks.multiblock.EndAlterMultiblock;
import dev.sebastianb.dracotrem.sounds.DracoTremSounds;
import net.minecraft.block.*;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.EndCrystalItem;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;



/*
Wait it's all mixins!
Always has been...
 */
@Mixin(DragonEggBlock.class)
public abstract class OnUse {





    @Inject(method = "onUse", at = @At("HEAD"), cancellable = true)
    private void used(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit, CallbackInfoReturnable<ActionResult> cir) {
        BlockPos hitBlockPos = hit.getBlockPos();
        BlockPos respawnAnchor = hitBlockPos.add(0,-1,0); // checks block beneath the egg
        boolean anchorUnderEgg = world.getBlockState(respawnAnchor).getBlock() instanceof RespawnAnchorBlock; //Boolean for if anchor is under a dragon egg
        boolean multiblockValid = false; //Does the multiblock have the correct amount of blocks?
        int blockCount = 0; //inits the multiblock count checker

        if (world.isClient) {
            if (anchorUnderEgg) {
                cir.setReturnValue(ActionResult.PASS); //Use this so the teleportation animation does not happen
            }
            return;
        }

        if (anchorUnderEgg) {
            soundPlayer(player,world,respawnAnchor,"eggError");
            cir.setReturnValue(ActionResult.PASS); //Serverside checker so dragon egg does not teleport. Check the ActionResult class for more info.
                for (Vec3i blockPositionsBase : EndAlterMultiblock.dragonEggAlter) {
                    if ((world.getBlockState(respawnAnchor.add(blockPositionsBase)).getBlock() instanceof CryingObsidianBlock)) { //checks if the block is crying obsidian for towers
                        blockCount++;
                        if (blockCount == EndAlterMultiblock.dragonEggAlter.size()) {
                            multiblockValid = true;
                        }
                    }
                }
            if (multiblockValid) {
                System.out.println("AAAA");
                //check for if there are crystals at coords above crying obby
            }

        }
    }

    /**
     * Plays a sound
     * @param player Takes in the player.
     * @param world Takes in the world of player.
     * @param soundSource Where does the sound originate?
     * @param identifier What type of sound will play?
     */
    private void soundPlayer(PlayerEntity player, World world, BlockPos soundSource, String identifier) {
        switch (identifier) {
            case "eggError":
                world.playSound(player, soundSource, DracoTremSounds.DRAGONEGGHIT_ERROR, SoundCategory.BLOCKS, 1f, 1f);
                break;
            case "eggSuccess":
                world.playSound(player, soundSource, SoundEvents.BLOCK_ANVIL_FALL, SoundCategory.BLOCKS, 1f, 1f);
                break;
            default:
                world.playSound(player, soundSource, SoundEvents.ENTITY_PIG_DEATH, SoundCategory.MASTER, 1f, 1f);

        }
    }
}