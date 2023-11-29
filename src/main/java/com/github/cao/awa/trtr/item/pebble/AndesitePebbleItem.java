package com.github.cao.awa.trtr.item.pebble;

import com.github.cao.awa.apricot.anntation.Auto;
import com.github.cao.awa.trtr.block.TrtrBlocks;
import com.github.cao.awa.trtr.block.cobbled.CobbledAndesite;
import com.github.cao.awa.trtr.item.SneakingPlaceBlock;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;

@Auto
public class AndesitePebbleItem extends PebbleItem {
    @Auto
    public static final Identifier IDENTIFIER = Identifier.tryParse("trtr:andesite_pebble");

    @Auto
    public AndesitePebbleItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        return SneakingPlaceBlock.place(context,
                                        4,
                                        TrtrBlocks.get(CobbledAndesite.IDENTIFIER)
        );
    }
}
