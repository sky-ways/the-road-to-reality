package com.github.cao.awa.trtr.ref.block.air;

import com.github.cao.awa.trtr.element.chemical.*;
import com.github.cao.awa.trtr.element.gas.*;
import com.github.cao.awa.trtr.ref.block.air.vanilla.*;
import com.github.cao.awa.trtr.ref.block.trtr.*;
import com.github.cao.awa.trtr.register.*;

public abstract class TrtrAirBlock extends TrtrBlockWithEntity<AirBlockEntity> implements ChemicalElemental<AirBlockEntity>, GasElemental {
    public TrtrAirBlock(Settings settings, TrtrBlockRegister register) {
        super(settings, register);
    }

    public TrtrAirBlock(Settings settings) {
        super(settings);
    }
}
