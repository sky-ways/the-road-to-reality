package com.github.cao.awa.trtr.transmission.gearwheel.test;

import com.github.cao.awa.trtr.transmission.gearwheel.*;
import net.minecraft.entity.*;
import net.minecraft.entity.mob.*;
import net.minecraft.nbt.*;
import net.minecraft.network.*;
import net.minecraft.network.packet.s2c.play.*;
import net.minecraft.world.*;

// todo
public class GearWheelEntity extends Entity {
    public GearWheelEntity(EntityType<GearWheelEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initDataTracker() {

    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {

    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {

    }

    @Override
    public Packet<?> createSpawnPacket() {
        return new EntitySpawnS2CPacket(this);
    }

    @Override
    public void tick() {
    }



//    @Override
//    public void updatePositionAndAngles(double x, double y, double z, float yaw, float pitch) {
//        super.updatePositionAndAngles(x, y, z, yaw, pitch);
//    }
}
