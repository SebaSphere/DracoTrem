package dev.sebastianb.dracotrem.blocks.multiblock.dragonbossalter;

import dev.sebastianb.dracotrem.DracoTrem;
import dev.sebastianb.dracotrem.sounds.DracoTremSounds;
import dev.sebastianb.dracotrem.structure.DracoTremStructures;
import dev.sebastianb.dracotrem.structure.EndBossIslandGenerator;
import github.Louwind.Features.impl.feature.GenericFeature;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.structure.Structure;
import net.minecraft.structure.StructureManager;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkManager;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

public class EndAlterCheck {

    public static final GenericFeature<DefaultFeatureConfig> END_BOSS_ISLAND = new GenericFeature(new Identifier("dracotrem:end_boss_island"), DefaultFeatureConfig.CODEC);
    public static final GenericFeature<DefaultFeatureConfig> THICK_STRIPPED_SPRUCE = new GenericFeature(new Identifier("features:thick_stripped_spruce"), DefaultFeatureConfig.CODEC);





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
        AtomicInteger num = new AtomicInteger();


        if (!(world.isClient)) {
            ServerWorld server = (ServerWorld) world;
            ChunkGenerator chunkGenerator = server.getChunkManager().getChunkGenerator();
        }



        Consumer<MinecraftServer> consumer = minecraftServer -> {
            for (EndCrystalEntity endCrystalEntity : endCrystalEntities) {
                BlockPos pos = respawnAnchor.add(EndAlterMultiblock.dragonAlterIslandLocation.get(num.get()));
                endCrystalEntity.setBeamTarget(pos);
                //place structure at coord
                summonIsland(world, pos);
            }
            num.getAndIncrement();
        };


        world.playSound(playerEntity, dragonEggPos, DracoTremSounds.DRAGON_BOSS_MUSIC, SoundCategory.MUSIC, 10f, 1f);

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
        DracoTrem.scheduler.repeatN(consumer, 8, 50, 50);
        System.out.println("TEST2");


    }
    private static void summonIsland(World world, BlockPos endIslandPos) {
        if (!(world.isClient)) {
            ServerWorld server = (ServerWorld) world;
            ChunkGenerator chunkGenerator = server.getChunkManager().getChunkGenerator();

            THICK_STRIPPED_SPRUCE.generate(server, chunkGenerator, world.getRandom(), endIslandPos, DefaultFeatureConfig.INSTANCE);
        }
    }

}
