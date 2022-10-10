package com.github.cao.awa.trtr.mud.stove;

import com.github.cao.awa.trtr.type.*;
import net.minecraft.block.*;
import net.minecraft.block.entity.*;
import net.minecraft.util.math.*;

public class MudStoveBlockEntity extends BlockEntity {
    public MudStoveBlockEntity(BlockPos pos, BlockState state) {
        super(TrtrBlockEntityType.MUD_STOVE, pos, state);
    }
}
