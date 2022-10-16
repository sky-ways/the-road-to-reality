package com.github.cao.awa.trtr.ref.block.air.vanilla;

import com.github.cao.awa.trtr.database.properties.*;
import com.github.cao.awa.trtr.element.generator.*;
import com.github.cao.awa.trtr.ref.block.air.*;
import com.github.cao.awa.trtr.ref.block.trtr.*;
import com.github.cao.awa.trtr.type.*;
import net.minecraft.block.*;
import net.minecraft.block.entity.*;
import net.minecraft.nbt.*;
import net.minecraft.network.*;
import net.minecraft.network.listener.*;
import net.minecraft.network.packet.s2c.play.*;
import net.minecraft.util.math.*;
import org.jetbrains.annotations.*;
import org.json.*;

public class AirBlockEntity extends BlockEntity implements ChemicalElementGenerator, PropertiesAccessible<AirBlockEntity> {
    private final InstanceProperties<AirBlockEntity> properties = new InstanceProperties<>(this);
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
//        ChunkPos chunkPos = new ChunkPos(pos);
//        properties.put("p", chunkPos.x + "." + chunkPos.z);
        properties.createAccess(nbt);
    }

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return null;
    }

    @Override
    public void generateElements() {
        if (air == null) {
            return;
        }
        air.generateElements(world, pos, properties);
    }

    @Override
    public InstanceProperties<AirBlockEntity> properties() {
        return properties;
    }
}
