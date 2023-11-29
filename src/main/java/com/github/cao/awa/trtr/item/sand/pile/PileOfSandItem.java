package com.github.cao.awa.trtr.item.sand.pile;

import com.github.cao.awa.apricot.anntation.Auto;
import com.github.cao.awa.trtr.item.SneakingPlaceBlock;
import com.github.cao.awa.trtr.item.handcraft.CraftingItem;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;

@Auto
public class PileOfSandItem extends CraftingItem {
    @Auto
    public static final Identifier IDENTIFIER = Identifier.tryParse("trtr:pile_of_sand");

    @Auto
    public PileOfSandItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        return SneakingPlaceBlock.place(context,
                                        4,
                                        Blocks.SAND
        );
    }
}
