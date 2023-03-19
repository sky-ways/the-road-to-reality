package com.github.cao.awa.trtr.block.stove.mud;

import com.github.cao.awa.apricot.anntation.Auto;
import com.github.cao.awa.trtr.block.entity.TrtrBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

@Auto
public class MudStoveBlockEntity extends TrtrBlockEntity {
    private ItemStack stack = new ItemStack(Items.AIR);

    public ItemStack getFuel() {
        return this.stack;
    }

    public MudStoveBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type,
              pos,
              state
        );
    }

    @Auto
    public static void tick(World world, BlockPos pos, BlockState state, MudStoveBlockEntity blockEntity) {
        // Tick details...
    }

    public void addFuel(ItemStack stack) {
        if (this.stack == null || this.getFuel()
                                      .getItem() != stack.getItem()) {
            this.stack = stack.copy();
        }

        stack.decrement(1);
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

    @Override
    protected void writeNbt(NbtCompound nbt) {
        this.stack.writeNbt(nbt);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        this.stack = ItemStack.fromNbt(nbt);
    }
}
