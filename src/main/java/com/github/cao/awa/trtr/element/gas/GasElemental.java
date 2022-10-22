package com.github.cao.awa.trtr.element.gas;

import com.github.cao.awa.trtr.database.properties.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;

public interface GasElemental {
    void generatePressures(World world, BlockPos pos, InstanceProperties properties);
}
