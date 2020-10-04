package dev.sebastianb.dracotrem.entity;

import dev.sebastianb.dracotrem.Constants;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static dev.sebastianb.dracotrem.DracoTrem.MOD_ID;


public class DracoTremEntities {

    public static final EntityType<BabyDragonEntity> BABY_DRAGON = Registry.register(Registry.ENTITY_TYPE,
            new Identifier(MOD_ID, Constants.Entities.BABY_DRAGON),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, BabyDragonEntity::new)
                    .dimensions(EntityDimensions.fixed(0.75F, 0.75F)).build());

    public static void register() {
        FabricDefaultAttributeRegistry.register(BABY_DRAGON, BabyDragonEntity.createBabyDragonAttributes());
    }
}
