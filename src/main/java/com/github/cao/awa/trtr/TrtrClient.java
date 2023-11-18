package com.github.cao.awa.trtr;

import com.github.cao.awa.modmdo.annotation.platform.Client;
import com.github.cao.awa.trtr.constant.trtr.TrtrConstants;
import net.fabricmc.api.ClientModInitializer;

@Client
public class TrtrClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        TrtrConstants.isServer = false;

        TrtrMod.BLOCK_FRAMEWORK.renderers();
    }
}
