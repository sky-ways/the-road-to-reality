package com.github.cao.awa.trtr.ref.block;

import com.github.cao.awa.trtr.heat.conductor.*;
import net.minecraft.world.*;

public interface HeatConductive {
    HeatConductor getConductor();
    void setConductor(HeatConductor conductor);
    int thermalConductivity();
}
