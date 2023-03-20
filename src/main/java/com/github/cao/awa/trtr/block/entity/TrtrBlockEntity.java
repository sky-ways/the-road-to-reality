package com.github.cao.awa.trtr.block.entity;

import com.github.cao.awa.apricot.anntation.Auto;
import com.github.cao.awa.trtr.TrtrMod;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nullable;

@Auto
public abstract class TrtrBlockEntity extends BlockEntity {
    public TrtrBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type,
              pos,
              state
        );
        TrtrMod.BLOCK_FRAMEWORK.initEntity(this);
    }

    @Auto
    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Auto
    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return createNbt();
    }

    @Auto
    @Override
    protected void writeNbt(NbtCompound nbt) {
        TrtrMod.BLOCK_FRAMEWORK.writeNbt(this,
                                         nbt
        );
    }

    @Auto
    @Override
    public void readNbt(NbtCompound nbt) {
        TrtrMod.BLOCK_FRAMEWORK.readNbt(this,
                                        nbt
        );
    }
}
