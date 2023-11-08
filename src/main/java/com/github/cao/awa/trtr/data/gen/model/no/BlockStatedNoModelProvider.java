package com.github.cao.awa.trtr.data.gen.model.no;

import com.github.cao.awa.apricot.anntation.Auto;
import com.github.cao.awa.trtr.framework.accessor.data.gen.model.TrtrBlockModelProvider;
import com.github.cao.awa.trtr.framework.block.data.gen.model.BlockModelDataGenFramework;
import com.github.cao.awa.trtr.framework.model.provider.FrameworkModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.block.Block;
import net.minecraft.data.client.BlockStateModelGenerator;

/**
 * Do not generate block model for blocks.
 *
 * @author 草二号机
 * @see BlockModelDataGenFramework
 * @see FrameworkModelProvider
 * @since 1.0.0
 */
@Auto
public class BlockStatedNoModelProvider extends TrtrBlockModelProvider {
    private final Block block;

    @Auto
    public BlockStatedNoModelProvider(FabricDataOutput output, Block block) {
        super(output);
        this.block = block;
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleState(this.block);
    }
}
