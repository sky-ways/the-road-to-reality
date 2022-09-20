package com.github.cao.awa.trtr;

import com.github.cao.awa.trtr.debuger.performance.tracker.*;
import com.github.cao.awa.trtr.heat.handler.*;
import com.github.cao.awa.trtr.type.*;
import net.fabricmc.api.*;
import net.fabricmc.fabric.api.loot.v2.*;
import net.minecraft.block.*;
import net.minecraft.entity.*;
import net.minecraft.item.*;
import net.minecraft.loot.*;
import net.minecraft.loot.entry.*;
import org.apache.logging.log4j.*;

public class TrtrMod implements ModInitializer {
    public static HeatHandler heatHandler = new HeatHandler();
    public static SubmitTimeTracker timeTracker = new SubmitTimeTracker();
    public static final Logger LOGGER = LogManager.getLogger("FOML");

    @Override
    public void onInitialize() {
        TrtrItems.pre();
        TrtrBlocks.pre();
        TrtrBlockEntityType.pre();
        TrtrScreenHandlerType.pre();
        TrtrPlacedFeatures.pre();
        TrtrHammerableProducts.pre();
        TrtrItemGroup.pre();
    }
}
