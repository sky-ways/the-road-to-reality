package com.github.cao.awa.trtr.ore.copper.cuprite.crushed;

import com.github.cao.awa.trtr.ref.item.*;
import com.github.cao.awa.trtr.ref.item.trtr.*;
import com.github.cao.awa.trtr.type.*;
import com.github.zhuaidadaya.rikaishinikui.handler.rage.*;
import com.github.zhuaidadaya.rikaishinikui.handler.rage.table.*;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.util.registry.*;

import java.util.*;

public class CrushedCuprite extends TrtrItem implements Hammerable {
    public static final Identifier IDENTIFIER = new Identifier("trtr:crushed_cuprite");

    @Override
    public Identifier identifier() {
        return IDENTIFIER;
    }

    @Override
    public RageTable<Item, NumberRage<Item>> products() {
        return TrtrHammerableProducts.CUPRITE_POWDER;
    }

    @Override
    public Set<Item> prototypes() {
        return Set.of(TrtrBlocks.CUPRITE_BLOCK.asItem());
    }
}
