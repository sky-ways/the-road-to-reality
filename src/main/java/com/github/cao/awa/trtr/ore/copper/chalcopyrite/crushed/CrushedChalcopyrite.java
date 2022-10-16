package com.github.cao.awa.trtr.ore.copper.chalcopyrite.crushed;

import com.github.cao.awa.trtr.ref.item.*;
import com.github.cao.awa.trtr.ref.item.trtr.*;
import com.github.cao.awa.trtr.type.*;
import com.github.zhuaidadaya.rikaishinikui.handler.range.*;
import com.github.zhuaidadaya.rikaishinikui.handler.range.table.*;
import net.minecraft.item.*;
import net.minecraft.util.*;

import java.util.*;

public class CrushedChalcopyrite extends TrtrItem implements Hammerable {
    public static final Identifier IDENTIFIER = new Identifier("trtr:crushed_chalcopyrite");

    @Override
    public Identifier identifier() {
        return IDENTIFIER;
    }

    @Override
    public RangeTable<Item, NumberRange<Item>> products() {
        return TrtrHammerableProducts.CHALCOPYRITE_POWDER;
    }

    @Override
    public Set<Item> prototypes() {
        return Set.of(TrtrBlocks.CHALCOPYRITE_BLOCK.asItem());
    }
}
