package com.github.cao.awa.trtr.ref.block.trtr.slab;

import com.github.cao.awa.trtr.ref.*;
import net.minecraft.block.*;
import net.minecraft.block.entity.*;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.*;
import net.minecraft.entity.player.*;
import net.minecraft.inventory.*;
import net.minecraft.item.*;
import net.minecraft.nbt.*;
import net.minecraft.network.*;
import net.minecraft.network.listener.*;
import net.minecraft.network.packet.s2c.play.*;
import net.minecraft.util.*;
import net.minecraft.util.collection.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;
import org.jetbrains.annotations.*;

public abstract class TrtrSlabBlockEntity extends BlockEntity implements BlockRenderer<TrtrConventionalSlabEntity> {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(1, ItemStack.EMPTY);
    private long cooling = 0;

    public TrtrSlabBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, inventory);
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
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

    public void setItemStack(ItemStack stack) {
        inventory.set(0, stack);
    }

    public ItemStack getItem() {
        return inventory.get(0);
    }

    public abstract void thump(World world, BlockPos pos, BlockState state, ItemStack tool, PlayerEntity player);

    public void take(World world, BlockPos pos, BlockState state, PlayerEntity entity) {

    }

    public long getCooling() {
        return cooling;
    }

    public void setCooling(long cooling) {
        this.cooling = cooling;
    }

    @Override
    public void render(BlockEntity blockEntity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {

    }

    @Override
    public void instanceRender(TrtrConventionalSlabEntity blockEntity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        BlockRenderer.super.instanceRender(blockEntity, tickDelta, matrices, vertexConsumers, light, overlay);
    }
}
