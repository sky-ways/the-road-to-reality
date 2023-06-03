package com.github.cao.awa.trtr.block;

import com.github.cao.awa.apricot.anntation.Auto;
import com.github.cao.awa.trtr.TrtrMod;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.StateManager;

@Auto
public abstract class TrtrBlock extends Block {
    @Auto
    public TrtrBlock(Settings settings) {
        super(settings);
    }

    // Append properties to state builder.
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        TrtrMod.BLOCK_FRAMEWORK.properties(this,
                                           builder
        );
    }
}
