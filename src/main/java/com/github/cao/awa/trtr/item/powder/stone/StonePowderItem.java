package com.github.cao.awa.trtr.item.powder.stone;

import com.github.cao.awa.apricot.anntation.Auto;
import com.github.cao.awa.trtr.block.TrtrBlocks;
import com.github.cao.awa.trtr.block.grave.NoFlintGravelBlock;
import com.github.cao.awa.trtr.item.SneakingPlaceBlock;
import com.github.cao.awa.trtr.item.handcraft.CraftingItem;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;

@Auto
public class StonePowderItem extends CraftingItem {
    @Auto
    public static final Identifier IDENTIFIER = Identifier.tryParse("trtr:stone_powder");

    @Auto
    public StonePowderItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        return SneakingPlaceBlock.place(context,
                                        4,
                                        TrtrBlocks.get(NoFlintGravelBlock.class)
        );
    }
}
