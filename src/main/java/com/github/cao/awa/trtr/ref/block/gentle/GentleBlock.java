package com.github.cao.awa.trtr.ref.block.gentle;

public interface GentleBlock {
    default boolean willCrushSpreadableBlock() {
        return false;
    }
}
