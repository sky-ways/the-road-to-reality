package com.github.cao.awa.trtr.item;

import com.github.cao.awa.apricot.anntation.Auto;
import net.minecraft.item.Item;

@Auto
public abstract class TrtrItem extends Item {
    @Auto
    public TrtrItem(Settings settings) {
        super(settings);
    }
}
