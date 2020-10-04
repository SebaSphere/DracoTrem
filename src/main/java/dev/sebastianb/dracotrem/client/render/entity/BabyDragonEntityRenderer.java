package dev.sebastianb.dracotrem.client.render.entity;

import dev.sebastianb.dracotrem.client.model.entity.BabyDragonEntityModel;
import dev.sebastianb.dracotrem.entity.BabyDragonEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.resource.ReloadableResourceManager;
import net.minecraft.util.Identifier;

import static dev.sebastianb.dracotrem.DracoTrem.MOD_ID;


public class BabyDragonEntityRenderer extends MobEntityRenderer<BabyDragonEntity, BabyDragonEntityModel> {

    private static final Identifier TEXTURE = new Identifier(MOD_ID, "textures/entity/babydragon.png");


    public BabyDragonEntityRenderer(EntityRenderDispatcher dispatcher) {
        super(dispatcher, new BabyDragonEntityModel(), 0.5F);
    }

    @Override
    public Identifier getTexture(BabyDragonEntity entity) {
        return TEXTURE;
    }
}
