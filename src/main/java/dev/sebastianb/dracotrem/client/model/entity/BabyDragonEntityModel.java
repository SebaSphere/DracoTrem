package dev.sebastianb.dracotrem.client.model.entity;

import dev.sebastianb.dracotrem.entity.BabyDragonEntity;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;


public class BabyDragonEntityModel extends EntityModel<BabyDragonEntity> {

    private final ModelPart base;


    public BabyDragonEntityModel() {
        this.textureHeight = 64;
        this.textureWidth = 64;

        base = new ModelPart(this, 0, 0);
        base.addCuboid(-6, -6, -6, 12, 12, 12);
    }

    @Override
    public void setAngles(BabyDragonEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        // lol
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
        matrices.translate(0, 1.125, 0);
        base.render(matrices, vertices, light, overlay);
    }
}
