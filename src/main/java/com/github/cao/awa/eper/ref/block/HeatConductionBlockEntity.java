package com.github.cao.awa.eper.ref.block;

import com.github.cao.awa.eper.heat.*;
import com.github.cao.awa.eper.ref.block.iron.*;
import net.minecraft.block.*;
import net.minecraft.block.entity.*;
import net.minecraft.nbt.*;
import net.minecraft.network.*;
import net.minecraft.network.listener.*;
import net.minecraft.network.packet.s2c.play.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;
import org.jetbrains.annotations.*;

public abstract class HeatConductionBlockEntity<T extends HeatConductionBlockEntity<T>> extends BlockEntity implements BlockEntityTicker<T> {
    private final HeatConductor heatConductor;

    public HeatConductor getHeatConductor() {
        return heatConductor;
    }

    public HeatConductionBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state, int thermalConductivity) {
        super(type, pos, state);
        this.heatConductor = new HeatConductor(thermalConductivity);
    }

    public void tick(World world, BlockPos pos, BlockState state, T blockEntity) {
        heatConductor.heat(world, pos, state, HeatConductionType.CALCULATE_MAX);
    }

    public void writeNbt(NbtCompound nbt) {
        nbt.putFloat("selfHeat", heatConductor.getSelfHeat());
        nbt.putInt("ticked", heatConductor.getTicked());
        nbt.putInt("conductionTick", heatConductor.getConductionTick());
        nbt.putInt("thermalConductivity", heatConductor.getThermalConductivity());
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
