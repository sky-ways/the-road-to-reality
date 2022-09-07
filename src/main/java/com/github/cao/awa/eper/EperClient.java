package com.github.cao.awa.eper;

import com.github.cao.awa.eper.type.*;
import net.fabricmc.api.*;

public class EperClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EperScreenType.pre();
    }
}
