package dev.sebastianb.dracotrem.structure;

import dev.sebastianb.dracotrem.DracoTrem;
import net.fabricmc.fabric.api.structure.v1.FabricStructureBuilder;
import net.minecraft.structure.MineshaftGenerator;
import net.minecraft.structure.Structure;
import net.minecraft.structure.StructurePiece;
import net.minecraft.structure.StructurePieceType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.ConfiguredStructureFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;

import java.util.Locale;

public class DracoTremStructures {

    public static final StructurePieceType END_BOSS_ISLAND = registerStructure(EndIslandPiece::new, "aa");


//    public static void register() {
//        FabricStructureBuilder.create(new Identifier(DracoTrem.MOD_ID, "end_boss_island"), END_BOSS_ISLAND)
//                .register();
//    }

    static StructurePieceType registerStructure(StructurePieceType pieceType, String id) {
        return (StructurePieceType) Registry.register(Registry.STRUCTURE_PIECE, (String)id.toLowerCase(Locale.ROOT), pieceType);
    }
}
