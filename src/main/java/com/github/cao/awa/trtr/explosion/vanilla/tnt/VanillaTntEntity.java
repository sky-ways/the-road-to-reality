package com.github.cao.awa.trtr.explosion.vanilla.tnt;

import net.minecraft.entity.*;
import net.minecraft.entity.data.*;
import net.minecraft.nbt.*;
import net.minecraft.network.*;
import net.minecraft.network.packet.s2c.play.*;
import net.minecraft.particle.*;
import net.minecraft.world.*;
import net.minecraft.world.explosion.*;
import org.jetbrains.annotations.*;

import static com.github.cao.awa.trtr.TrtrMod.delayTasks;

public class VanillaTntEntity extends Entity {
    private static final int POWER = 8;
    private static final TrackedData<Integer> FUSE;
    private static final int DEFAULT_FUSE = 0;

    static {
        FUSE = DataTracker.registerData(
                TntEntity.class,
                TrackedDataHandlerRegistry.INTEGER
        );
    }

    @Nullable
    private LivingEntity causingEntity;

    public VanillaTntEntity(World world, double x, double y, double z, @Nullable LivingEntity igniter) {
        this(
                EntityType.TNT,
                world
        );
        this.setPosition(
                x,
                y,
                z
        );
        double d = world.random.nextDouble() * 6.2831854820251465D;
        this.setVelocity(
                - Math.sin(d) * 0.02D,
                0.20000000298023224D,
                - Math.cos(d) * 0.02D
        );
        this.setFuse(DEFAULT_FUSE);
        this.prevX = x;
        this.prevY = y;
        this.prevZ = z;
        this.causingEntity = igniter;
    }

    public VanillaTntEntity(EntityType<? extends TntEntity> entityType, World world) {
        super(
                entityType,
                world
        );
        this.intersectionChecked = true;
    }

    protected void initDataTracker() {
        this.dataTracker.startTracking(
                FUSE,
                DEFAULT_FUSE
        );
    }

    public void tick() {
        if (! this.hasNoGravity()) {
            this.setVelocity(this.getVelocity()
                                 .add(0.0D,
                                      - 0.04D,
                                      0.0D
                                 ));
        }

        this.move(
                MovementType.SELF,
                this.getVelocity()
        );
        this.setVelocity(this.getVelocity()
                             .multiply(0.98D));
        if (this.onGround) {
            this.setVelocity(this.getVelocity()
                                 .multiply(0.7D,
                                           - 0.5D,
                                           0.7D
                                 ));
        }

        int i = this.getFuse() - 1;
        this.setFuse(i);
        if (i <= 0) {
            this.discard();
            if (! this.world.isClient) {
                this.explode();
            }
        } else {
            this.updateWaterState();
            if (this.world.isClient) {
                this.world.addParticle(
                        ParticleTypes.SMOKE,
                        this.getX(),
                        this.getY() + 0.5D,
                        this.getZ(),
                        0.0D,
                        0.0D,
                        0.0D
                );
            }
        }

    }

    protected MoveEffect getMoveEffect() {
        return MoveEffect.NONE;
    }

    protected void readCustomDataFromNbt(NbtCompound nbt) {
    }

    protected void writeCustomDataToNbt(NbtCompound nbt) {
    }

    protected float getEyeHeight(EntityPose pose, EntityDimensions dimensions) {
        return 0.15F;
    }

    public Packet<?> createSpawnPacket() {
        return new EntitySpawnS2CPacket(this);
    }

    private void explode() {
        delayTasks.submit(
                () -> this.world.createExplosion(
                        this,
                        this.getX(),
                        this.getBodyY(0.0625D),
                        this.getZ(),
                        POWER,
                        Explosion.DestructionType.BREAK
                ),
                1
        );
    }

    public int getFuse() {
        return this.dataTracker.get(FUSE);
    }

    public void setFuse(int fuse) {
        this.dataTracker.set(
                FUSE,
                fuse
        );
    }

    @Nullable
    public LivingEntity getCausingEntity() {
        return this.causingEntity;
    }
}
