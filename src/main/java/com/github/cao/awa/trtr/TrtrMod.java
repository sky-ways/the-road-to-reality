package com.github.cao.awa.trtr;

import com.github.cao.awa.modmdo.annotations.platform.*;
import com.github.cao.awa.trtr.air.manager.*;
import com.github.cao.awa.trtr.database.*;
import com.github.cao.awa.trtr.debuger.performance.tracker.*;
import com.github.cao.awa.trtr.element.chemical.*;
import com.github.cao.awa.trtr.heat.handler.*;
import com.github.cao.awa.trtr.ref.item.fire.*;
import com.github.cao.awa.trtr.type.*;
import com.github.zhuaidadaya.rikaishinikui.handler.universal.runnable.*;
import net.fabricmc.api.*;
import org.apache.logging.log4j.*;
import redis.clients.jedis.*;

@Server
public class TrtrMod implements ModInitializer {
    public static final String VERSION = "1.0.0";
    public static final Logger LOGGER = LogManager.getLogger("Trtr");
    public static HeatManager heatManager = new HeatManager();
    public static AirManager airManager = new AirManager();
    public static SubmitTimeTracker timeTracker = new SubmitTimeTracker();
    public static Counter counter = new Counter();
    public static FutureTaskOrder delayTasks = new FutureTaskOrder();

    public static InstancePropertiesDatabase propertiesDatabase = null;

    @Override
    public void onInitialize() {
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
}
