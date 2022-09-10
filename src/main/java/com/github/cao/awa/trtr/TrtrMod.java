package com.github.cao.awa.trtr;

import com.github.cao.awa.trtr.type.*;
import net.fabricmc.api.*;

public class TrtrMod implements ModInitializer {
    @Override
    public void onInitialize() {
        TrtrItems.pre();
        TrtrBlocks.pre();
        TrtrBlockEntityType.pre();
        TrtrScreenHandlerType.pre();
    }
}
