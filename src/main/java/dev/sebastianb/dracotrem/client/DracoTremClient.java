package dev.sebastianb.dracotrem.client;

import dev.sebastianb.dracotrem.client.render.entity.BabyDragonEntityRenderer;
import dev.sebastianb.dracotrem.entity.DracoTremEntities;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;


@Environment(EnvType.CLIENT)
public class DracoTremClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.INSTANCE.register(DracoTremEntities.BABY_DRAGON,
                (entityRenderDispatcher, context) -> new BabyDragonEntityRenderer(entityRenderDispatcher));
    }
}
