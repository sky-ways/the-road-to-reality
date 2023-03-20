package com.github.cao.awa.trtr.block.stove.mud;

import com.github.cao.awa.apricot.anntation.Auto;
import com.github.cao.awa.trtr.annotation.serializer.AutoNbt;
import com.github.cao.awa.trtr.block.entity.TrtrBlockEntity;
import com.github.cao.awa.trtr.block.stove.mud.fuel.MudStoveFuelLayer;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

@Auto
public class MudStoveBlockEntity extends TrtrBlockEntity {
    @AutoNbt("layer")
    private MudStoveFuelLayer layer;

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

    public boolean addFuel(ItemStack stack) {
        if (stack.getItem() == Items.COAL && this.layer.add()) {
            stack.decrement(1);
            return true;
        }
        return false;
    }

    public double getFuel() {
        return this.layer.get() / 45D;
    }
}
