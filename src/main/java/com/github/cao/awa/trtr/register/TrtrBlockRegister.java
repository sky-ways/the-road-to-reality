package com.github.cao.awa.trtr.register;

import com.github.cao.awa.trtr.ore.generic.*;
import com.github.cao.awa.trtr.ref.block.trtr.*;
import net.minecraft.util.registry.*;

/**
 * Register for block.
 *
 * @author cao_awa
 * @since 1.0.0
 */
public final class TrtrBlockRegister {
    private boolean registerBlock = false;
    private boolean registerItem = false;
    private TrtrBasedBlock block = null;

    public boolean isRegisterBlock() {
        return registerBlock;
    }

    public boolean isRegisterItem() {
        return registerItem;
    }

    public TrtrBasedBlock getBlock() {
        return block;
    }

    public TrtrBlockRegister(TrtrBasedBlock block) {
        block(block);
    }

    public TrtrBlockRegister() {

    }

    public void register() {
        if (registerBlock) {
            Registry.register(Registry.BLOCK, block.identifier(), block);
        }
        if (registerItem) {
            new TrtrGenBlockItem(block);
        }
    }

    public TrtrBlockRegister block(TrtrBasedBlock block) {
        this.block = block;
        return this;
    }

    public TrtrBlockRegister registerBlock(boolean register) {
        this.registerBlock = register;
        return this;
    }

    public TrtrBlockRegister registerItem(boolean register) {
        this.registerItem = register;
        return this;
    }
}