package com.github.cao.awa.trtr.cooking.container.pot;

import com.github.cao.awa.trtr.properties.*;
import com.github.cao.awa.trtr.type.*;
import net.minecraft.block.*;
import net.minecraft.block.entity.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import net.minecraft.nbt.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;

public class PotBlockEntity extends BlockEntity {
    private final InstanceProperties<PotBlockEntity> properties = new InstanceProperties<>(this);

    public PotBlockEntity(BlockPos pos, BlockState state) {
        super(TrtrBlockEntityType.POT, pos, state);
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        properties.writeNbt(nbt);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        properties.readNbt(nbt);
    }

    public void tick(World world, BlockPos pos, BlockState state) {

    }

    public void stack(World world, BlockPos pos, PlayerEntity player, ItemStack stack) {
        properties.stack("items", stack);
    }

    public ItemStack pop(World world, BlockPos pos, PlayerEntity player) {
        return properties.pop("items");
    }
}
