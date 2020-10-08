package dev.sebastianb.dracotrem.blocks.multiblock.dragonbossalter;

import dev.sebastianb.dracotrem.DracoTrem;
import dev.sebastianb.dracotrem.sounds.DracoTremSounds;
import github.Louwind.Features.impl.feature.GenericFeature;
import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;
import net.fabricmc.fabric.api.network.ServerSidePacketRegistry;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;


/**
 * kill me, now I need to learn networking
 */
public class BlahEndAlterCheck {

    public static final GenericFeature<DefaultFeatureConfig> END_BOSS_ISLAND = new GenericFeature(new Identifier("dracotrem:end_boss_island"), DefaultFeatureConfig.CODEC);
    public static final GenericFeature<DefaultFeatureConfig> THICK_STRIPPED_SPRUCE = new GenericFeature(new Identifier("features:thick_stripped_spruce"), DefaultFeatureConfig.CODEC);

    public static final Identifier ISLAND_SUMMONER = new Identifier("dracotrem", "island_summons");






    public static void register() {
        test();
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


    private static ArrayList<EndCrystalEntity> endCrystalEntities = new ArrayList<EndCrystalEntity>();
    private static BlockPos respawnAch;


    private static void startIslandSpawning(World world, PlayerEntity playerEntity, BlockPos dragonEggPos, BlockPos respawnAnchor) {
        respawnAch = respawnAnchor;


        world.playSound(playerEntity, dragonEggPos, DracoTremSounds.DRAGON_BOSS_MUSIC, SoundCategory.MUSIC, 10f, 1f);

        for (Vec3i x: EndAlterMultiblock.dragonEggAlterEntity) {
            Vec3i relPos = respawnAnchor.add(x);
            List<Entity> list = world.getOtherEntities((Entity) null, new Box(relPos.getX(), relPos.getY(), relPos.getZ(), relPos.getX() + 1.0D, relPos.getY() + 2.0D, relPos.getZ() + 1.0D));
            if (!list.isEmpty()) {
                if (list.get(0) instanceof EndCrystalEntity) {

                    BlockPos endCrystalLocation = list.get(0).getBlockPos();
                    EndCrystalEntity endCrystalEntity = (EndCrystalEntity) list.get(0);
                    //endCrystalEntity.setBeamTarget(endCrystalLocation.add(0,200,0));
                    endCrystalEntities.add(endCrystalEntity); //adds each entity to the array list


                    PacketByteBuf passedData = new PacketByteBuf(Unpooled.buffer());
                    passedData.writeBlockPos(endCrystalLocation.add(0,20,0));
                    ClientSidePacketRegistry.INSTANCE.sendToServer(BlahEndAlterCheck.ISLAND_SUMMONER, passedData);



                }
            }


        }

        System.out.println("test");
        //DracoTrem.scheduler.repeatN(consumer, 8, 50, 50);
        System.out.println("TEST2");


    }
    private static int num = 0;
    private static int num2 = 0;

    private static void summonIsland(World world, BlockPos endIslandPos) {

        if (!world.isClient) {
            if (num == 8) {
                System.out.println("Attempting to summon at " + endIslandPos);
                ServerWorld server = (ServerWorld) world;
                ChunkGenerator chunkGenerator = server.getChunkManager().getChunkGenerator();
                if (num2 == 64) {
                    num = 0;
                    num2 = 0;
                    System.out.println("aa");
                    return;
                }

                END_BOSS_ISLAND.generate(server, chunkGenerator, world.getRandom(), endIslandPos, DefaultFeatureConfig.INSTANCE);
                num=0;
            }
            num++;
            num2++;
        }


    }



    //guess I'll start pain
    private static void test() {



        ServerSidePacketRegistry.INSTANCE.register(ISLAND_SUMMONER, (packetContext, attachedData) -> {
            BlockPos pos = attachedData.readBlockPos();
            AtomicInteger islandPosIndex = new AtomicInteger(0);
            packetContext.getTaskQueue().execute(() -> {

                Consumer<MinecraftServer> consumer = minecraftServer -> {
                    for (EndCrystalEntity endCrystalEntity : endCrystalEntities) {
                        BlockPos islandLoca = respawnAch.add(EndAlterMultiblock.dragonAlterIslandLocation.get(islandPosIndex.get()));
                        endCrystalEntity.setBeamTarget(islandLoca);
                        //place structure at coord
                    }
                    summonIsland(packetContext.getPlayer().world, respawnAch.add(EndAlterMultiblock.dragonAlterIslandLocation.get(islandPosIndex.get())));
                    islandPosIndex.getAndIncrement();
                };


                if (packetContext.getPlayer().world.canSetBlock(pos)) {
                    for (EndCrystalEntity endCrystalEntity : endCrystalEntities) {
                        endCrystalEntity.setBeamTarget(endCrystalEntity.getBlockPos().add(0,100,0));
                    }
                    DracoTrem.scheduler.repeatN(consumer, 8, 50, 50);

                }

            });

        });
    }

}
