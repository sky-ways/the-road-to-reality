package com.github.cao.awa.trtr.item.pebble;

import com.github.cao.awa.apricot.anntation.Auto;
import com.github.cao.awa.trtr.block.TrtrBlocks;
import com.github.cao.awa.trtr.block.cobbled.CobbledGranite;
import com.github.cao.awa.trtr.item.SneakingPlaceBlock;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;

@Auto
public class GranitePebbleItem extends PebbleItem {
    @Auto
    public static final Identifier IDENTIFIER = Identifier.tryParse("trtr:granite_pebble");

    @Auto
    public GranitePebbleItem(Item.Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        return SneakingPlaceBlock.place(context,
                                        4,
                                        TrtrBlocks.get(CobbledGranite.IDENTIFIER)
        );
    }
}
