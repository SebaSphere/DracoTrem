package dev.sebastianb.cringemod.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.DragonEggBlock;
import net.minecraft.block.RespawnAnchorBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
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

        if (world.getBlockState(respawnAnchor).getBlock() instanceof RespawnAnchorBlock) {
            System.out.println("has been click");
        }
    }
}