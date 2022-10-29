package com.github.cao.awa.trtr.mud.blower;

import com.github.cao.awa.trtr.type.*;
import net.minecraft.block.*;
import net.minecraft.block.entity.*;
import net.minecraft.util.math.*;

public class MudBlowerBlockEntity extends BlockEntity {
    public MudBlowerBlockEntity(BlockPos pos, BlockState state) {
        super(TrtrBlockEntityType.MUD_BLOWER, pos, state);
    }
}
