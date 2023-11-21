package com.github.cao.awa.trtr;

import com.github.cao.awa.modmdo.annotation.platform.Server;
import com.github.cao.awa.trtr.constant.trtr.TrtrConstants;
import net.fabricmc.api.DedicatedServerModInitializer;

@Server
public class TrtrServer implements DedicatedServerModInitializer {
    @Override
    public void onInitializeServer() {
        TrtrConstants.isServer = true;
    }
}
