package com.github.cao.awa.trtr;

import com.github.cao.awa.modmdo.annotations.platform.*;
import com.github.cao.awa.trtr.air.manager.*;
import com.github.cao.awa.trtr.config.*;
import com.github.cao.awa.trtr.database.*;
import com.github.cao.awa.trtr.debuger.performance.tracker.*;
import com.github.cao.awa.trtr.element.chemical.*;
import com.github.cao.awa.trtr.factor.*;
import com.github.cao.awa.trtr.heat.handler.*;
import com.github.cao.awa.trtr.loader.resource.*;
import com.github.cao.awa.trtr.ref.item.fire.*;
import com.github.cao.awa.trtr.type.*;
import com.github.cao.awa.trtr.util.io.*;
import com.github.zhuaidadaya.rikaishinikui.handler.universal.entrust.*;
import com.github.zhuaidadaya.rikaishinikui.handler.universal.receptacle.*;
import com.github.zhuaidadaya.rikaishinikui.handler.universal.runnable.*;
import net.fabricmc.api.*;
import org.apache.logging.log4j.*;

import java.io.*;

@Client
@Server
public class TrtrMod implements ModInitializer {
    public static final String VERSION = "1.0.0";
    public static final Logger LOGGER = LogManager.getLogger("Trtr");
    public static HeatManager heatManager = new HeatManager();
    public static AirManager airManager = new AirManager();
    public static SubmitTimeTracker timeTracker = new SubmitTimeTracker();
    public static FutureTaskOrder delayTasks = new FutureTaskOrder();
    public static Configure configs = new Configure(SupplierTemplates.emptyString());

    public static InstancePropertiesDatabase propertiesDatabase = null;

    @Override
    public void onInitialize() {
        initConfig();
        TrtrItems.pre();
        TrtrBlocks.pre();
        TrtrBlockEntityType.pre();
        TrtrScreenHandlerType.pre();
        TrtrPlacedFeatures.pre();
        TrtrHammerableProducts.pre();
        TrtrItemGroup.pre();
        TrtrEntityType.pre();
        CombinationReactions.pre();
        FireReacts.pre();
    }

    public static void initConfig() {
        configs = new Configure(() -> {
            File config = new File("config/the-road-to-reality/config.conf");
            final Receptacle<String> configInformation = new Receptacle<>(null);
            EntrustExecution.tryTemporary(() -> {
                if (! config.isFile()) {
                    TrtrMod.LOGGER.info("Config not found, generating default config");

                    config.getParentFile()
                          .mkdirs();
                    configInformation.set(IOUtil.read(Resources.getResource(
                            "default-config.conf"
                    )));
                    EntrustExecution.tryTemporary(() -> IOUtil.write(
                            new BufferedWriter(new FileWriter(config)),
                            configInformation.get()
                    ));
                }

                if (configInformation.get() == null) {
                    configInformation.set(IOUtil.read(new BufferedReader(new FileReader(config))));
                }
            });
            return configInformation.get();
        });

        configs.load();
    }
}
