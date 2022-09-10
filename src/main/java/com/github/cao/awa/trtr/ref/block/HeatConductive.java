package com.github.cao.awa.trtr.ref.block;

import com.github.cao.awa.trtr.heat.conductor.*;
import net.minecraft.block.*;

public interface HeatConductive {
    HeatConductor getConductor();
    int thermalConductivity();
}
