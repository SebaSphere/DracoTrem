package dev.sebastianb.dracotrem.structure;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.structure.*;
import net.minecraft.structure.pool.StructurePoolElement;
import net.minecraft.structure.processor.BlockIgnoreStructureProcessor;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;

import java.util.Random;

public class EndIslandPiece extends StructurePiece {
    private final BlockRotation rotation;
    private final Identifier template;

    public EndIslandPiece(StructureManager structureManager, CompoundTag compoundTag) {
        super(DracoTremStructures.END_BOSS_ISLAND, compoundTag);
        this.template = new Identifier(compoundTag.getString("Template"));
        this.rotation = BlockRotation.valueOf(compoundTag.getString("Rot"));
        this.initializeStructureData(structureManager);
    }

    public EndIslandPiece(StructureManager structureManager, BlockPos pos, Identifier template, BlockRotation rotation) {
        super(DracoTremStructures.END_BOSS_ISLAND, 0);
        this.rotation = rotation;
        this.template = template;

        this.initializeStructureData(structureManager);
    }

    private void initializeStructureData(StructureManager structureManager) {
        Structure structure = structureManager.getStructureOrBlank(this.template);
        StructurePlacementData placementData = (new StructurePlacementData())
                .setRotation(this.rotation)
                .setMirror(BlockMirror.NONE)
                .addProcessor(BlockIgnoreStructureProcessor.IGNORE_STRUCTURE_BLOCKS);
    }

    @Override
    protected void toNbt(CompoundTag tag) {

    }

    @Override
    public boolean generate(StructureWorldAccess structureWorldAccess, StructureAccessor structureAccessor, ChunkGenerator chunkGenerator, Random random, BlockBox boundingBox, ChunkPos chunkPos, BlockPos blockPos) {
        return false;
    }
}
