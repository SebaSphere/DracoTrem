package dev.sebastianb.dracotrem.entity;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.RangedAttackMob;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.boss.ServerBossBar;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.HorseBaseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animation.builder.AnimationBuilder;
import software.bernie.geckolib.animation.controller.EntityAnimationController;
import software.bernie.geckolib.entity.IAnimatedEntity;
import software.bernie.geckolib.event.AnimationTestEvent;
import software.bernie.geckolib.manager.EntityAnimationManager;


public class BabyDragonEntity extends HorseBaseEntity implements IAnimatedEntity {

    private final ServerBossBar bossBar;
    private final EntityAnimationManager manager;
    private final EntityAnimationController controller;

    public BabyDragonEntity(EntityType<? extends BabyDragonEntity> entityType, World world) {
        super(entityType, world);
        this.bossBar = (ServerBossBar)(new ServerBossBar(
                this.getDisplayName(),
                BossBar.Color.PURPLE,
                BossBar.Style.PROGRESS
            )
        ).setDarkenSky(false).setThickenFog(false);
        this.setHealth(this.getMaxHealth());
        this.experiencePoints = 50;

        this.manager = new EntityAnimationManager();
        this.controller = new EntityAnimationController(this, "flapController", 20, this::animationPredicate);
        this.manager.addAnimationController(controller);
    }

    @Override
    protected void initGoals() {}

    @Override
    public boolean isTame() {
        return true;
    }

    @Override
    public boolean eatsGrass() {
        return false;
    }

    @Override
    public boolean isSaddled() {
        return true;
    }

    @Override
    public void travel(Vec3d movementInput) {
        if (this.isAlive()) {
            if (this.hasPassengers() && this.canBeControlledByRider()) {
                this.setVelocity(Vec3d.ZERO);
                LivingEntity livingEntity = (LivingEntity)this.getPrimaryPassenger();
                this.yaw = livingEntity.yaw;
                this.prevYaw = this.yaw;
                this.pitch = livingEntity.pitch * 0.5F;
                this.setRotation(this.yaw, this.pitch);
                this.bodyYaw = this.yaw;
                this.headYaw = this.bodyYaw;
                float f = livingEntity.sidewaysSpeed * 0.5F;
                float g = livingEntity.forwardSpeed;
                if (g <= 0.0F) {
                    g *= 0.25F;
                }
                Vec3d vec3d = this.getVelocity();
                this.setVelocity(vec3d.x, vec3d.y, vec3d.z);
                this.setInAir(true);
                this.velocityDirty = true;
                if (g > 0.0F) {
                    float i = MathHelper.sin(this.yaw * 0.017453292F);
                    float j = MathHelper.cos(this.yaw * 0.017453292F);
                    this.setVelocity(this.getVelocity().add((-0.4F * i), 0.0D, (0.4F * j)));
                }
                this.flyingSpeed = this.getMovementSpeed() * 0.1F;
                if (this.isLogicalSideForUpdatingMovement()) {
                    this.setMovementSpeed((float)this.getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED));
                    super.travel(new Vec3d(f, movementInput.y, g));
                } else if (livingEntity instanceof PlayerEntity) {
                    this.setVelocity(Vec3d.ZERO);
                }
                if (this.onGround) {
                    this.setInAir(false);
                }
                this.method_29242(this, false);
            } else {
                this.flyingSpeed = 1.0F;
                super.travel(movementInput);
            }
        }
    }

    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);

        if (this.isTame() && player.shouldCancelInteraction()) {
            this.openInventory(player);
            return ActionResult.success(this.world.isClient);
        }
        if (this.hasPassengers()) {
            return super.interactMob(player, hand);
        }
        if (!itemStack.isEmpty()) {
            ActionResult actionResult = itemStack.useOnEntity(player, this, hand);
            if (actionResult.isAccepted()) {
                return actionResult;
            }
            boolean bl = itemStack.getItem() == Items.SADDLE;
            if (this.isHorseArmor(itemStack) || bl) {
                this.openInventory(player);
                return ActionResult.success(this.world.isClient);
            }
        }
        this.putPlayerOnBack(player);
        return ActionResult.success(this.world.isClient);
    }

    @Override
    protected void playWalkSound(BlockSoundGroup group) {}

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {}

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
}
