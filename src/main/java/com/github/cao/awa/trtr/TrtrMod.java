package com.github.cao.awa.trtr;

import com.github.cao.awa.modmdo.annotations.platform.*;
import com.github.cao.awa.trtr.debuger.performance.tracker.*;
import com.github.cao.awa.trtr.element.chemical.*;
import com.github.cao.awa.trtr.heat.handler.*;
import com.github.cao.awa.trtr.type.*;
import net.fabricmc.api.*;
import org.apache.logging.log4j.*;

@Server
public class TrtrMod implements ModInitializer {
    public static HeatHandler heatHandler = new HeatHandler();
    public static final String VERSION = "1.0.0";
    public static SubmitTimeTracker timeTracker = new SubmitTimeTracker();
    public static final Logger LOGGER = LogManager.getLogger("Trtr");

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
    }
}
