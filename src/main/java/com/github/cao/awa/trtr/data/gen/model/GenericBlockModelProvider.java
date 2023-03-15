package com.github.cao.awa.trtr.data.gen.model;

import com.github.cao.awa.trtr.block.TrtrBlocks;
import com.github.cao.awa.trtr.framework.data.gen.model.TrtrBlockModelProvider;
import com.github.cao.awa.trtr.identifier.namespane.Namespace;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.block.Block;
import net.minecraft.data.client.BlockStateModelGenerator;

public class GenericBlockModelProvider extends TrtrBlockModelProvider {
    private final Block block;

    public GenericBlockModelProvider(FabricDataOutput output, Block block) {
        super(output);
        this.block = block;
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(this.block);
        blockStateModelGenerator.registerParentedItemModel(this.block,
                                                           Namespace.subSpace(TrtrBlocks.getIdentifier(this.block),
                                                                              "block"
                                                           )
        );
    }
}
