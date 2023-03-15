package com.github.cao.awa.trtr;

import com.github.cao.awa.modmdo.annotations.platform.*;
import com.github.cao.awa.trtr.framework.block.BlockFramework;
import net.fabricmc.api.*;

@Client
@Server
public class TrtrMod implements ModInitializer {
    public static final BlockFramework BLOCK_FRAMEWORK = new BlockFramework();

    @Override
    public void onInitialize() {
        initializeConfig();

        BLOCK_FRAMEWORK.load();
    }

    public static void initializeConfig() {
    }
}
