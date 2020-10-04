package dev.sebastianb.dracotrem.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.item.EndCrystalItem;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(EndCrystalItem.class)
public class EndCrystalCryingObby {

    @Inject(method = "useOnBlock", at = @At("HEAD"), cancellable = true)
    private void useOnBlock(ItemUsageContext context, CallbackInfoReturnable<ActionResult> cir) {
        World world = context.getWorld();
        BlockPos blockPos = context.getBlockPos();
        BlockState blockState = world.getBlockState(blockPos);
        if (blockState.isOf(Blocks.CRYING_OBSIDIAN)) {
            BlockPos blockPos2 = blockPos.up();
            double x = (double)blockPos2.getX();
            double y = (double)blockPos2.getY();
            double z = (double)blockPos2.getZ();
            List<Entity> list = world.getOtherEntities((Entity)null, new Box(x, y, z, x + 1.0D, y + 2.0D, z + 1.0D));
            if (!list.isEmpty()) {
                cir.setReturnValue(ActionResult.FAIL);
                return;
            }
            EndCrystalEntity endCrystalEntity = new EndCrystalEntity(world, x + 0.5D, y, z + 0.5D);
            endCrystalEntity.setShowBottom(false);
            world.spawnEntity(endCrystalEntity);
        }
    }

}