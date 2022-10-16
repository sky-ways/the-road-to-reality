package com.github.cao.awa.trtr.ore.stone.crushed;

import com.github.cao.awa.trtr.ref.item.*;
import com.github.cao.awa.trtr.ref.item.trtr.*;
import com.github.cao.awa.trtr.type.*;
import com.github.zhuaidadaya.rikaishinikui.handler.range.*;
import com.github.zhuaidadaya.rikaishinikui.handler.range.table.*;
import net.minecraft.block.*;
import net.minecraft.item.*;
import net.minecraft.util.*;

import java.util.*;

public class CrushedDeepslate extends TrtrItem implements Hammerable {
    public static final Identifier IDENTIFIER = new Identifier("trtr:crushed_deepslate");

    @Override
    public Identifier identifier() {
        return IDENTIFIER;
    }

    @Override
    public RangeTable<Item, NumberRange<Item>> products() {
        return TrtrHammerableProducts.DEEPSLATE_POWDER;
    }

    @Override
    public Set<Item> prototypes() {
        return Set.of(Blocks.DEEPSLATE.asItem());
    }
}
