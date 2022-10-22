package com.github.cao.awa.trtr.element.gas;

import com.github.cao.awa.trtr.database.properties.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;

public interface Gas<T> {
    void generatePressure(World world, BlockPos pos, InstanceProperties properties);
}
