package com.github.cao.awa.trtr.ore.feldspar.orthoclase.crushed;

import com.github.cao.awa.trtr.ref.item.*;
import com.github.cao.awa.trtr.ref.item.trtr.*;
import com.github.cao.awa.trtr.type.*;
import com.github.zhuaidadaya.rikaishinikui.handler.range.*;
import com.github.zhuaidadaya.rikaishinikui.handler.range.table.*;
import net.minecraft.item.*;
import net.minecraft.util.*;

import java.util.*;

public class CrushedOrthoclase extends TrtrItem implements Hammerable {
    public static final Identifier IDENTIFIER = new Identifier("trtr:crushed_orthoclase");

    @Override
    public Identifier identifier() {
        return IDENTIFIER;
    }

    @Override
    public RangeTable<Item, NumberRange<Item>> products() {
        return TrtrHammerableProducts.ORTHOCLASE_POWDER;
    }

    @Override
    public Set<Item> prototypes() {
        return Set.of(TrtrBlocks.ORTHOCLASE_BLOCK.asItem());
    }
}
