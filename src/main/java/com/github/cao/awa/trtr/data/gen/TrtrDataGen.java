package com.github.cao.awa.trtr.data.gen;

import com.github.cao.awa.trtr.TrtrMod;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class TrtrDataGen implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        TrtrMod.BLOCK_FRAMEWORK.dataGen()
                               .loot(fabricDataGenerator);
        TrtrMod.BLOCK_FRAMEWORK.dataGen()
                               .model(fabricDataGenerator);
        TrtrMod.BLOCK_FRAMEWORK.dataGen()
                               .mineable(fabricDataGenerator);
        TrtrMod.BLOCK_FRAMEWORK.dataGen()
                               .tag(fabricDataGenerator);

        TrtrMod.ITEM_FRAMEWORK.dataGen()
                              .model(fabricDataGenerator);

        TrtrMod.MODEL_FRAMEWORK.done(fabricDataGenerator);
    }
}
