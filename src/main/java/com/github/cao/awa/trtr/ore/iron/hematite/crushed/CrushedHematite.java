package com.github.cao.awa.trtr.ore.iron.hematite.crushed;

import com.github.cao.awa.trtr.ref.item.*;
import com.github.cao.awa.trtr.ref.item.trtr.*;
import com.github.cao.awa.trtr.type.*;
import com.github.zhuaidadaya.rikaishinikui.handler.rage.*;
import com.github.zhuaidadaya.rikaishinikui.handler.rage.table.*;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.util.registry.*;

import java.util.*;

public class CrushedHematite extends TrtrItem implements Hammerable {
    public static final Identifier IDENTIFIER = new Identifier("trtr:crushed_hematite");

    public CrushedHematite(Settings settings) {
        super(settings);
    }

    public static Item register() {
        Settings settings = new Settings();
        CrushedHematite hematite = new CrushedHematite(settings);
        Registry.register(Registry.ITEM, IDENTIFIER, hematite);
        return hematite;
    }

    @Override
    public RageTable<Item, NumberRage<Item>> products() {
        return TrtrHammerableProducts.HEMATITE_POWDER;
    }

    @Override
    public Set<Item> prototypes() {
        return Set.of(Items.IRON_ORE);
    }
}
