package com.github.cao.awa.trtr.pebble;

import com.github.cao.awa.trtr.database.properties.*;
import com.github.cao.awa.trtr.type.*;
import net.minecraft.block.*;
import net.minecraft.block.entity.*;
import net.minecraft.nbt.*;
import net.minecraft.util.math.*;

// todo delete
public class PebbleBlockEntity extends BlockEntity {
    private final InstanceProperties properties = new InstanceProperties();

    public PebbleBlockEntity(BlockPos pos, BlockState state) {
        super(TrtrBlockEntityType.PEBBLE, pos, state);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        this.properties.readNbt(nbt);
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        this.properties.writeNbt(nbt);
    }

}
