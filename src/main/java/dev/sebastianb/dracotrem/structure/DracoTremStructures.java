package dev.sebastianb.dracotrem.structure;

import net.fabricmc.fabric.api.structure.v1.FabricStructureBuilder;
import net.minecraft.structure.StructurePieceType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

public class DracoTremStructures {
    public static final StructurePieceType END_BOSS_ISLAND_PIECE = EndBossIslandGenerator.EndIslandPiece::new;
    private static final StructureFeature<DefaultFeatureConfig> END_BOSS_ISLAND_STRUCTURE = new EndBossIslandFeature(DefaultFeatureConfig.CODEC);




    public static void register() {
        Registry.register(Registry.STRUCTURE_PIECE, new Identifier("dracotrem", "baby_dragon_boss/end_boss_island"), END_BOSS_ISLAND_PIECE);
        FabricStructureBuilder.create(new Identifier("dracotrem", "baby_dragon_boss/end_boss_island"), END_BOSS_ISLAND_STRUCTURE)
                .step(GenerationStep.Feature.SURFACE_STRUCTURES)
                .defaultConfig(32, 8, 12345)
                .adjustsSurface()
                .register();
    }


}
