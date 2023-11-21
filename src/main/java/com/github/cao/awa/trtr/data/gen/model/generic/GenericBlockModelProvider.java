package com.github.cao.awa.trtr.data.gen.model.generic;

import com.github.cao.awa.apricot.anntation.Auto;
import com.github.cao.awa.trtr.block.TrtrBlocks;
import com.github.cao.awa.trtr.framework.accessor.data.gen.model.TrtrBlockModelProvider;
import com.github.cao.awa.trtr.framework.block.data.gen.model.BlockModelDataGenFramework;
import com.github.cao.awa.trtr.framework.model.provider.FrameworkModelProvider;
import com.github.cao.awa.trtr.identifier.namespane.Namespace;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.block.Block;
import net.minecraft.data.client.BlockStateModelGenerator;

/**
 * Generate a whole block model for blocks.
 * And generate no flat texture for item of blocks.
 *
 * @author cao_awa
 * @author 草二号机
 * @see BlockModelDataGenFramework
 * @see FrameworkModelProvider
 * @since 1.0.0
 */
@Auto
public class GenericBlockModelProvider extends TrtrBlockModelProvider {
    private final Block block;

    @Auto
    public GenericBlockModelProvider(FabricDataOutput output, Block block) {
        super(output);
        this.block = block;
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        // Default block model is a whole block.
        // The item parented models should in 'block' subspace.
        blockStateModelGenerator.registerSimpleCubeAll(this.block);
        blockStateModelGenerator.registerParentedItemModel(this.block,
                                                           Namespace.subSpace(TrtrBlocks.getIdentifier(this.block),
                                                                              "block"
                                                           )
        );
    }
}
