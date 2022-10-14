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

import static com.github.cao.awa.trtr.TrtrMod.heatManager;

public abstract class HeatConductionBlockEntity<T extends HeatConductionBlockEntity<T>> extends BlockEntity implements HeatConductiveBlockEntity {
    public NbtCompound nbtOpt = null;

    public HeatConductionBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public MetalBlockHeatConductor getConductor() {
        return heatManager.getConductor(world, pos) instanceof MetalBlockHeatConductor conductor ? conductor : null;
    }

    public abstract int thermalConductivity();

    public void tick(World world, BlockPos pos, BlockState state, T blockEntity) {
        prepare(world, pos);
        if (nbtOpt == null) {
            return;
        }
        heatManager.prepare(world, pos, () -> {
            HeatConductor conductor = heatManager.getConductor(world, pos);
            if (conductor == null) {
                return;
            }
            conductor.readNbt(nbtOpt);
            nbtOpt = null;
        });
        markDirty();
//        if (heatHandler.isUnloaded(world)) {
//            return;
//        }
//        world.removeBlockEntity(pos);
    }

    public void prepare(World world, BlockPos pos) {
        heatManager.getOrReplace(world, pos, () -> new MetalBlockHeatConductor(this));
        heatManager.requireTick(world, pos);
    }

    @Override
    public void init(World world) {
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        nbtOpt = nbt;
    }

    @Override
    public void writeNbt(NbtCompound nbt) {
        HeatConductor conductor = heatManager.getConductor(world, pos);
        if (conductor != null) {
            conductor.writeNbt(nbt);
        }
    }

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return createNbt();
    }

    @Override
    public boolean participateUnload() {
        return true;
    }
}
