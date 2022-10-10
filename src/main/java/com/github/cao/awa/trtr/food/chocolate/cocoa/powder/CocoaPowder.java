package com.github.cao.awa.trtr.food.chocolate.cocoa.powder;

import com.github.cao.awa.trtr.ref.item.*;
import com.github.cao.awa.trtr.ref.item.trtr.*;
import com.github.cao.awa.trtr.type.*;
import com.github.zhuaidadaya.rikaishinikui.handler.rage.*;
import com.github.zhuaidadaya.rikaishinikui.handler.rage.table.*;
import net.minecraft.item.*;
import net.minecraft.util.*;

import java.util.*;

public class CocoaPowder extends TrtrItem implements Hammerable {
    public static final Identifier IDENTIFIER = new Identifier("trtr:cocoa_powder");

    @Override
    public Identifier identifier() {
        return IDENTIFIER;
    }

    @Override
    public RageTable<Item, NumberRage<Item>> products() {
        return TrtrHammerableProducts.COCOA_POWDER;
    }

    @Override
    public Set<Item> prototypes() {
        return Set.of(Items.COCOA_BEANS);
    }
}
