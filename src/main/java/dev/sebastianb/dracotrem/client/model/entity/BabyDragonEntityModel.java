// Made with Blockbench 3.6.6
// Exported for Minecraft version 1.12.2 or 1.15.2 (same format for both) for entity models animated with GeckoLib
// Paste this class into your mod and follow the documentation for GeckoLib to use animations. You can find the documentation here: https://github.com/bernie-g/geckolib
// Blockbench plugin created by Gecko
package dev.sebastianb.dracotrem.client.model.entity;

import com.google.gson.JsonObject;
import dev.sebastianb.dracotrem.entity.BabyDragonEntity;
import software.bernie.geckolib.forgetofabric.ResourceLocation;
import software.bernie.geckolib.animation.model.AnimatedEntityModel;
import software.bernie.geckolib.animation.render.AnimatedModelRenderer;

import static dev.sebastianb.dracotrem.DracoTrem.MOD_ID;


public class BabyDragonEntityModel extends AnimatedEntityModel<BabyDragonEntity> {

    private final AnimatedModelRenderer body;
	private final AnimatedModelRenderer neck0;
	private final AnimatedModelRenderer neck1;
	private final AnimatedModelRenderer neck2;
	private final AnimatedModelRenderer head;
	private final AnimatedModelRenderer mouth0;
	private final AnimatedModelRenderer mouth1;
	private final AnimatedModelRenderer wing_l0;
	private final AnimatedModelRenderer wing_l1;
	private final AnimatedModelRenderer wing_r0;
	private final AnimatedModelRenderer wing_r1;
	private final AnimatedModelRenderer tail0;
	private final AnimatedModelRenderer tail1;
	private final AnimatedModelRenderer tail2;
	private final AnimatedModelRenderer tail3;
	private final AnimatedModelRenderer tail4;
	private final AnimatedModelRenderer tail5;
	private final AnimatedModelRenderer arml0;
	private final AnimatedModelRenderer arml1;
	private final AnimatedModelRenderer legr0;
	private final AnimatedModelRenderer legr1;
	private final AnimatedModelRenderer legr2;
	private final AnimatedModelRenderer legl0;
	private final AnimatedModelRenderer legl1;
	private final AnimatedModelRenderer legl2;
	private final AnimatedModelRenderer armr0;
	private final AnimatedModelRenderer armr1;

    public BabyDragonEntityModel() {
        textureWidth = 64;
        textureHeight = 64;
        body = new AnimatedModelRenderer(this);
		body.setRotationPoint(0.0F, 11.0F, 7.0F);
		body.setTextureOffset(0, 9).addBox(-2.0F, -3.0F, -6.0F, 5.0F, 6.0F, 12.0F, 0.0F, false);
		body.setTextureOffset(14, 6).addBox(0.0F, -4.0F, -5.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
		body.setTextureOffset(14, 6).addBox(0.0F, -4.0F, 3.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
		body.setTextureOffset(14, 6).addBox(0.0F, -4.0F, -1.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
		body.setModelRendererName("body");
		this.registerModelRenderer(body);

		neck0 = new AnimatedModelRenderer(this);
		neck0.setRotationPoint(0.5F, 0.5F, -6.0F);
		body.addChild(neck0);
		neck0.setTextureOffset(52, 21).addBox(-1.5F, -1.5F, -3.0F, 3.0F, 3.0F, 3.0F, 0.0F, false);
		neck0.setTextureOffset(56, 19).addBox(-0.5F, -2.5F, -2.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		neck0.setModelRendererName("neck0");
		this.registerModelRenderer(neck0);

		neck1 = new AnimatedModelRenderer(this);
		neck1.setRotationPoint(0.0F, 0.0F, -3.0F);
		neck0.addChild(neck1);
		neck1.setTextureOffset(52, 21).addBox(-1.5F, -1.5F, -3.0F, 3.0F, 3.0F, 3.0F, 0.0F, false);
		neck1.setTextureOffset(56, 19).addBox(-0.5F, -2.5F, -2.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		neck1.setModelRendererName("neck1");
		this.registerModelRenderer(neck1);

		neck2 = new AnimatedModelRenderer(this);
		neck2.setRotationPoint(0.0F, 0.0F, -3.0F);
		neck1.addChild(neck2);
		neck2.setTextureOffset(52, 21).addBox(-1.5F, -1.5F, -3.0F, 3.0F, 3.0F, 3.0F, 0.0F, false);
		neck2.setTextureOffset(56, 19).addBox(-0.5F, -2.5F, -2.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		neck2.setModelRendererName("neck2");
		this.registerModelRenderer(neck2);

		head = new AnimatedModelRenderer(this);
		head.setRotationPoint(0.0F, 0.5F, -3.0F);
		neck2.addChild(head);
		head.setTextureOffset(42, 0).addBox(-2.5F, -3.5F, -6.0F, 5.0F, 5.0F, 6.0F, 0.0F, false);
		head.setTextureOffset(58, 0).addBox(0.5F, -4.5F, -3.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
		head.setTextureOffset(58, 0).addBox(-1.5F, -4.5F, -3.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
		head.setModelRendererName("head");
		this.registerModelRenderer(head);

		mouth0 = new AnimatedModelRenderer(this);
		mouth0.setRotationPoint(0.0F, 0.0F, -6.0F);
		head.addChild(mouth0);
		mouth0.setTextureOffset(52, 11).addBox(-1.5F, -0.5F, -3.0F, 3.0F, 1.0F, 3.0F, 0.0F, false);
		mouth0.setModelRendererName("mouth0");
		this.registerModelRenderer(mouth0);

		mouth1 = new AnimatedModelRenderer(this);
		mouth1.setRotationPoint(0.0F, 1.0F, -6.0F);
		head.addChild(mouth1);
		mouth1.setTextureOffset(52, 15).addBox(-1.5F, -0.5F, -3.0F, 3.0F, 1.0F, 3.0F, 0.0F, false);
		mouth1.setModelRendererName("mouth1");
		this.registerModelRenderer(mouth1);

		wing_l0 = new AnimatedModelRenderer(this);
		wing_l0.setRotationPoint(3.0F, -0.5F, -3.0F);
		body.addChild(wing_l0);
		wing_l0.setTextureOffset(0, 27).addBox(0.0F, -0.5F, -1.0F, 8.0F, 1.0F, 2.0F, 0.0F, false);
		wing_l0.setTextureOffset(0, 36).addBox(0.0F, 0.0F, 1.0F, 8.0F, 1.0F, 5.0F, 0.0F, false);
		wing_l0.setModelRendererName("wing_l0");
		this.registerModelRenderer(wing_l0);

		wing_l1 = new AnimatedModelRenderer(this);
		wing_l1.setRotationPoint(8.0F, 0.0F, 0.0F);
		wing_l0.addChild(wing_l1);
		wing_l1.setTextureOffset(20, 27).addBox(0.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, 0.0F, false);
		wing_l1.setTextureOffset(26, 37).addBox(0.0F, 0.0F, 1.0F, 7.0F, 1.0F, 4.0F, 0.0F, false);
		wing_l1.setModelRendererName("wing_l1");
		this.registerModelRenderer(wing_l1);

		wing_r0 = new AnimatedModelRenderer(this);
		wing_r0.setRotationPoint(-2.0F, -0.5F, -3.0F);
		body.addChild(wing_r0);
		wing_r0.setTextureOffset(0, 27).addBox(-8.0F, -0.5F, -1.0F, 8.0F, 1.0F, 2.0F, 0.0F, false);
		wing_r0.setTextureOffset(0, 30).addBox(-8.0F, 0.0F, 1.0F, 8.0F, 1.0F, 5.0F, 0.0F, false);
		wing_r0.setModelRendererName("wing_r0");
		this.registerModelRenderer(wing_r0);

		wing_r1 = new AnimatedModelRenderer(this);
		wing_r1.setRotationPoint(-8.0F, 0.0F, 0.0F);
		wing_r0.addChild(wing_r1);
		wing_r1.setTextureOffset(20, 27).addBox(-7.0F, -0.5F, -1.0F, 7.0F, 1.0F, 2.0F, 0.0F, false);
		wing_r1.setTextureOffset(26, 31).addBox(-7.0F, 0.0F, 1.0F, 7.0F, 1.0F, 4.0F, 0.0F, false);
		wing_r1.setModelRendererName("wing_r1");
		this.registerModelRenderer(wing_r1);

		tail0 = new AnimatedModelRenderer(this);
		tail0.setRotationPoint(0.5F, 0.5F, 6.0F);
		body.addChild(tail0);
		tail0.setTextureOffset(11, 0).addBox(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 3.0F, 0.0F, false);
		tail0.setTextureOffset(20, 0).addBox(-0.5F, -2.5F, 1.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		tail0.setModelRendererName("tail0");
		this.registerModelRenderer(tail0);

		tail1 = new AnimatedModelRenderer(this);
		tail1.setRotationPoint(0.0F, 0.0F, 3.0F);
		tail0.addChild(tail1);
		tail1.setTextureOffset(11, 0).addBox(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 3.0F, 0.0F, false);
		tail1.setTextureOffset(20, 0).addBox(-0.5F, -2.5F, 1.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		tail1.setModelRendererName("tail1");
		this.registerModelRenderer(tail1);

		tail2 = new AnimatedModelRenderer(this);
		tail2.setRotationPoint(0.0F, 0.0F, 3.0F);
		tail1.addChild(tail2);
		tail2.setTextureOffset(11, 0).addBox(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 3.0F, 0.0F, false);
		tail2.setTextureOffset(20, 0).addBox(-0.5F, -2.5F, 1.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		tail2.setModelRendererName("tail2");
		this.registerModelRenderer(tail2);

		tail3 = new AnimatedModelRenderer(this);
		tail3.setRotationPoint(0.0F, 0.0F, 3.0F);
		tail2.addChild(tail3);
		tail3.setTextureOffset(20, 0).addBox(-0.5F, -2.5F, 1.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		tail3.setTextureOffset(11, 0).addBox(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 3.0F, 0.0F, false);
		tail3.setModelRendererName("tail3");
		this.registerModelRenderer(tail3);

		tail4 = new AnimatedModelRenderer(this);
		tail4.setRotationPoint(0.0F, 0.0F, 3.0F);
		tail3.addChild(tail4);
		tail4.setTextureOffset(0, 0).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 3.0F, 0.0F, false);
		tail4.setTextureOffset(7, 1).addBox(-0.5F, -2.0F, 1.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		tail4.setModelRendererName("tail4");
		this.registerModelRenderer(tail4);

		tail5 = new AnimatedModelRenderer(this);
		tail5.setRotationPoint(0.0F, 0.0F, 3.0F);
		tail4.addChild(tail5);
		tail5.setTextureOffset(0, 0).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 3.0F, 0.0F, false);
		tail5.setTextureOffset(7, 1).addBox(-0.5F, -2.0F, 1.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		tail5.setModelRendererName("tail5");
		this.registerModelRenderer(tail5);

		arml0 = new AnimatedModelRenderer(this);
		arml0.setRotationPoint(2.0F, 3.0F, -5.0F);
		body.addChild(arml0);
		setRotationAngle(arml0, 0.7854F, 0.0F, 0.0F);
		arml0.setTextureOffset(24, 0).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 4.0F, 1.0F, 0.0F, false);
		arml0.setModelRendererName("arml0");
		this.registerModelRenderer(arml0);

		arml1 = new AnimatedModelRenderer(this);
		arml1.setRotationPoint(0.0F, 4.0F, 0.5F);
		arml0.addChild(arml1);
		setRotationAngle(arml1, -0.7854F, 0.0F, 0.0F);
		arml1.setTextureOffset(28, 0).addBox(-1.0F, -1.5F, -0.5F, 2.0F, 2.0F, 1.0F, 0.0F, false);
		arml1.setModelRendererName("arml1");
		this.registerModelRenderer(arml1);

		legr0 = new AnimatedModelRenderer(this);
		legr0.setRotationPoint(-1.0F, 3.0F, 2.0F);
		body.addChild(legr0);
		setRotationAngle(legr0, 0.7854F, 0.0F, 0.0F);
		legr0.setTextureOffset(24, 0).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 4.0F, 1.0F, 0.0F, false);
		legr0.setModelRendererName("legr0");
		this.registerModelRenderer(legr0);

		legr1 = new AnimatedModelRenderer(this);
		legr1.setRotationPoint(0.0F, 4.0F, 0.5F);
		legr0.addChild(legr1);
		setRotationAngle(legr1, -0.7854F, 0.0F, 0.0F);
		legr1.setTextureOffset(24, 5).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 3.0F, 0.0F, false);
		legr1.setModelRendererName("legr1");
		this.registerModelRenderer(legr1);

		legr2 = new AnimatedModelRenderer(this);
		legr2.setRotationPoint(0.0F, 0.0F, 2.5F);
		legr1.addChild(legr2);
		setRotationAngle(legr2, 0.3927F, 0.0F, 0.0F);
		legr2.setTextureOffset(29, 5).addBox(-0.5F, -1.0824F, -0.3869F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		legr2.setModelRendererName("legr2");
		this.registerModelRenderer(legr2);

		legl0 = new AnimatedModelRenderer(this);
		legl0.setRotationPoint(2.0F, 3.0F, 2.0F);
		body.addChild(legl0);
		setRotationAngle(legl0, 0.7854F, 0.0F, 0.0F);
		legl0.setTextureOffset(24, 0).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 4.0F, 1.0F, 0.0F, false);
		legl0.setModelRendererName("legl0");
		this.registerModelRenderer(legl0);

		legl1 = new AnimatedModelRenderer(this);
		legl1.setRotationPoint(0.0F, 4.0F, 0.5F);
		legl0.addChild(legl1);
		setRotationAngle(legl1, -0.7854F, 0.0F, 0.0F);
		legl1.setTextureOffset(24, 5).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 3.0F, 0.0F, false);
		legl1.setModelRendererName("legl1");
		this.registerModelRenderer(legl1);

		legl2 = new AnimatedModelRenderer(this);
		legl2.setRotationPoint(0.0F, 0.0F, 2.5F);
		legl1.addChild(legl2);
		setRotationAngle(legl2, 0.3927F, 0.0F, 0.0F);
		legl2.setTextureOffset(29, 5).addBox(-0.5F, -1.0824F, -0.3869F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		legl2.setModelRendererName("legl2");
		this.registerModelRenderer(legl2);

		armr0 = new AnimatedModelRenderer(this);
		armr0.setRotationPoint(-1.0F, 3.0F, -5.0F);
		body.addChild(armr0);
		setRotationAngle(armr0, 0.7854F, 0.0F, 0.0F);
		armr0.setTextureOffset(24, 0).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 4.0F, 1.0F, 0.0F, false);
		armr0.setModelRendererName("armr0");
		this.registerModelRenderer(armr0);

		armr1 = new AnimatedModelRenderer(this);
		armr1.setRotationPoint(0.0F, 4.0F, 0.5F);
		armr0.addChild(armr1);
		setRotationAngle(armr1, -0.7854F, 0.0F, 0.0F);
		armr1.setTextureOffset(28, 0).addBox(-1.0F, -1.5F, -0.5F, 2.0F, 2.0F, 1.0F, 0.0F, false);
		armr1.setModelRendererName("armr1");
		this.registerModelRenderer(armr1);

		this.rootBones.add(body);
    }


    @Override
    public ResourceLocation getAnimationFileLocation() {
        return new ResourceLocation(MOD_ID, "animations/baby_dragon.json");
    }
}