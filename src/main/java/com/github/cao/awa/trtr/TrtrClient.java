package com.github.cao.awa.trtr;

import com.github.cao.awa.trtr.type.*;
import net.fabricmc.api.*;

public class TrtrClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        TrtrScreenType.pre();
    }
}
