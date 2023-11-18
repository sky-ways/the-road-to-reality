package com.github.cao.awa.trtr.item.spear.flint;

import com.github.cao.awa.apricot.anntation.Auto;
import com.github.cao.awa.trtr.constant.flint.FlintConstants;
import com.github.cao.awa.trtr.item.TrtrItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.util.Identifier;

@Auto
public class FlintSpearItem extends TrtrItem {
    @Auto
    public static final Identifier IDENTIFIER = Identifier.tryParse("trtr:flint_spear");
    @Auto
    public static final FabricItemSettings SETTINGS = new FabricItemSettings()
            .maxCount(1)
            .maxDamage(FlintConstants.FLINT_TOOL_MAX_DAMAGE);

    @Auto
    public FlintSpearItem(Settings settings) {
        super(settings);
    }
}
