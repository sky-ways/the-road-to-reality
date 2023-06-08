package com.github.cao.awa.trtr.block.example.simple.model;

import com.github.cao.awa.apricot.anntation.Auto;
import com.github.cao.awa.trtr.block.TrtrBlocks;
import com.github.cao.awa.trtr.block.example.simple.SimpleExampleBlock;
import com.github.cao.awa.trtr.framework.accessor.data.gen.model.TrtrBlockModelProvider;
import com.github.cao.awa.trtr.identifier.namespane.Namespace;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.data.client.BlockStateModelGenerator;

@Auto
public class SimpleExampleModel extends TrtrBlockModelProvider {
    @Auto
    public SimpleExampleModel(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        System.out.println("Custom simple model generator");
        blockStateModelGenerator.registerSimpleState(TrtrBlocks.get(SimpleExampleBlock.IDENTIFIER));
        blockStateModelGenerator.registerParentedItemModel(TrtrBlocks.get(SimpleExampleBlock.IDENTIFIER),
                                                           Namespace.subSpace(SimpleExampleBlock.IDENTIFIER,
                                                                              "block"
                                                           )
        );
    }
}
