package com.github.cao.awa.trtr.ref.block.trtr;

import com.github.cao.awa.trtr.database.properties.*;
import com.github.cao.awa.trtr.element.generator.*;
import com.github.cao.awa.trtr.type.*;
import net.minecraft.block.*;
import net.minecraft.block.entity.*;
import net.minecraft.nbt.*;
import net.minecraft.network.*;
import net.minecraft.network.listener.*;
import net.minecraft.network.packet.s2c.play.*;
import net.minecraft.util.math.*;
import org.jetbrains.annotations.*;

public class TrtrOreBlockEntity extends BlockEntity implements ChemicalElementGenerator {
    private final InstanceProperties<TrtrOreBlockEntity> properties = new InstanceProperties<>(this);
    private final TrtrOreBlock ore;

    public TrtrOreBlockEntity(BlockPos pos, BlockState state, TrtrOreBlock ore) {
        super(TrtrBlockEntityType.ORE, pos, state);
        this.ore = ore;
    }

    public TrtrOreBlockEntity(BlockPos pos, BlockState state) {
        super(TrtrBlockEntityType.ORE, pos, state);
        ore = null;
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        properties.readNbt(nbt);
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        properties.writeNbt(nbt);
    }

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public void generateElements() {
        if (ore == null) {
            return;
        }
        ore.generateElements(world, pos, properties);
    }
}
