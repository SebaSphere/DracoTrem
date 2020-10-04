package dev.sebastianb.dracotrem.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.BatEntity;
import net.minecraft.world.World;


public class BabyDragonEntity extends BatEntity {

    public BabyDragonEntity(EntityType<? extends BatEntity> entityType, World world) {
        super(entityType, world);
    }

    public static DefaultAttributeContainer.Builder createBabyDragonAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.5D)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 48.0D);
    }
}
