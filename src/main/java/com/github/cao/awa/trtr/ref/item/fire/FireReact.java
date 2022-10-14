package com.github.cao.awa.trtr.ref.item.fire;

import net.minecraft.util.math.*;
import net.minecraft.world.*;

public interface FireReact {
    void react(World world, BlockPos pos, double strength);
}
