package com.github.cao.awa.trtr.ore.stone.crushed;

import com.github.cao.awa.trtr.ref.item.*;
import com.github.cao.awa.trtr.ref.item.trtr.*;
import com.github.cao.awa.trtr.type.*;
import com.github.zhuaidadaya.rikaishinikui.handler.rage.*;
import com.github.zhuaidadaya.rikaishinikui.handler.rage.table.*;
import net.minecraft.block.*;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.util.registry.*;

import java.util.*;

public class CrushedDeepslate extends TrtrItem implements Hammerable {
    public static final Identifier IDENTIFIER = new Identifier("trtr:crushed_deepslate");

    public CrushedDeepslate(Settings settings) {
        super(settings);
    }

    public static Item register() {
        Settings settings = new Settings();
        CrushedDeepslate deepslate = new CrushedDeepslate(settings);
        Registry.register(Registry.ITEM, IDENTIFIER, deepslate);
        return deepslate;
    }

    @Override
    public RageTable<Item, NumberRage<Item>> products() {
        return TrtrHammerableProducts.DEEPSLATE_POWDER;
    }

    @Override
    public Set<Item> prototypes() {
        return Set.of(Blocks.DEEPSLATE.asItem());
    }
}
