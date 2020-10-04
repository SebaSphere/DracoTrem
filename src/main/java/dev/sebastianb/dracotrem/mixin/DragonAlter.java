package dev.sebastianb.dracotrem.mixin;

import dev.sebastianb.dracotrem.blocks.multiblock.EndAlterMultiblock;
import dev.sebastianb.dracotrem.sounds.DracoTremSounds;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.boss.dragon.EnderDragonSpawnState;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;


/*
Wait it's all mixins!
Always has been...
 */
@Mixin(DragonEggBlock.class)
public abstract class DragonAlter {

    @Inject(method = "onBlockBreakStart", at = @At("HEAD"), cancellable = true)
    private void preventBlockBreak(BlockState state, World world, BlockPos pos, PlayerEntity player, CallbackInfo ci) {
        if (world.getBlockState(pos.add(0,-1,0)).getBlock() instanceof RespawnAnchorBlock) {
            soundPlayer(player,world,pos,"eggError");
            ci.cancel();
        }
    }





    @Inject(method = "onUse", at = @At("HEAD"), cancellable = true)
    private void onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit, CallbackInfoReturnable<ActionResult> cir) {
        BlockPos hitBlockPos = hit.getBlockPos();
        BlockPos respawnAnchor = hitBlockPos.add(0,-1,0); // checks block beneath the egg
        boolean anchorUnderEgg = world.getBlockState(respawnAnchor).get(RespawnAnchorBlock.CHARGES) == 4; //Boolean for if anchor is under a dragon egg
        boolean multiblockValid = false; //Does the multiblock have the correct amount of blocks?
        int blockCount = 0; //inits the multiblock count checker

        if (world.isClient) {
            if (anchorUnderEgg) {
                soundPlayer(player,world,respawnAnchor,"eggError");
                cir.setReturnValue(ActionResult.PASS); //Use this so the teleportation animation does not happen
            }
            return;
        }

        if (anchorUnderEgg) {
            soundPlayer(player,world,respawnAnchor,"eggError");
            cir.setReturnValue(ActionResult.PASS); //Serverside checker so dragon egg does not teleport. Check the ActionResult class for more info.
                for (Vec3i blockPositionsBase : EndAlterMultiblock.dragonEggAlter) {
                    if (world.getBlockState(respawnAnchor.add(blockPositionsBase)) == Blocks.CRYING_OBSIDIAN.getDefaultState()) { //checks if the block is crying obsidian for towers
                        blockCount++;
                        if (blockCount == EndAlterMultiblock.dragonEggAlter.size()) {
                            multiblockValid = true;
                        }
                    }
                }
            if (multiblockValid) {
                blockCount = 0;
                for (Vec3i x: EndAlterMultiblock.dragonEggAlterEntity) {
                    Vec3i relPos = respawnAnchor.add(x);
                    List<Entity> list = world.getOtherEntities((Entity) null, new Box(relPos.getX(), relPos.getY(), relPos.getZ(), relPos.getX() + 1.0D, relPos.getY() + 2.0D, relPos.getZ() + 1.0D));
                    if (!list.isEmpty()) {
                        if (list.get(0) instanceof EndCrystalEntity) {
                            blockCount++;
                            if (blockCount == 8) {
                                startIslandSpawning(world);
                            }
                        }
                    }
                }
            }
        }
    }


    private void startIslandSpawning(World world) {
        System.out.println("AA");
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