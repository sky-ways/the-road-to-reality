package com.github.cao.awa.trtr.ref.block;

import com.github.cao.awa.trtr.heat.conductor.*;
import net.minecraft.block.*;
import net.minecraft.block.entity.*;
import net.minecraft.nbt.*;
import net.minecraft.network.*;
import net.minecraft.network.listener.*;
import net.minecraft.network.packet.s2c.play.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;
import org.jetbrains.annotations.*;

public abstract class HeatConductionBlockEntity<T extends HeatConductionBlockEntity<T>> extends BlockEntity implements BlockEntityTicker<T>, HeatConductive {
    protected final MetalBlockHeatConductor conductor;

    public MetalBlockHeatConductor getConductor() {
        return conductor;
    }

    public HeatConductionBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        this.conductor = new MetalBlockHeatConductor(0);
    }

    public void tick(World world, BlockPos pos, BlockState state, T blockEntity) {
        conductor.endothermic(world, pos, state);
        markDirty();
    }

    public void writeNbt(NbtCompound nbt) {
        conductor.writeNbt(nbt);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        conductor.readNbt(nbt);
    }

    public abstract int thermalConductivity();

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return createNbt();
    }
}
