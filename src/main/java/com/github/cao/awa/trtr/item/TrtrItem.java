package com.github.cao.awa.trtr.item;

import com.github.cao.awa.apricot.anntation.Auto;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;

@Auto
public abstract class TrtrItem extends Item {
    public TrtrItem(Settings settings) {
        super(settings);
    }

    public TrtrItem() {
        super(new FabricItemSettings());
    }
}
