package com.github.cao.awa.trtr;

import com.github.cao.awa.modmdo.annotation.platform.Client;
import com.github.cao.awa.modmdo.annotation.platform.Server;
import com.github.cao.awa.trtr.annotation.mine.repo.MineableAnnotations;
import com.github.cao.awa.trtr.command.GetAllTrtrBlockCommand;
import com.github.cao.awa.trtr.framework.block.BlockFramework;
import com.github.cao.awa.trtr.framework.item.ItemFramework;
import com.github.cao.awa.trtr.framework.model.provider.ModelFramework;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;

@Client
@Server
public class TrtrMod implements ModInitializer {
    public static final BlockFramework BLOCK_FRAMEWORK = new BlockFramework();
    public static final ItemFramework ITEM_FRAMEWORK = new ItemFramework();
    public static final ModelFramework MODEL_FRAMEWORK = new ModelFramework();
    public static final boolean DEV_MODE = false;

    @Override
    public void onInitialize() {
        initializeConfig();

        // Register mineable annotations for data generator.
        MineableAnnotations.register();

        // Startup frameworks.
        BLOCK_FRAMEWORK.work();
        ITEM_FRAMEWORK.work();

        ServerLifecycleEvents.SERVER_STARTING.register(GetAllTrtrBlockCommand :: register);
    }

    public static void initializeConfig() {
    }
}
