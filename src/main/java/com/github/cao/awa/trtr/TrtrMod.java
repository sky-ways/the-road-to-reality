package com.github.cao.awa.trtr;

import com.github.cao.awa.modmdo.annotations.platform.Client;
import com.github.cao.awa.modmdo.annotations.platform.Server;
import com.github.cao.awa.trtr.annotations.repo.MineableAnnotations;
import com.github.cao.awa.trtr.framework.block.BlockFramework;
import net.fabricmc.api.ModInitializer;

@Client
@Server
public class TrtrMod implements ModInitializer {
    public static final BlockFramework BLOCK_FRAMEWORK = new BlockFramework();

    @Override
    public void onInitialize() {
        initializeConfig();

        MineableAnnotations.putDefaults();

        BLOCK_FRAMEWORK.work();
    }

    public static void initializeConfig() {
    }
}
