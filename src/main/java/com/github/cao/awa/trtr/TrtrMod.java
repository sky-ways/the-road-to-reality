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
    public static final FutureTaskOrder delayTasks = new FutureTaskOrder();
    public static final Configure configs = new Configure(SupplierTemplates.emptyString());
    public static HeatManager heatManager = new HeatManager();
    public static AirManager airManager = new AirManager();
    public static SubmitTimeTracker timeTracker = new SubmitTimeTracker();
    public static InstancePropertiesDatabase propertiesDatabase = null;

    @Override
    public void onInitialize() {
        initializeConfig();
        TrtrItems.initialize();
        TrtrBlocks.initialize();
        TrtrBlockEntityType.initialize();
        TrtrScreenHandlerType.initialize();
        TrtrPlacedFeatures.initialize();
        TrtrHammerableProducts.initialize();
        TrtrItemGroup.initialize();
        TrtrEntityType.initialize();
        CombinationReactions.initialize();
        FireReacts.initialize();
        ChemicalElements.initialize();
    }

    public static void initializeConfig() {
        configs.setLoader(() -> EntrustEnvironment.receptacle(receptacle -> {
            File config = new File("config/the-road-to-reality/config.conf");
            if (! config.isFile()) {
                TrtrMod.LOGGER.info("Config not found, generating default config");

                EntrustEnvironment.tryTemporary(() -> config.getParentFile()
                                                            .mkdirs());

                receptacle.set(IOUtil.read(Resources.getResource("default-config.conf")));

                EntrustEnvironment.tryTemporary(() -> IOUtil.write(
                        new BufferedWriter(new FileWriter(config)),
                        receptacle.get()
                ));
            }

            if (receptacle.get() == null) {
                receptacle.set(IOUtil.read(new BufferedReader(new FileReader(config))));
            }
        }));

        configs.load();
    }
}
