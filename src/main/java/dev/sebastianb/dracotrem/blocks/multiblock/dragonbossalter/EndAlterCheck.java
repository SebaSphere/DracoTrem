package dev.sebastianb.dracotrem.blocks.multiblock.dragonbossalter;

import com.sun.jna.Structure;
import dev.sebastianb.dracotrem.sounds.DracoTremSounds;
import dev.sebastianb.dracotrem.utils.Scheduler;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.*;
import net.minecraft.block.entity.FurnaceBlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.dragon.EnderDragonSpawnState;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.sound.SoundCategory;
import net.minecraft.structure.StructureManager;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Tickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.ScheduledTick;
import net.minecraft.world.TickScheduler;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class EndAlterCheck {
    public static void register() {
        checkMultiblockValidation();
    }

    private static void checkMultiblockValidation() {
        UseBlockCallback.EVENT.register((playerEntity, world, hand, blockHitResult) -> {
            BlockPos dragonEggPos = blockHitResult.getBlockPos();
            BlockPos respawnAnchorPos = dragonEggPos.add(0,-1,0);
            Block dragonEgg = world.getBlockState(dragonEggPos).getBlock();
            int blockCount = 0;

            if (dragonEgg instanceof DragonEggBlock) {
                if (world.getBlockState(respawnAnchorPos).getBlock() instanceof RespawnAnchorBlock) {
                    for (Vec3i blockPositionsBase : EndAlterMultiblock.dragonEggAlter) {
                        if (world.getBlockState(respawnAnchorPos.add(blockPositionsBase)) == Blocks.CRYING_OBSIDIAN.getDefaultState()) { //checks if the block is crying obsidian for towers
                            blockCount++;
                            if (blockCount == EndAlterMultiblock.dragonEggAlter.size()) {
                                blockCount = 0;
                                for (Vec3i x: EndAlterMultiblock.dragonEggAlterEntity) {
                                    Vec3i relPos = respawnAnchorPos.add(x);
                                    List<Entity> list = world.getOtherEntities((Entity) null, new Box(relPos.getX(), relPos.getY(), relPos.getZ(), relPos.getX() + 1.0D, relPos.getY() + 2.0D, relPos.getZ() + 1.0D));
                                    if (!list.isEmpty()) {
                                        if (list.get(0) instanceof EndCrystalEntity) {
                                            blockCount++;
                                            if (blockCount == 8) {
                                                startIslandSpawning(world, playerEntity, dragonEggPos, respawnAnchorPos);
                                                return ActionResult.FAIL;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    world.playSound(playerEntity, dragonEggPos, DracoTremSounds.DRAGONEGGHIT_ERROR, SoundCategory.BLOCKS, 1f, 1f);
                    return ActionResult.FAIL;
                }
            }
            return ActionResult.PASS;
        });
    }
    private static void startIslandSpawning(World world, PlayerEntity playerEntity, BlockPos dragonEggPos, BlockPos respawnAnchor) {
        ArrayList<EndCrystalEntity> endCrystalEntities = new ArrayList<EndCrystalEntity>();
        Consumer<MinecraftServer> consumer = minecraftServer -> {
            System.out.println("@");
            for (EndCrystalEntity endCrystalEntity: endCrystalEntities) {
                System.out.println("AAA");
                endCrystalEntity.setBeamTarget(respawnAnchor.add(0,20,-50));
            }
        };

        world.playSound(playerEntity, dragonEggPos, DracoTremSounds.DRAGON_BOSS_MUSIC, SoundCategory.MUSIC, 10f, 1f);
        Scheduler scheduler = new Scheduler();
        for (Vec3i x: EndAlterMultiblock.dragonEggAlterEntity) {
            Vec3i relPos = respawnAnchor.add(x);
            List<Entity> list = world.getOtherEntities((Entity) null, new Box(relPos.getX(), relPos.getY(), relPos.getZ(), relPos.getX() + 1.0D, relPos.getY() + 2.0D, relPos.getZ() + 1.0D));
            if (!list.isEmpty()) {
                if (list.get(0) instanceof EndCrystalEntity) {

                    BlockPos endCrystalLocation = list.get(0).getBlockPos();
                    EndCrystalEntity endCrystalEntity = (EndCrystalEntity) list.get(0);
                    endCrystalEntity.setBeamTarget(endCrystalLocation.add(0,200,0));
                    endCrystalEntities.add(endCrystalEntity); //adds each entity to the array list
                }
            }


        }
        System.out.println("test");
        //scheduler.queue(consumer, 200);
        scheduler.repeatN(consumer, 6, 3, 3);
        System.out.println("TEST2");

    }



}
