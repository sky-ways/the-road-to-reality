package com.github.cao.awa.trtr.ore.silver.acanthite.crushed;

import com.github.cao.awa.trtr.ref.item.*;
import com.github.cao.awa.trtr.ref.item.trtr.*;
import com.github.cao.awa.trtr.type.*;
import com.github.zhuaidadaya.rikaishinikui.handler.range.*;
import com.github.zhuaidadaya.rikaishinikui.handler.range.table.*;
import net.minecraft.item.*;
import net.minecraft.util.*;

import java.util.*;

public class CrushedDeepslateAcanthite extends TrtrItem implements Hammerable {
    public static final Identifier IDENTIFIER = new Identifier("trtr:crushed_deepslate_acanthite");

    @Override
    public Identifier identifier() {
        return IDENTIFIER;
    }

    @Override
    public RangeTable<Item, NumberRange<Item>> products() {
        return TrtrHammerableProducts.ACANTHITE_POWDER;
    }

    @Override
    public Set<Item> prototypes() {
        return Set.of(TrtrBlocks.DEEPSLATE_ACANTHITE_BLOCK.asItem());
    }
}
