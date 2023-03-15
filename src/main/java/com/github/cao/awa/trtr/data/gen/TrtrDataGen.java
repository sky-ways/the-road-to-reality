package com.github.cao.awa.trtr.data.gen;

import com.github.cao.awa.trtr.TrtrMod;
import com.github.cao.awa.trtr.block.TrtrBlocks;
import com.github.cao.awa.trtr.block.example.ExampleBlock;
import com.github.cao.awa.trtr.block.ore.aluminum.alunite.Alunite;
import com.github.cao.awa.trtr.data.gen.model.GenericModelProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class TrtrDataGen implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        TrtrMod.BLOCK_FRAMEWORK.dataGen().loot(fabricDataGenerator);
        TrtrMod.BLOCK_FRAMEWORK.dataGen().model(fabricDataGenerator);

//        fabricDataGenerator.createPack()
//                           .addProvider((output, future) -> new GenericModelProvider(output,
//                                                                                     TrtrBlocks.get(Alunite.IDENTIFIER)
//                           ));
    }
}
