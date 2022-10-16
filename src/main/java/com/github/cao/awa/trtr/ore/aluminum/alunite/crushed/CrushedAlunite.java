package com.github.cao.awa.trtr.ore.aluminum.alunite.crushed;

import com.github.cao.awa.trtr.ref.item.*;
import com.github.cao.awa.trtr.ref.item.trtr.*;
import com.github.cao.awa.trtr.type.*;
import com.github.zhuaidadaya.rikaishinikui.handler.range.*;
import com.github.zhuaidadaya.rikaishinikui.handler.range.table.*;
import net.minecraft.item.*;
import net.minecraft.util.*;

import java.util.*;

public class CrushedAlunite extends TrtrItem implements Hammerable {
    public static final Identifier IDENTIFIER = new Identifier("trtr:crushed_alunite");

    @Override
    public Identifier identifier() {
        return IDENTIFIER;
    }

    @Override
    public RangeTable<Item, NumberRange<Item>> products() {
        return TrtrHammerableProducts.ALUNITE_POWDER;
    }

    @Override
    public Set<Item> prototypes() {
        return Set.of(TrtrBlocks.ALUNITE_BLOCK.asItem());
    }
}
