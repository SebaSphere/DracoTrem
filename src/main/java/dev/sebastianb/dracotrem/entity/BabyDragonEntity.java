package dev.sebastianb.dracotrem.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.RangedAttackMob;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.boss.ServerBossBar;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animation.builder.AnimationBuilder;
import software.bernie.geckolib.animation.controller.EntityAnimationController;
import software.bernie.geckolib.entity.IAnimatedEntity;
import software.bernie.geckolib.event.AnimationTestEvent;
import software.bernie.geckolib.manager.EntityAnimationManager;


public class BabyDragonEntity extends HostileEntity implements RangedAttackMob, IAnimatedEntity {

    private final ServerBossBar bossBar;
    private final EntityAnimationManager manager;
    private final EntityAnimationController controller;

    public BabyDragonEntity(EntityType<? extends BabyDragonEntity> entityType, World world) {
        super(entityType, world);
        this.bossBar = (ServerBossBar)(new ServerBossBar(this.getDisplayName(), BossBar.Color.PURPLE, BossBar.Style.PROGRESS))
                .setDarkenSky(true)
                .setThickenFog(true);
        this.setHealth(this.getMaxHealth());
        this.experiencePoints = 50;

        this.manager = new EntityAnimationManager();
        this.controller = new EntityAnimationController(this, "flapController", 20, this::animationPredicate);
        this.manager.addAnimationController(controller);
    }

    public static DefaultAttributeContainer.Builder createBabyDragonAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 250.0D)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.5D)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 48.0D);
    }

    private <E extends BabyDragonEntity> boolean animationPredicate(AnimationTestEvent<E> event) {
        this.controller.setAnimation(new AnimationBuilder().addAnimation("flap", true));
        return true;
    }

    @Override
    public EntityAnimationManager getAnimationManager() {
        return this.manager;
    }

    @Override
    public boolean isPersistent() {
        return true;
    }

    @Override
    protected void mobTick() {
        super.mobTick();
        this.bossBar.setPercent(this.getHealth() / this.getMaxHealth());

        Vec3d vec3d = this.getVelocity();
        Vec3d vec3d2 = vec3d.add((0.5D - vec3d.x) * 0.1D, (0.699999988079071D - vec3d.y) * 0.1D, (0.5D - vec3d.z) * 0.1D);
        this.setVelocity(vec3d2);
        float g = (float)(MathHelper.atan2(vec3d2.z, vec3d2.x) * 57.2957763671875D) - 90.0F;
        float h = MathHelper.wrapDegrees(g - this.yaw);
        this.forwardSpeed = 0.5F;
        this.yaw += h;
    }

    @Override
    public void onStartedTrackingBy(ServerPlayerEntity player) {
        super.onStartedTrackingBy(player);
        this.bossBar.addPlayer(player);
    }

    @Override
    public void onStoppedTrackingBy(ServerPlayerEntity player) {
        super.onStoppedTrackingBy(player);
        this.bossBar.removePlayer(player);
    }

    @Override
    public void setCustomName(@Nullable Text name) {
        super.setCustomName(name);
        this.bossBar.setName(this.getDisplayName());
    }

    @Override
    public @Nullable SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_ENDER_DRAGON_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_ENDER_DRAGON_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_ENDER_DRAGON_DEATH;
    }

    @Override
    protected float getSoundPitch() {
        return super.getSoundPitch() + 1.5F;
    }

    @Override
    public void attack(LivingEntity target, float pullProgress) {
        // TODO: attack
    }
}
