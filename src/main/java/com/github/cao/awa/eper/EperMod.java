package com.github.cao.awa.eper;

import com.github.cao.awa.eper.type.*;
import net.fabricmc.api.*;

public class EperMod implements ModInitializer {
    @Override
    public void onInitialize() {
        EperItems.pre();
        EperBlocks.pre();
        EperBlockEntityType.pre();
        EperScreenHandlerType.pre();
    }
}
