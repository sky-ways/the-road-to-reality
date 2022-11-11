package com.github.cao.awa.trtr.ref.block.air.vanilla;

import com.github.cao.awa.trtr.database.properties.*;
import com.github.cao.awa.trtr.element.generator.*;
import com.github.cao.awa.trtr.ref.block.air.*;
import com.github.cao.awa.trtr.type.*;
import com.github.zhuaidadaya.rikaishinikui.handler.universal.entrust.*;
import net.minecraft.block.*;
import net.minecraft.block.entity.*;
import net.minecraft.nbt.*;
import net.minecraft.network.*;
import net.minecraft.network.listener.*;
import net.minecraft.util.math.*;
import org.jetbrains.annotations.*;

public class AirBlockEntity extends BlockEntity implements ChemicalElementGenerator, GasElementGenerator, PropertiesAccessible {
    private final InstanceProperties properties = new InstanceProperties();
    private final TrtrAirBlock air;

    public AirBlockEntity(BlockPos pos, BlockState state, TrtrAirBlock air) {
        super(TrtrBlockEntityType.AIR, pos, state);
        this.air = air;
    }

    public AirBlockEntity(BlockPos pos, BlockState state) {
        super(TrtrBlockEntityType.AIR, pos, state);
        air = null;
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        if (nbt.contains("acs")) {
            properties.access(nbt.getString("acs"));
        }
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        properties.createAccess(nbt);
    }

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return null;
    }

    @Override
    public void generateElement() {
        EntrustEnvironment.notNull(air, airBlock -> {
            airBlock.generateElements(world, pos, properties);
        });
    }

    @Override
    public InstanceProperties getProperties() {
        return properties;
    }

    @Override
    public void setProperties(InstanceProperties properties) {
        this.properties.readProperties(properties);
    }

    @Override
    public void generatePressures() {
        EntrustEnvironment.notNull(air, airBlock -> {
            airBlock.generatePressures(world, pos, properties);
        });
    }
}
