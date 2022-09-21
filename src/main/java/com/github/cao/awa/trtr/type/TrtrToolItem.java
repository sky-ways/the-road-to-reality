package com.github.cao.awa.trtr.type;

import com.github.cao.awa.trtr.ref.item.trtr.*;
import net.minecraft.item.*;

public abstract class TrtrToolItem extends TrtrItem {
    public TrtrToolItem(Settings settings) {
        super(settings);
    }

    public int getEnchantability() {
        return 5;
    }

    public boolean canRepair(ItemStack stack, ItemStack ingredient) {
        return super.canRepair(stack, ingredient);
    }
}
