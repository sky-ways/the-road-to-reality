package com.github.cao.awa.trtr.block;

import com.github.cao.awa.apricot.anntation.Auto;
import net.minecraft.block.Block;

@Auto
public abstract class TrtrBlock extends Block {
    @Auto
    public TrtrBlock(Settings settings) {
        super(settings);
    }
}
