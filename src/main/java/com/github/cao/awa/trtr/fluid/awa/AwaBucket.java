package com.github.cao.awa.trtr.fluid.awa;

import com.github.cao.awa.apricot.anntation.Auto;
import com.github.cao.awa.trtr.TrtrMod;
import com.github.cao.awa.trtr.annotation.dev.DevOnly;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;

@Auto
@DevOnly
public class AwaBucket extends BucketItem {
    @Auto
    public static final Identifier IDENTIFIER = Identifier.tryParse("trtr:awa_bucket");

    @Auto
    public static final FabricItemSettings SETTINGS = new FabricItemSettings().recipeRemainder(Items.BUCKET)
                                                                              .maxCount(1);

    @Auto
    public AwaBucket(Settings settings) {
        super(TrtrMod.AWA,
              settings
        );
    }
}
