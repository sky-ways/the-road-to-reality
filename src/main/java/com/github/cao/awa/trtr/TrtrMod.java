package com.github.cao.awa.trtr;

import com.github.cao.awa.modmdo.annotation.platform.Client;
import com.github.cao.awa.modmdo.annotation.platform.Server;
import com.github.cao.awa.trtr.annotation.mine.repo.MineableAnnotations;
import com.github.cao.awa.trtr.command.GetAllTrtrBlockCommand;
import com.github.cao.awa.trtr.framework.block.BlockFramework;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;

@Client
@Server
public class TrtrMod implements ModInitializer {
    public static final BlockFramework BLOCK_FRAMEWORK = new BlockFramework();

    @Override
    public void onInitialize() {
        initializeConfig();

        // Register mineable annotations for data generator.
        MineableAnnotations.putDefaults();

        // Startup block framework.
        BLOCK_FRAMEWORK.work();

        ServerLifecycleEvents.SERVER_STARTING.register(l -> {
            GetAllTrtrBlockCommand.register(l);
        });
    }

    public static void initializeConfig() {
    }
}
