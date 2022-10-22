package com.github.cao.awa.trtr;

import com.github.cao.awa.modmdo.annotations.platform.*;
import com.github.cao.awa.trtr.data.gen.advbancement.*;
import com.github.cao.awa.trtr.data.gen.loot.block.*;
import net.fabricmc.fabric.api.datagen.v1.*;

@Server
public class TrtrDatagen implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        fabricDataGenerator.addProvider(AdvancementDatagen::new);
        fabricDataGenerator.addProvider(BlockLootDatagen::new);
    }
}