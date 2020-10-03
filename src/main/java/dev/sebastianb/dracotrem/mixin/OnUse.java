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
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;


/*
Wait it's all mixins!
Always has been...
 */
@Mixin(DragonEggBlock.class)
public abstract class OnUse {

    private Random r = new Random();



    @Inject(method = "onUse", at = @At("HEAD"), cancellable = true)
    private void used(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit, CallbackInfoReturnable<ActionResult> cir) {
        BlockPos hitBlockPos = hit.getBlockPos();
        BlockPos respawnAnchor = hitBlockPos.add(0,-1,0); // checks block beneath the egg

        if (world.getBlockState(respawnAnchor).getBlock() instanceof RespawnAnchorBlock) {

            soundPlayer(player, world, respawnAnchor, "eggError");
            cir.setReturnValue(ActionResult.PASS); //sets the return value to pass. Check the ActionResult class for more values

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